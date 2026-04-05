package com.fullhouse.DTOs.AdminDTOs;

/**
 * The type Admin ban user response.
 */
public class AdminBanUserResponse {
    private boolean success;

    /**
     * Instantiates a new Admin ban user response.
     *
     * @param success the success
     */
    public AdminBanUserResponse(boolean success) {
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