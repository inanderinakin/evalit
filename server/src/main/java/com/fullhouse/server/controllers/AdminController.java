package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.AdminBanUserRequest;
import com.fullhouse.DTOs.AdminBanUserResponse;
import com.fullhouse.DTOs.AdminRemoveParentSurveyRequest;
import com.fullhouse.DTOs.AdminRemoveParentSurveyResponse;
import com.fullhouse.server.services.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}