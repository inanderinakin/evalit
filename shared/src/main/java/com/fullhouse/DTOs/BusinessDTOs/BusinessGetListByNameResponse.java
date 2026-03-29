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

    public BusinessGetListByNameResponse() {
    }

    public BusinessGetListByNameResponse(List<BusinessInListDTO> businesses) {
        this.businesses = businesses;
    }

    public List<BusinessInListDTO> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<BusinessInListDTO> businesses) {
        this.businesses = businesses;
    }
}