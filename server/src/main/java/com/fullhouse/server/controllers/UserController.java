package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.UserDTOs.UserGetRequest;
import com.fullhouse.DTOs.UserDTOs.UserGetResponse;
import com.fullhouse.server.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserGetResponse getUser(UserGetRequest request) {
        return userService.getUser(request);
    }
}