package com.fullhouse.server.services;

import com.fullhouse.DTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyApplyResponse;
import com.google.api.services.forms.v1.FormsScopes;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class SurveyApplyServiceImpl implements SurveyApplyService {

    @Override
    public SurveyApplyResponse applySurvey(SurveyApplyRequest request) {
        String token = null;
        try {
            token = getAccessToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return new SurveyApplyResponse();
    }

    private static String getAccessToken() throws IOException {
        GoogleCredentials credential = GoogleCredentials
                .fromStream(new FileInputStream("server/secrets/google-credentials.json"))
                .createScoped(FormsScopes.all());
        return credential.getAccessToken() != null ?
                credential.getAccessToken().getTokenValue() :
                credential.refreshAccessToken().getTokenValue();
    }

}
