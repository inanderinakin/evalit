package com.fullhouse.server.services;

import com.fullhouse.DTOs.AdminDTOs.AdminBanUserRequest;
import com.fullhouse.DTOs.AdminDTOs.AdminBanUserResponse;
import com.fullhouse.DTOs.AdminDTOs.AdminRemoveParentSurveyRequest;
import com.fullhouse.DTOs.AdminDTOs.AdminRemoveParentSurveyResponse;

public interface AdminService {
    AdminBanUserResponse banUser(AdminBanUserRequest request);
    AdminRemoveParentSurveyResponse removeParentSurvey(AdminRemoveParentSurveyRequest request);
}