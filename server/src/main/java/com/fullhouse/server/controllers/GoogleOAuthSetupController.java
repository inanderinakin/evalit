package com.fullhouse.server.controllers;

import com.fullhouse.server.services.GoogleOAuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;

/**
 * Controller for the Google OAuth system.
 */
@RestController
public class GoogleOAuthSetupController {

    private final GoogleOAuthService googleOAuthService;

    public GoogleOAuthSetupController(GoogleOAuthService googleOAuthService) {
        this.googleOAuthService = googleOAuthService;
    }

    /**
     * Listens to the given URL. When the user
     * accesses to the URL, the method prompts
     * the user to log in to a Google account.
     * If the Google account is permitted by the
     * server (currently on 15.03.2026 only
     * {@literal fullhouseevalit@gmail.com} is
     * permitted. Further settings can be done via
     * Google Cloud Console.), then the token is provided.
     * @param response
     * @throws IOException
     */
    @GetMapping("/google/oauth/start")
    public void startGoogleOAuth(HttpServletResponse response) throws IOException {
        String authorizationUrl = googleOAuthService.buildAuthorizationUrl();
        response.sendRedirect(authorizationUrl);
    }

    /**
     * Recevied the code that is going
     * to be used to exchange the access token
     * @param code
     * @return
     */
    @GetMapping("/google/oauth/callback")
    public ResponseEntity<String> handleGoogleCallback(@RequestParam("code") String code) {
        try {
            googleOAuthService.handleAuthorizationCode(code);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
