package com.fullhouse.DTOs.ParentSurveyDTOs;

public class ParentSurveySingularQuestionsRequest {
    private long id;

    public ParentSurveySingularQuestionsRequest(long id) {
        this.id = id;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }
}
