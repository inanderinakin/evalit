package com.fullhouse.DTOs.LoginDTOs;

public class LoginSuccessResponse {
    private String googleSub;
    private String name;
    private String email;
    private String profilePictureURL;
    private boolean isBusinessOwner;
    private boolean isAdmin;
    private boolean isBanned;
    private String phoneNumber;

    public LoginSuccessResponse() {
    }

    public LoginSuccessResponse(String googleSub, String name, String email, String profilePictureURL, boolean isBusinessOwner, boolean isAdmin, boolean isBanned, String phoneNumber) {
        this.googleSub = googleSub;
        this.name = name;
        this.email = email;
        this.profilePictureURL = profilePictureURL;
        this.isBusinessOwner = isBusinessOwner;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
        this.phoneNumber = phoneNumber;
    }

    public String getGoogleSub() {
        return googleSub;
    }

    public void setGoogleSub(String googleSub) {
        this.googleSub = googleSub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public boolean isBusinessOwner() {
        return isBusinessOwner;
    }

    public void setBusinessOwner(boolean isBusinessOwner) {
        this.isBusinessOwner = isBusinessOwner;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
