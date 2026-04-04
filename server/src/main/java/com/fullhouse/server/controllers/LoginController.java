package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.LoginDTOs.LoginSuccessResponse;
import com.fullhouse.server.services.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Login controller.
 */
@RestController
public class LoginController {

    private final LoginService loginService;

    /**
     * Instantiates a new Login controller.
     *
     * @param loginService the login service
     */
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Login success login success response.
     *
     * @param user the user
     * @return the login success response
     */
    @GetMapping("/loginSuccess")
    public LoginSuccessResponse loginSuccess(@AuthenticationPrincipal OAuth2User user) {
        return loginService.registerLogin(user);
    }

    /**
     * Login success client login success response.
     *
     * @return the login success response
     */
    @GetMapping("/loginSuccess/client")
    public LoginSuccessResponse loginSuccessClient() {
        return loginService.getLastLogin();
    }

    /**
     * Logout client response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/logout/client")
    public ResponseEntity<String> logoutClient(HttpServletRequest request) {
        loginService.clearLastLogin();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Logged out");
    }
}