package com.fullhouse.server.services;

import com.fullhouse.DTOs.LoginSuccessResponse;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface LoginService {
    LoginSuccessResponse registerLogin(OAuth2User user);

    LoginSuccessResponse getLastLogin();
}
