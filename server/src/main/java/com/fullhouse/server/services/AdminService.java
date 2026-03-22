package com.fullhouse.server.services;

import com.fullhouse.DTOs.AdminBanUserRequest;
import com.fullhouse.DTOs.AdminBanUserResponse;
import com.fullhouse.DTOs.AdminRemoveParentSurveyRequest;
import com.fullhouse.DTOs.AdminRemoveParentSurveyResponse;

public interface AdminService {
    AdminBanUserResponse banUser(AdminBanUserRequest request);
    AdminRemoveParentSurveyResponse removeParentSurvey(AdminRemoveParentSurveyRequest request);
}