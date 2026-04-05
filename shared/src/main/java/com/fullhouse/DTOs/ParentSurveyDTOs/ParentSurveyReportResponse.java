package com.fullhouse.DTOs.ParentSurveyDTOs;

public class ParentSurveyReportResponse {
    private boolean success;

    public ParentSurveyReportResponse() {}

    public ParentSurveyReportResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
