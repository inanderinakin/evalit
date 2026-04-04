package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.UserDTOs.UserGetRequest;
import com.fullhouse.DTOs.UserDTOs.UserGetResponse;
import com.fullhouse.server.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets user.
     *
     * @param request the request
     * @return the user
     */
    @GetMapping
    public UserGetResponse getUser(UserGetRequest request) {
        return userService.getUser(request);
    }
}