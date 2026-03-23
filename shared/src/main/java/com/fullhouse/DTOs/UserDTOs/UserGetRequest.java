package com.fullhouse.DTOs.UserDTOs;

public class UserGetRequest {
    private String userGoogleSub;

    public UserGetRequest() {
    }

    public UserGetRequest(String userGoogleSub) {
        this.userGoogleSub = userGoogleSub;
    }

    public String getUserGoogleSub() {
        return userGoogleSub;
    }

    public void setUserGoogleSub(String userGoogleSub) {
        this.userGoogleSub = userGoogleSub;
    }
}
