package com.fullhouse.DTOs.AdminDTOs;

public class AdminRemoveBusinessResponse {
    private boolean success;

    public AdminRemoveBusinessResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
