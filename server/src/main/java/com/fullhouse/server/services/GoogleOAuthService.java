package com.fullhouse.server.services;

import java.io.IOException;

public interface GoogleOAuthService {
    String buildAuthorizationUrl();

    void handleAuthorizationCode(java.lang.String code) throws IOException;

    String getFreshAccessToken() throws IOException;
}
