package com.fullhouse.DTOs.AdminDTOs;

public class AdminBanUserRequest {
    private String userGoogleSub;

    public AdminBanUserRequest() {
    }

    public AdminBanUserRequest(String userGoogleSub) {
        this.userGoogleSub = userGoogleSub;
    }

    public String getUserGoogleSub() {
        return userGoogleSub;
    }

    public void setUserGoogleSub(String userGoogleSub) {
        this.userGoogleSub = userGoogleSub;
    }

}