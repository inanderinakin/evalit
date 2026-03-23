package com.fullhouse.server.services;

import com.fullhouse.DTOs.UserDTOs.UserGetRequest;
import com.fullhouse.DTOs.UserDTOs.UserGetResponse;

public interface UserService {
    UserGetResponse getUser(UserGetRequest request);
}