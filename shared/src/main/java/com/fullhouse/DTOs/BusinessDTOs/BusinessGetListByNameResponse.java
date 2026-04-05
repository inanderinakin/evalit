package com.fullhouse.DTOs.BusinessDTOs;

import java.util.List;

/**
 * For the endpoint that receives a name
 * and returns the list of Businesses that
 * contain the String given in the request
 * in their names.
 */
public class BusinessGetListByNameResponse {
    private List<BusinessInListDTO> businesses;

    /**
     * Instantiates a new Business get list by name response.
     */
    public BusinessGetListByNameResponse() {
    }

    /**
     * Instantiates a new Business get list by name response.
     *
     * @param businesses the businesses
     */
    public BusinessGetListByNameResponse(List<BusinessInListDTO> businesses) {
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