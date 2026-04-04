package com.fullhouse.DTOs.BusinessDTOs;

/**
 * The type Business get list by owner request.
 */
public class BusinessGetListByOwnerRequest {
    private String ownerGoogleSub;

    /**
     * Instantiates a new Business get list by owner request.
     */
    public BusinessGetListByOwnerRequest() {
    }

    /**
     * Instantiates a new Business get list by owner request.
     *
     * @param ownerGoogleSub the owner google sub
     */
    public BusinessGetListByOwnerRequest(String ownerGoogleSub) {
        this.ownerGoogleSub = ownerGoogleSub;
    }

    /**
     * Gets owner google sub.
     *
     * @return the owner google sub
     */
    public String getOwnerGoogleSub() {
        return ownerGoogleSub;
    }

    /**
     * Sets owner google sub.
     *
     * @param ownerGoogleSub the owner google sub
     */
    public void setOwnerGoogleSub(String ownerGoogleSub) {
        this.ownerGoogleSub = ownerGoogleSub;
    }
}
