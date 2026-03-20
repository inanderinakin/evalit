package com.fullhouse.server.services;

import com.fullhouse.DTOs.UserGetRequest;
import com.fullhouse.DTOs.UserGetResponse;

public interface UserService {
    UserGetResponse getUser(UserGetRequest request);
}