package com.fullhouse.DTOs.LoginDTOs;

public class LoginSuccessResponse {
    private String googleSub;
    private String name;
    private String email;

    public LoginSuccessResponse() {
    }

    public LoginSuccessResponse(String googleSub, String name, String email) {
        this.googleSub = googleSub;
        this.name = name;
        this.email = email;
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
}
