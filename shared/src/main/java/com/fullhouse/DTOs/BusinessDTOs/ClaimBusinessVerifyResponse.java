package com.fullhouse.DTOs.BusinessDTOs;

public class ClaimBusinessVerifyResponse {
    private boolean success;
    private String message;
    private Long businessId;

    public ClaimBusinessVerifyResponse() {}

    public ClaimBusinessVerifyResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ClaimBusinessVerifyResponse(boolean success, String message, Long businessId) {
        this.success = success;
        this.message = message;
        this.businessId = businessId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
}