package com.fullhouse.DTOs.BusinessDTOs;

/**
 * For the endpoint that receives the id
 * of a ParentSurvey and returns the list
 * of Businesses that are evaluated in
 * the given ParentSurvey
 */
public class BusinessGetListBySurveyRequest {
    private long id;

    public BusinessGetListBySurveyRequest() {}

    public BusinessGetListBySurveyRequest(long id) {
        this.id = id;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }
}
