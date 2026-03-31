package com.fullhouse.DTOs.BusinessDTOs;

public class ClaimBusinessStartRequest {
    private String googleSub;
    private String businessName;
    private String businessEmail;
    private String address;
    private String phoneNumber;
    private String city;
    private String imageURL;

    public ClaimBusinessStartRequest() {
    }

    public ClaimBusinessStartRequest(String googleSub, String businessName, String businessEmail,
                                     String address, String phoneNumber, String city) {
        this.googleSub = googleSub;
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    public String getGoogleSub() {
        return googleSub;
    }

    public void setGoogleSub(String googleSub) {
        this.googleSub = googleSub;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setimageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}