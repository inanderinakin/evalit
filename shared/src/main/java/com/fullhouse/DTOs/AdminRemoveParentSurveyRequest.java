package com.fullhouse.DTOs;

public class AdminRemoveParentSurveyRequest {
    private long parentSurveyId;

    public AdminRemoveParentSurveyRequest() {
    }

    public AdminRemoveParentSurveyRequest(long parentSurveyId) {
        this.parentSurveyId = parentSurveyId;
    }

    public long getParentSurveyId() {
        return parentSurveyId;
    }

    public void setParentSurveyId(long parentSurveyId) {
        this.parentSurveyId = parentSurveyId;
    }
}