package com.fullhouse.DTOs.AdminDTOs;

/**
 * The type Admin remove parent survey response.
 */
public class AdminRemoveParentSurveyResponse {
    private boolean success;

    /**
     * Instantiates a new Admin remove parent survey response.
     *
     * @param success the success
     */
    public AdminRemoveParentSurveyResponse(boolean success) {
        this.success = success;
    }

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess() {
        return success;
    }
}