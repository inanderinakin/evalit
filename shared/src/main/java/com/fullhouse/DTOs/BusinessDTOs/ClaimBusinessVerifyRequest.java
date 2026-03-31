package com.fullhouse.DTOs.BusinessDTOs;

public class ClaimBusinessVerifyRequest {
    private String googleSub;
    private String businessEmail;
    private String verificationCode;

    public ClaimBusinessVerifyRequest() {
    }

    public ClaimBusinessVerifyRequest(String googleSub, String businessEmail, String verificationCode) {
        this.googleSub = googleSub;
        this.businessEmail = businessEmail;
        this.verificationCode = verificationCode;
    }

    public String getGoogleSub() {
        return googleSub;
    }

    public void setGoogleSub(String googleSub) {
        this.googleSub = googleSub;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
