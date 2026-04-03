package com.fullhouse.server.services;

import com.fullhouse.DTOs.SurveyDTOs.*;
import com.fullhouse.server.domain.Business;
import com.fullhouse.server.domain.ParentSurvey;
import com.fullhouse.server.domain.Survey;
import com.fullhouse.server.mappers.SurveyToGetSurveyListMapper;
import com.fullhouse.server.repositories.BusinessRepository;
import com.fullhouse.server.repositories.ParentSurveyRepository;
import com.fullhouse.server.repositories.SurveyRepository;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.forms.v1.Forms;
import com.google.api.services.forms.v1.model.*;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import jakarta.transaction.Transactional;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * This service manages the server side
 * operations concerning Surveys.
 */
@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private static final String APPLICATION_NAME = "eval-it";
    private final BusinessRepository businessRepository; // NEW
    private final ParentSurveyRepository parentSurveyRepository;
    private final SurveyRepository surveyRepository;
    private final GoogleOAuthService googleOAuthService;
    private final JsonFactory jsonFactory;
    private Forms formsService;

    public SurveyServiceImpl(SurveyRepository surveyRepository, GoogleOAuthService googleOAuthService,
                             JsonFactory jsonFactory, BusinessRepository businessRepository, ParentSurveyRepository parentSurveyRepository) {
        this.googleOAuthService = googleOAuthService;
        this.jsonFactory = jsonFactory;
        this.surveyRepository = surveyRepository;
        this.businessRepository = businessRepository;
        this.parentSurveyRepository = parentSurveyRepository;
    }

    /**
     * This is the method called when a survey is
     * requested to be applied by a BusinessOwner.
     * The survey is first created, then updated
     * with the questions that the client sends.
     *
     * @return a link to the Google Forms
     */
    @Override
    public SurveyApplyResponse applySurvey(SurveyApplyRequest request) {

        Form form;
        if (businessRepository.findById(request.getBusinessId()).isEmpty()) {
            return new SurveyApplyResponse("");
        }
        Business business = businessRepository.findById(request.getBusinessId()).get();
        business.getSurveys().clear();
        businessRepository.save(business);

        String titleOfTheForm = business.getName() + " Survey";

        try {
            form = createNewForm(titleOfTheForm);
            createWatch(form.getFormId());
            createEntrancePage(form);
            for (long id : request.getParentSurveyIds()) {
                if (parentSurveyRepository.findById(id).isPresent()) {
                    ParentSurvey parentSurvey = parentSurveyRepository.findById(id).get();
                    parentSurvey.incrementPopularity();
                    updateForm(parentSurvey.getQuestions(), parentSurvey.getName(), form);
                    Survey survey = new Survey(parentSurvey.getName(), parentSurvey, business);
                    business.getSurveys().add(survey);
                    surveyRepository.save(survey);
                }
            }
            business.setFormId(form.getFormId());
            business.setFormOfSurvey(form.getResponderUri());
            businessRepository.save(business);

            endOfTheFormMessage(form, "Thanks for participating in " + titleOfTheForm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new SurveyApplyResponse(form.getResponderUri());

    }

    /**
     * This method receives a business ID and returns all
     * the surveys that has that business ID.
     *
     * @param request
     * @return response
     */
    public SurveyListResponse getSurveyList(SurveyListRequest request) {
        long businessId = request.getBusinessId();

        List<Survey> surveys = new ArrayList<>(surveyRepository.findByBusinessOfSurveyId(businessId));

        List<SurveyInListDTO> surveyDtos = new ArrayList<>();
        for (Survey s : surveys) surveyDtos.add(SurveyToGetSurveyListMapper.surveyToSurveyDTO(s));
        return new SurveyListResponse(surveyDtos);
    }

    /**
     * This method updates the Survey fields
     * when a response is given to a Survey
     * and the server receives it through the
     * pub/sub system.
     */
    public void updateSurveysBasedOnTheResponse(String formId) {

        List<Survey> surveys = businessRepository.findByFormId(formId).getSurveys();
        List<Integer> questionNumbersInEachSurvey = new ArrayList<>();
        for (Survey s : surveys) {
            questionNumbersInEachSurvey.add(s.getParentSurvey().getQuestions().size());
        }

        float averageScoreOfTheBusiness = 0.0f;
        try {
            for (Survey s : computeScoresOfSurveys(surveys, questionNumbersInEachSurvey, formId)) {
                surveyRepository.save(s);
                averageScoreOfTheBusiness += s.getOverallScore();
            }
        } catch (Exception _) {

        }
        averageScoreOfTheBusiness = averageScoreOfTheBusiness / surveys.size();
        businessRepository.findByFormId(formId).setAverageScore(averageScoreOfTheBusiness);
        businessRepository.save(businessRepository.findByFormId(formId));
    }

    /**
     * Helper to create a new Form.
     *
     * @return A Google Form
     */
    private Form createNewForm(String title) throws Exception {
        identify();
        Form form = new Form();
        Info info = new Info();
        info.setTitle(title);
        form.setInfo(info);
        return formsService.forms().create(form).execute();
    }

    private void createEntrancePage(Form form) throws Exception {
        identify();

        List<Request> requests = new ArrayList<>();

        int size = formsService.forms().get(form.getFormId()).execute().getItems() == null ? 0 : formsService.forms().get(form.getFormId()).execute().getItems().size();

        requests.add((new Request())
                .setCreateItem(
                        (new CreateItemRequest())
                                .setLocation(
                                        (new Location()).setIndex(size))
                                .setItem(
                                        (new Item())
                                                .setTextItem(new TextItem())
                                                .setTitle("Welcome to the survey. You can rate your experience with the star ratings from 1-5.")
                                )
                )
        );
//        divideSection(form, "", requests, size + 1);
        formsService.forms().batchUpdate(form.getFormId(), (new BatchUpdateFormRequest()).setRequests(requests)).execute();

    }

    private void divideSection(Form form, String sectionTitle, String description, List<Request> requests, int atIndex) throws Exception {
        identify();

        requests.add((new Request())
                .setCreateItem(
                        (new CreateItemRequest())
                                .setItem(new Item()
                                        .setTitle(sectionTitle.toUpperCase())
                                        .setDescription(description)
                                        .setPageBreakItem(new PageBreakItem()))
                                .setLocation(new Location().setIndex(atIndex))
                )
        );
    }

    /**
     * Helper to update the form with the
     * given questions.
     *
     * @param questions
     * @param form
     */
    private void updateForm(List<String> questions, String sectionTitle, Form form) throws Exception {
        identify();
        List<Request> requests = new ArrayList<>();

        List<Item> items = formsService.forms().get(form.getFormId()).execute().getItems();
        int curLength = items == null ? 0 : items.size();

        divideSection(formsService.forms().get(form.getFormId()).execute(), sectionTitle, "Please select a number.", requests, curLength);
        curLength++;

        int cnt = curLength;
        for (String q : questions) {
            requests.add(
                    new Request().setCreateItem
                            ((new CreateItemRequest())
                                    .setLocation
                                            ((new Location()).setIndex(cnt))
                                    .setItem
                                            ((new Item())
                                                    .setDescription(questions.get(cnt - curLength))
                                                    .setQuestionItem
                                                            ((new QuestionItem()).setQuestion
                                                                    ((new Question())
                                                                            .setQuestionId(String.valueOf(cnt))
                                                                            .setRequired(true)
                                                                            .setRatingQuestion
                                                                                    ((new RatingQuestion()).setIconType("STAR").setRatingScaleLevel(5))))
                                            )
                            )
            );
            cnt++;
        }


        formsService.forms().batchUpdate(form.getFormId(), (new BatchUpdateFormRequest()).setRequests(requests)).execute();
    }

    /**
     * Helper to add a final message at the
     * end of the Google Form
     */
    private void endOfTheFormMessage(Form form, String message) throws Exception {
        identify();
        List<Request> requests = new ArrayList<>();

        int size = formsService.forms().get(form.getFormId()).execute().getItems() == null ? 0 : formsService.forms().get(form.getFormId()).execute().getItems().size();
        divideSection(formsService.forms().get(form.getFormId()).execute(), "End of Survey", "", requests, size);
        size++;

        requests.add((new Request())
                .setCreateItem(
                        (new CreateItemRequest())
                                .setLocation(
                                        (new Location()).setIndex(size))
                                .setItem(
                                        (new Item())
                                                .setTextItem(new TextItem())
                                                .setTitle(message)
                                )
                )
        );
        formsService.forms().batchUpdate(form.getFormId(), (new BatchUpdateFormRequest()).setRequests(requests)).execute();
    }

    /**
     * Helper to create a watch for the newly
     * applied {@link Survey}. This watch sends
     * a notification to the specified Pub/Sub
     * topic. Note that the name of the topic
     * is not parameterized here.
     *
     * @param formId
     * @throws Exception
     */
    private void createWatch(String formId) throws Exception {
        identify();

        formsService.forms().watches().create(formId, (new CreateWatchRequest()).setWatch((new Watch())
                .setEventType("RESPONSES")
                .setTarget((new WatchTarget()).setTopic((new CloudPubsubTopic()).setTopicName("projects/eval-it-490310/topics/responses")))
        )).execute();
    }

    /**
     * Helper to refresh the authorization. Called
     * before using formsService of driveService.
     *
     */
    private void identify() throws Exception {
        String accessToken = googleOAuthService.getFreshAccessToken();

        GoogleCredentials credentials = GoogleCredentials.create(
                new AccessToken(accessToken, new Date(System.currentTimeMillis() + 3600_000))
        );

        formsService = new Forms.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                jsonFactory,
                new HttpCredentialsAdapter(credentials)
        ).setApplicationName(APPLICATION_NAME).build();
    }

    /**
     * Computes the overall scores for each of the surveys
     * Also computes the overall scores for each of the questions of the surveys.
     * Returns the updates surveys.
     * @param surveys
     * @param questionNumbersInEachSurvey
     * @param formId
     * @return
     * @throws Exception
     */
    private List<Survey> computeScoresOfSurveys(List<Survey> surveys, List<Integer> questionNumbersInEachSurvey, String formId) throws Exception {
        identify();

        Forms.FormsOperations.Responses.List responsesList = formsService.forms().responses().list(formId);
        List<FormResponse> responses = responsesList.execute().getResponses();

        System.out.println("Size of the responses list is :" + responses.size());
        System.out.println("Size of the surveys list is :" + surveys.size());

        List<Answer> answerList;

        /*
         * This holds the sum of the answer given
         * to each question. For example for the 3
         * responses given to a 2 question survey,
         *
         * 3 4 1
         * 3 5 2
         *
         * The list will hold 8 and 10
         */
        List<Float> answerSums = new ArrayList<>();

        // Total number of questions in the whole form.
        // i.e. sum of the number of questions of the surveys
        // of the business.
        int totalNumberOfQuestions = 0;
        for(Integer i : questionNumbersInEachSurvey) totalNumberOfQuestions+=i;
        while(totalNumberOfQuestions > 0) {
            answerSums.add(0.0f);
            totalNumberOfQuestions--;
        }



        // traverse each response
        for (FormResponse fr : responses) {

            // Sort the answers given to ensure
            // the questions are ordered
            answerList = new ArrayList<>(fr.getAnswers().values());
            answerList.sort(new Comparator<Answer>() {
                @Override
                public int compare(Answer o1, Answer o2) {
                    return Math.round(Float.parseFloat(o1.getQuestionId()) - Float.parseFloat(o2.getQuestionId()));
                }
            });

            int indexOfTheAnswer = 0;
            for(Answer a : answerList) {
                Float givenAnswer = Float.parseFloat(a.getTextAnswers().getAnswers().getFirst().getValue());

                // increment the answerSum list
                answerSums.set(
                        indexOfTheAnswer,
                        answerSums.get(indexOfTheAnswer) + givenAnswer
                );
                indexOfTheAnswer++;
            }

        }


        // Computing the answers sums is done. Now
        // traverse the sum of answers
        int i = 0;

        int ithSurveyBeginIndex = 0;
        int ithSurveyEndIndex = 0;
        Iterator<Survey> iterateSurveys = surveys.iterator();
        Iterator<Integer> iterateQuestionNumbers = questionNumbersInEachSurvey.iterator();

        while(i < answerSums.size()) {

            Survey currentSurvey = iterateSurveys.next();
            Integer currentQuestion = iterateQuestionNumbers.next();

            ithSurveyBeginIndex = i;
            ithSurveyEndIndex = currentQuestion + i;

            List<Float> subAnswerList = new ArrayList<>(List.copyOf(answerSums.subList(ithSurveyBeginIndex, ithSurveyEndIndex)));
            // Calculate the sum of these elements
            Float sumForTheSurvey = 0.0f;
            for(Float f : subAnswerList) sumForTheSurvey += f;
            currentSurvey.setOverallScore(sumForTheSurvey / (responses.size() * subAnswerList.size()));


            // Divide each sum with the number of responses
            subAnswerList.replaceAll(aFloat -> aFloat / responses.size());
            currentSurvey.setScoresOfQuestions(subAnswerList);

            i = ithSurveyEndIndex;
        }

        return surveys;
    }

}
