package com.fullhouse.server.services;

import com.fullhouse.DTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyApplyResponse;
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
public class SurveyApplyServiceImpl implements SurveyApplyService {

    private static final String APPLICATION_NAME = "eval-it";
    private final GoogleOAuthService googleOAuthService;
    private final JsonFactory jsonFactory;
    private Forms formsService;
    private GoogleCredentials credentials;
    private String accessToken;

    public SurveyApplyServiceImpl(GoogleOAuthService googleOAuthService,
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
     * Helper to create a new Form.
     * @param title
     * @return
     * @throws Exception
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
    private void identify() throws IOException, GeneralSecurityException {
        accessToken = googleOAuthService.getFreshAccessToken();

        credentials = GoogleCredentials.create(
                new AccessToken(accessToken, new Date(System.currentTimeMillis() + 3600_000))
        );

        formsService = new Forms.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                jsonFactory,
                new HttpCredentialsAdapter(credentials)
        ).setApplicationName(APPLICATION_NAME).build();
    }


}
