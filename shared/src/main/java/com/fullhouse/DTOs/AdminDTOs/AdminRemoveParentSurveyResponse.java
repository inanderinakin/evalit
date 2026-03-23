package com.fullhouse.DTOs.AdminDTOs;

public class AdminRemoveParentSurveyResponse {
    private boolean success;

    public AdminRemoveParentSurveyResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}