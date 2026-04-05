package com.fullhouse.DTOs.ParentSurveyDTOs;

public class ParentSurveyReportRequest {
    private long parentSurveyId;
    private String report;

    public ParentSurveyReportRequest() {}

    public ParentSurveyReportRequest(long parentSurveyId, String report) {
        this.parentSurveyId = parentSurveyId;
        this.report = report;
    }

    public long getParentSurveyId() {
        return parentSurveyId;
    }

    public void setParentSurveyId(long parentSurveyId) {
        this.parentSurveyId = parentSurveyId;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
