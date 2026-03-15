package com.fullhouse.DTOs;

import java.util.List;

// TODO: not complete
public class SurveyApplyRequest {

    private long facilityID;
    private List<String> questions;
    private String title;
    private String parentSurveyId;

    public long getFacilityID() {
        return facilityID;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public String getTitle() { return title; }

    public String getParentSurveyId() { return parentSurveyId; }
}
