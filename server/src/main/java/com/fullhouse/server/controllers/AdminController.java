package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.AdminDTOs.*;
import com.fullhouse.server.services.AdminService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/remove-survey")
    public AdminRemoveSurveyResponse removeSurvey(@RequestParam("surveyId") long surveyId) {
        return adminService.removeSurvey(surveyId);
    }

    @PostMapping("/remove-business")
    public AdminRemoveBusinessResponse removeBusiness(@RequestBody AdminRemoveBusinessRequest request) {
        return adminService.removeBusiness(request);
    }
}