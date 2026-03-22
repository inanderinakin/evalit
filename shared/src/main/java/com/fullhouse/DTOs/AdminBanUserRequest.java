package com.fullhouse.DTOs;

public class AdminBanUserRequest {
    private long userId;

    public AdminBanUserRequest() {
    }

    public AdminBanUserRequest(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}