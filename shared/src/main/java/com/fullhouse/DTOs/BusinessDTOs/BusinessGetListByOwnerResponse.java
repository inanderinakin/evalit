package com.fullhouse.DTOs.BusinessDTOs;

import java.util.List;

/**
 * The type Business get list by owner response.
 */
public class BusinessGetListByOwnerResponse {
    private List<BusinessInListDTO> businesses;

    /**
     * Instantiates a new Business get list by owner response.
     */
    public BusinessGetListByOwnerResponse() {
    }

    /**
     * Instantiates a new Business get list by owner response.
     *
     * @param businesses the businesses
     */
    public BusinessGetListByOwnerResponse(List<BusinessInListDTO> businesses) {
        this.businesses = businesses;
    }

    /**
     * Gets businesses.
     *
     * @return the businesses
     */
    public List<BusinessInListDTO> getBusinesses() {
        return businesses;
    }

    /**
     * Sets businesses.
     *
     * @param businesses the businesses
     */
    public void setBusinesses(List<BusinessInListDTO> businesses) {
        this.businesses = businesses;
    }
}
