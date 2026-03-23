package com.fullhouse.server.services;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.fullhouse.DTOs.LoginDTOs.LoginSuccessResponse;

public interface LoginService {
    LoginSuccessResponse registerLogin(OAuth2User user);

    LoginSuccessResponse getLastLogin();
}
