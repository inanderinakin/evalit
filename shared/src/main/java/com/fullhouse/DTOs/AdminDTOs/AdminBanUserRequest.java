package com.fullhouse.DTOs.AdminDTOs;

/**
 * The type Admin ban user request.
 */
public class AdminBanUserRequest {
    private String userGoogleSub;

    /**
     * Instantiates a new Admin ban user request.
     */
    public AdminBanUserRequest() {
    }

    /**
     * Instantiates a new Admin ban user request.
     *
     * @param userGoogleSub the user google sub
     */
    public AdminBanUserRequest(String userGoogleSub) {
        this.userGoogleSub = userGoogleSub;
    }

    /**
     * Gets user google sub.
     *
     * @return the user google sub
     */
    public String getUserGoogleSub() {
        return userGoogleSub;
    }

    /**
     * Sets user google sub.
     *
     * @param userGoogleSub the user google sub
     */
    public void setUserGoogleSub(String userGoogleSub) {
        this.userGoogleSub = userGoogleSub;
    }

}