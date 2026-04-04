package com.fullhouse.DTOs.BusinessDTOs;

/**
 * The type Claim business verify response.
 */
public class ClaimBusinessVerifyResponse {
    private boolean success;
    private String message;
    private Long businessId;

    /**
     * Instantiates a new Claim business verify response.
     */
    public ClaimBusinessVerifyResponse() {}

    /**
     * Instantiates a new Claim business verify response.
     *
     * @param success the success
     * @param message the message
     */
    public ClaimBusinessVerifyResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Instantiates a new Claim business verify response.
     *
     * @param success    the success
     * @param message    the message
     * @param businessId the business ıd
     */
    public ClaimBusinessVerifyResponse(boolean success, String message, Long businessId) {
        this.success = success;
        this.message = message;
        this.businessId = businessId;
    }

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets business ıd.
     *
     * @return the business ıd
     */
    public Long getBusinessId() {
        return businessId;
    }

    /**
     * Sets business ıd.
     *
     * @param businessId the business ıd
     */
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
}