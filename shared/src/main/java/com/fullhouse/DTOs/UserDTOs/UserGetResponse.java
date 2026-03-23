package com.fullhouse.DTOs.UserDTOs;

public class UserGetResponse {
    private String googleSub;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean isBanned;
    private boolean isAdmin;
    private boolean isBusinessOwner;

    public UserGetResponse(String googleSub, String name, String email, String phoneNumber,
                           boolean isBanned, boolean isAdmin, boolean isBusinessOwner) {
        this.googleSub = googleSub;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isBanned = isBanned;
        this.isAdmin = isAdmin;
        this.isBusinessOwner = isBusinessOwner;
    }

    public String getGoogleSub() {
        return googleSub;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isBusinessOwner() {
        return isBusinessOwner;
    }
}