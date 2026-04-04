package com.fullhouse.DTOs.UserDTOs;

/**
 * The type User get request.
 */
public class UserGetRequest {
    private String userGoogleSub;

    /**
     * Instantiates a new User get request.
     */
    public UserGetRequest() {
    }

    /**
     * Instantiates a new User get request.
     *
     * @param userGoogleSub the user google sub
     */
    public UserGetRequest(String userGoogleSub) {
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
