package com.fullhouse.DTOs.ParentSurveyDTOs;

public class ParentSurveyListRequest {
    private String userId;

    public ParentSurveyListRequest(String userId) {
        this.userId = userId;
    }

    public String userId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }
}
