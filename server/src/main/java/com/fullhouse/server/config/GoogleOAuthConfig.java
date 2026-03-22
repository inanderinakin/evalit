package com.fullhouse.server.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

/**
 * Configuration file for OAuth
 * Fetches the information from application.properties
 * Injects in {@link com.fullhouse.server.services.GoogleOAuthServiceImpl}
 */
@Configuration
public class GoogleOAuthConfig {

    private static final List<String> SCOPES = List.of(
            "https://www.googleapis.com/auth/forms.body",
            "https://www.googleapis.com/auth/drive",
            "https://www.googleapis.com/auth/drive.file",
            "https://www.googleapis.com/auth/forms.responses.readonly"
    );
    @Value("${google.oauth.client-id}")
    private String clientId;
    @Value("${google.oauth.client-secret}")
    private String clientSecret;
    @Value("${google.oauth.redirect-uri}")
    private String redirectUri;

    @Bean
    public JsonFactory jsonFactory() {
        return GsonFactory.getDefaultInstance();
    }

    @Bean
    public HttpTransport httpTransport() throws Exception {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    @Bean
    public Credentials googleCredentials() throws IOException {
        return GoogleCredentials.getApplicationDefault();
    }

    @Bean
    public GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow(HttpTransport httpTransport, JsonFactory jsonFactory) {

        return new GoogleAuthorizationCodeFlow.Builder(
                httpTransport,
                jsonFactory,
                clientId,
                clientSecret,
                SCOPES
        )
                .setAccessType("offline")
                .build();
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}