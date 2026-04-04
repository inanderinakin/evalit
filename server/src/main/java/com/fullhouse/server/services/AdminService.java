package com.fullhouse.server.services;

import com.fullhouse.DTOs.AdminDTOs.AdminBanUserRequest;
import com.fullhouse.DTOs.AdminDTOs.AdminBanUserResponse;
import com.fullhouse.DTOs.AdminDTOs.AdminRemoveParentSurveyRequest;
import com.fullhouse.DTOs.AdminDTOs.AdminRemoveParentSurveyResponse;

/**
 * The interface Admin service.
 */
public interface AdminService {
    /**
     * Ban user admin ban user response.
     *
     * @param request the request
     * @return the admin ban user response
     */
    AdminBanUserResponse banUser(AdminBanUserRequest request);

    /**
     * Remove parent survey admin remove parent survey response.
     *
     * @param request the request
     * @return the admin remove parent survey response
     */
    AdminRemoveParentSurveyResponse removeParentSurvey(AdminRemoveParentSurveyRequest request);
}