package com.fullhouse.DTOs.BusinessDTOs;

public class BusinessGetListByOwnerRequest {
    private String ownerGoogleSub;

    public BusinessGetListByOwnerRequest() {
    }

    public BusinessGetListByOwnerRequest(String ownerGoogleSub) {
        this.ownerGoogleSub = ownerGoogleSub;
    }

    public String getOwnerGoogleSub() {
        return ownerGoogleSub;
    }

    public void setOwnerGoogleSub(String ownerGoogleSub) {
        this.ownerGoogleSub = ownerGoogleSub;
    }
}
