package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.AdminDTOs.*;
import com.fullhouse.server.services.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/ban-user")
    public AdminBanUserResponse banUser(@RequestBody AdminBanUserRequest request) {
        return adminService.banUser(request);
    }

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