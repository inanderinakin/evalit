package com.fullhouse.server.services;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.fullhouse.DTOs.LoginDTOs.LoginSuccessResponse;

/**
 * The interface Login service.
 */
public interface LoginService {
    LoginSuccessResponse registerLogin(OAuth2User user, String loginToken);

    LoginSuccessResponse getLogin(String loginToken);

    void clearLogin(String loginToken);
}