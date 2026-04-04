package com.fullhouse.DTOs.ParentSurveyDTOs;

/**
 * The type Parent survey list request.
 */
public class ParentSurveyListRequest {
    private String userId;

    /**
     * Instantiates a new Parent survey list request.
     */
    public ParentSurveyListRequest() {}

    /**
     * Instantiates a new Parent survey list request.
     *
     * @param userId the user ıd
     */
    public ParentSurveyListRequest(String userId) {
        this.userId = userId;
    }

    /**
     * Gets user ıd.
     *
     * @return the user ıd
     */
    public String getUserId() { return userId; }

    /**
     * Sets user ıd.
     *
     * @param userId the user ıd
     */
    public void setUserId(String userId) { this.userId = userId; }
}
