package com.fullhouse.server.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Implements {@link GoogleOAuthService}
 * This service's main aim is to communicate
 * with Google to get an access token, which
 * enables the server to use the Forms API.
 *
 */

@Service
public class GoogleOAuthServiceImpl implements GoogleOAuthService{

    private final GoogleAuthorizationCodeFlow flow;
    private final RefreshTokenStore refreshTokenStore;
    private final HttpTransport httpTransport;
    private final JsonFactory jsonFactory;

    // These parameters are provided from application.properties file
    @Value("${google.oauth.redirect-uri}")
    private String redirectUri;

    @Value("${google.oauth.client-id}")
    private String clientId;

    @Value("${google.oauth.client-secret}")
    private String clientSecret;

    public GoogleOAuthServiceImpl(GoogleAuthorizationCodeFlow flow,
                              RefreshTokenStore refreshTokenStore,
                              HttpTransport httpTransport,
                              JsonFactory jsonFactory) {
        this.flow = flow;
        this.refreshTokenStore = refreshTokenStore;
        this.httpTransport = httpTransport;
        this.jsonFactory = jsonFactory;
    }


    /**
     * This method sets up the environment in which
     * the server is approved manually for once.
     * @return newAuthorizationUrl
     */
    @Override
    public String buildAuthorizationUrl() {
        return flow.newAuthorizationUrl()
                .setRedirectUri(redirectUri)
                .set("prompt", "consent")
                .build();
    }

    /**
     * This method handles the code that is received
     * from Google and exchanges it with a refresh
     * token. It also saves the refresh token locally
     * using {@link RefreshTokenStore}.
     * @param code
     */
    @Override
    public void handleAuthorizationCode(String code) throws IOException {
        System.out.println(code);
        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code)
                .setRedirectUri(redirectUri)
                .execute();

        String refreshToken = tokenResponse.getRefreshToken();

        refreshTokenStore.save(refreshToken);
    }

    /**
     * This method is used to generate an
     * access token using the local refresh token
     * @return the access token
     * @throws IOException
     */
    public String getFreshAccessToken() throws IOException {
        String refreshToken = refreshTokenStore.load();

        GoogleTokenResponse tokenResponse = new GoogleRefreshTokenRequest(
                httpTransport,
                jsonFactory,
                refreshToken,
                clientId,
                clientSecret
        ).execute();

        return tokenResponse.getAccessToken();
    }
}
