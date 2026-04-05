package com.fullhouse.DTOs.AdminDTOs;

public class AdminRemoveSurveyResponse {

    private boolean isSuccess;

    public AdminRemoveSurveyResponse() {}

    public AdminRemoveSurveyResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() { return isSuccess; }

    public void setSuccess(boolean success) { isSuccess = success; }
}
