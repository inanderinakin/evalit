package com.fullhouse.DTOs.AdminDTOs;

/**
 * The type Admin remove parent survey request.
 */
public class AdminRemoveParentSurveyRequest {
    private long parentSurveyId;

    /**
     * Instantiates a new Admin remove parent survey request.
     */
    public AdminRemoveParentSurveyRequest() {
    }

    /**
     * Instantiates a new Admin remove parent survey request.
     *
     * @param parentSurveyId the parent survey ıd
     */
    public AdminRemoveParentSurveyRequest(long parentSurveyId) {
        this.parentSurveyId = parentSurveyId;
    }

    /**
     * Gets parent survey ıd.
     *
     * @return the parent survey ıd
     */
    public long getParentSurveyId() {
        return parentSurveyId;
    }

    /**
     * Sets parent survey ıd.
     *
     * @param parentSurveyId the parent survey ıd
     */
    public void setParentSurveyId(long parentSurveyId) {
        this.parentSurveyId = parentSurveyId;
    }
}