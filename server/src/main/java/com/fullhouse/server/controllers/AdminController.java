package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.AdminDTOs.AdminBanUserRequest;
import com.fullhouse.DTOs.AdminDTOs.AdminBanUserResponse;
import com.fullhouse.DTOs.AdminDTOs.AdminRemoveParentSurveyRequest;
import com.fullhouse.DTOs.AdminDTOs.AdminRemoveParentSurveyResponse;
import com.fullhouse.server.services.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Admin controller.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    /**
     * Instantiates a new Admin controller.
     *
     * @param adminService the admin service
     */
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Ban user admin ban user response.
     *
     * @param request the request
     * @return the admin ban user response
     */
    @PostMapping("/ban-user")
    public AdminBanUserResponse banUser(@RequestBody AdminBanUserRequest request) {
        return adminService.banUser(request);
    }

    /**
     * Remove parent survey admin remove parent survey response.
     *
     * @param request the request
     * @return the admin remove parent survey response
     */
    @PostMapping("/remove-parent-survey")
    public AdminRemoveParentSurveyResponse removeParentSurvey(@RequestBody AdminRemoveParentSurveyRequest request) {
        return adminService.removeParentSurvey(request);
    }
}