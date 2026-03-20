package com.fullhouse.DTOs;

public class UserGetRequest {
    private long userId;

    public UserGetRequest() {
    }

    public UserGetRequest(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
