package com.fullhouse.DTOs.BusinessDTOs;

/**
 * The type Claim business verify request.
 */
public class ClaimBusinessVerifyRequest {
    private String googleSub;
    private String businessEmail;
    private String verificationCode;

    /**
     * Instantiates a new Claim business verify request.
     */
    public ClaimBusinessVerifyRequest() {
    }

    /**
     * Instantiates a new Claim business verify request.
     *
     * @param googleSub        the google sub
     * @param businessEmail    the business email
     * @param verificationCode the verification code
     */
    public ClaimBusinessVerifyRequest(String googleSub, String businessEmail, String verificationCode) {
        this.googleSub = googleSub;
        this.businessEmail = businessEmail;
        this.verificationCode = verificationCode;
    }

    /**
     * Gets google sub.
     *
     * @return the google sub
     */
    public String getGoogleSub() {
        return googleSub;
    }

    /**
     * Sets google sub.
     *
     * @param googleSub the google sub
     */
    public void setGoogleSub(String googleSub) {
        this.googleSub = googleSub;
    }

    /**
     * Gets business email.
     *
     * @return the business email
     */
    public String getBusinessEmail() {
        return businessEmail;
    }

    /**
     * Sets business email.
     *
     * @param businessEmail the business email
     */
    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    /**
     * Gets verification code.
     *
     * @return the verification code
     */
    public String getVerificationCode() {
        return verificationCode;
    }

    /**
     * Sets verification code.
     *
     * @param verificationCode the verification code
     */
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
