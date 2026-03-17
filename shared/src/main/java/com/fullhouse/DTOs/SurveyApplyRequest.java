package com.fullhouse.DTOs;

// TODO: not complete
public class SurveyApplyRequest {

    private long businessId;
    private String title;
    private Long parentSurveyId;

    public long getBusinessId() { return businessId;}

    public String getTitle() { return title; }

    public Long getParentSurveyId() { return parentSurveyId; }
}
