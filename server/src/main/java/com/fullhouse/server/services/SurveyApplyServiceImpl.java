package com.fullhouse.server.services;

import com.fullhouse.DTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyApplyResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.forms.v1.Forms;
import com.google.api.services.forms.v1.model.Form;
import com.google.api.services.forms.v1.model.Info;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SurveyApplyServiceImpl implements SurveyApplyService {

    private static final String APPLICATION_NAME = "eval-it";
    private final GoogleOAuthService googleOAuthService;
    private final JsonFactory jsonFactory;

    public SurveyApplyServiceImpl(GoogleOAuthService googleOAuthService,
                              JsonFactory jsonFactory) {
        this.googleOAuthService = googleOAuthService;
        this.jsonFactory = jsonFactory;
    }

    @Override
    public SurveyApplyResponse applySurvey(SurveyApplyRequest request) {
        Form form;
        try {
            form = createNewForm();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //TODO: take the questions, and update the form so that it includes questions.git
        return new SurveyApplyResponse(form.getResponderUri());
    }

    private  Form createNewForm() throws Exception {
        String accessToken = googleOAuthService.getFreshAccessToken();

        GoogleCredentials credentials = GoogleCredentials.create(
                new AccessToken(accessToken, new Date(System.currentTimeMillis() + 3600_000))
        );

        Forms formsService = new Forms.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                jsonFactory,
                new HttpCredentialsAdapter(credentials)
        ).setApplicationName(APPLICATION_NAME).build();

        Form form = new Form();
        Info info = new Info();
        info.setTitle("New Form");
        form.setInfo(info);

        return formsService.forms().create(form).execute();
    }

}
