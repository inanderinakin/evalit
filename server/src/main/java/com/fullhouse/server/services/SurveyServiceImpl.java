package com.fullhouse.server.services;

import com.fullhouse.DTOs.*;
import com.fullhouse.server.domain.Survey;
import com.fullhouse.server.mappers.SurveyToGetSurveyListMapper;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.forms.v1.Forms;
import com.google.api.services.forms.v1.model.*;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This service manages the server side
 * operations concerning Surveys.
 */
@Service
public class SurveyServiceImpl implements SurveyService {

    private static final String APPLICATION_NAME = "eval-it";
    private final GoogleOAuthService googleOAuthService;
    private final JsonFactory jsonFactory;
    private Forms formsService;

    public SurveyServiceImpl(GoogleOAuthService googleOAuthService,
                             JsonFactory jsonFactory) {
        this.googleOAuthService = googleOAuthService;
        this.jsonFactory = jsonFactory;
    }

    /**
     * This is the method called when a survey is
     * requested to be applied by a BusinessOwner.
     * The survey is first created, then updated
     * with the questions that the client sends.
     * @param request
     * @return response(link to the Google Forms)
     */
    @Override
    public SurveyApplyResponse applySurvey(SurveyApplyRequest request) {
        Form form;
        try {
            form = createNewForm(request.getTitle());
            updateForm(request.getQuestions(), form);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new SurveyApplyResponse(form.getResponderUri());

        // TODO: The survey now has a link, a list of questions, an ID, a ParentSurvey ID.
        //  Now, a Survey object must be created and saved to the database.
        //  See SurveyApplyRequest DTO for the information about which fields
        //  we receive from the Client. That part is tentative.
    }

    /**
     * This method receives a business ID and returns all
     * the surveys that has that business ID.
     * @param request
     * @return response
     */
    public SurveyListResponse getSurveyList(SurveyListRequest request) {
        long businessId = request.getBusinessId();
        List<Survey> surveys = new ArrayList<>();

        // Dummy surveys for testing. Delete later.
        surveys.add(new Survey("s1",123921239, 13,(float)4.5, 123));
        surveys.add(new Survey("s2",831273129, 18,(float)4.7, 124));
        surveys.add(new Survey("s3",132423523, 19,(float)4.9, 124));
        surveys.add(new Survey("s4",123712922, 21,(float)4.95, 123));

        // TODO: fetch Surveys from the database which have the given
        //  businessId. Add them to the surveys list. The rest will
        //  be handled.

        // TODO: When adding the surveys, I figured you will need to know
        //  their overallScores. The computeOverallScore method returns that.
        //  You must provide the Google Forms ID for the method.
        //  Actually it is better to modify the method so that it works with a
        //  Survey reference but for now I am leaving it like this. You may
        //  consider to change it.

        List<SurveyDTO> surveyDtos = new ArrayList<>();
        for( Survey s : surveys ) surveyDtos.add(SurveyToGetSurveyListMapper.surveyToSurveyDTO(s));
        return new SurveyListResponse(surveyDtos);
    }

    /**
     * Helper to create a new Form.
     * @param title
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

    /**
     * Helper to update the form with the
     * given questions.
     * @param questions
     * @param form
     * @throws Exception
     */
    private void updateForm(List<String> questions, Form form) throws Exception {
        identify();

        List<Request> requests = new ArrayList<>();
        int cnt = 0;
        for( String q : questions ) {
            requests.add(
                    new Request().setCreateItem
                            ((new CreateItemRequest())
                                    .setLocation
                                            ((new Location()).setIndex((Integer)cnt))
                                    .setItem
                                            ((new Item())
                                                            .setDescription(questions.get(cnt))
                                                            .setQuestionItem
                                                                    ((new QuestionItem()).setQuestion
                                                                            ((new Question()).setRatingQuestion
                                                                                    ((new RatingQuestion()).setIconType("STAR").setRatingScaleLevel(5))))
                                            )
                            )
            );
            cnt++;
        }

        formsService.forms().batchUpdate(form.getFormId(), (new BatchUpdateFormRequest()).setRequests(requests)).execute();
    }

    /**
     * Helper to refresh the authorization. Called
     * before using formsService of driveService.
     * @throws IOException
     * @throws GeneralSecurityException
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
     * Helper to compute the overall score for a {@link Survey}
     * This method computes the averages of the responses to all
     * questions from all the fill-outs of a Survey.
     * @param id (Google Forms ID)
     * @return overallScore
     */
    private float computeOverallScore(String id) throws Exception {
        identify();

        float overallScore = (float)0.0;
        Forms.FormsOperations.Responses.List responsesList = formsService.forms().responses().list(id);
        List<FormResponse> responses = responsesList.execute().getResponses();
        for( FormResponse fr : responses ) {
            for( Answer a : fr.getAnswers().values() ) {
                overallScore += Float.parseFloat(a.getTextAnswers().getAnswers().get(0).getValue());
            }
        }
        return overallScore / (responses.size()*responses.getFirst().getAnswers().size());
    }
}
