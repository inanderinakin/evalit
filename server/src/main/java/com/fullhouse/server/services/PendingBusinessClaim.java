package com.fullhouse.server.services;

public class PendingBusinessClaim {
    private String googleSub;
    private String businessName;
    private String businessEmail;
    private String imageURL;
    private String address;
    private String phoneNumber;
    private String city;
    private String verificationCode;
    private String logoString64;

    public PendingBusinessClaim(String googleSub, String businessName, String businessEmail, String address, String phoneNumber, String city, String imageURL, String verificationCode, String logoString64) {
        this.googleSub = googleSub;
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.imageURL = imageURL;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.verificationCode = verificationCode;
        this.logoString64 = logoString64;
    }

    public String getLogoString64() {
        return logoString64;
    }

    public String getGoogleSub() {
        return googleSub;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}