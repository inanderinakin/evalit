package com.fullhouse.server.services;

import java.io.IOException;

/**
 * The interface Google o auth service.
 */
public interface GoogleOAuthService {
    /**
     * Build authorization url string.
     *
     * @return the string
     */
    String buildAuthorizationUrl();

    /**
     * Handle authorization code.
     *
     * @param code the code
     * @throws IOException the ıo exception
     */
    void handleAuthorizationCode(java.lang.String code) throws IOException;

    /**
     * Gets fresh access token.
     *
     * @return the fresh access token
     * @throws IOException the ıo exception
     */
    String getFreshAccessToken() throws IOException;
}
