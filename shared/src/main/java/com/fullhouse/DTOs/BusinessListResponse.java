package com.fullhouse.DTOs;

import java.util.List;

public class BusinessListResponse {
    private List<BusinessInListDTO> businesses;

    public BusinessListResponse(List<BusinessInListDTO> businesses) {
        this.businesses = businesses;
    }

    public List<BusinessInListDTO> getBusinesses() {
        return businesses;
    }
}