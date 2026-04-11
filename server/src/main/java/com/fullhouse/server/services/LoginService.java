package com.fullhouse.server.services;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.fullhouse.DTOs.LoginDTOs.LoginSuccessResponse;

/**
 * The interface Login service.
 */
public interface LoginService {
    /**
     * Register login login success response.
     *
     * @param user the user
     * @return the login success response
     */
    LoginSuccessResponse registerLogin(OAuth2User user);

}