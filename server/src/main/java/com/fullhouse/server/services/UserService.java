package com.fullhouse.server.services;

import com.fullhouse.DTOs.UserDTOs.UserGetRequest;
import com.fullhouse.DTOs.UserDTOs.UserGetResponse;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Gets user.
     *
     * @param request the request
     * @return the user
     */
    UserGetResponse getUser(UserGetRequest request);
}