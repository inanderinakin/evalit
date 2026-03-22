package com.fullhouse.DTOs;

public class AdminBanUserResponse {
    private boolean success;

    public AdminBanUserResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}