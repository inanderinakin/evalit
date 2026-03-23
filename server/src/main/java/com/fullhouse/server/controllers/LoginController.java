package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.LoginDTOs.LoginSuccessResponse;
import com.fullhouse.server.services.LoginService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/loginSuccess")
    public LoginSuccessResponse loginSuccess(@AuthenticationPrincipal OAuth2User user) {
        return loginService.registerLogin(user);
    }

    @GetMapping("/loginSuccess/client")
    public LoginSuccessResponse loginSuccessClient() {
        return loginService.getLastLogin();
    }
}