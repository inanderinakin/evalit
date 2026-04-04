package com.fullhouse.DTOs.BusinessDTOs;

/**
 * The type Claim business start response.
 */
public class ClaimBusinessStartResponse {
    private boolean success;
    private String message;

    /**
     * Instantiates a new Claim business start response.
     */
    public ClaimBusinessStartResponse() {}

    /**
     * Instantiates a new Claim business start response.
     *
     * @param success the success
     * @param message the message
     */
    public ClaimBusinessStartResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
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
}