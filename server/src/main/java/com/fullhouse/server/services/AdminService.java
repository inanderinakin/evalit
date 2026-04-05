package com.fullhouse.server.services;

import com.fullhouse.DTOs.AdminDTOs.*;

public interface AdminService {
    AdminBanUserResponse banUser(AdminBanUserRequest request);
    AdminRemoveParentSurveyResponse removeParentSurvey(AdminRemoveParentSurveyRequest request);
    AdminRemoveSurveyResponse removeSurvey(long id);
    AdminRemoveBusinessResponse removeBusiness(AdminRemoveBusinessRequest request);
}