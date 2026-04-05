package com.fullhouse.DTOs.AdminDTOs;

public class AdminRemoveBusinessRequest {
    private long businessId;

    public AdminRemoveBusinessRequest() {
    }

    public AdminRemoveBusinessRequest(long businessId) {
        this.businessId = businessId;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }
}
