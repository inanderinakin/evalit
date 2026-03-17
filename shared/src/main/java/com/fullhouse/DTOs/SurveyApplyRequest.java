package com.fullhouse.DTOs;

import java.util.List;

// TODO: not complete
public class SurveyApplyRequest {

    private long businessId;
    private String title;
    private String parentSurveyId;

    public long getBusinessId() { return businessId;}

    public String getTitle() { return title; }

    public String getParentSurveyId() { return parentSurveyId; }
}
