package com.fullhouse.DTOs.BusinessDTOs;

import java.util.List;

public class BusinessGetListByOwnerResponse {
    private List<BusinessInListDTO> businesses;

    public BusinessGetListByOwnerResponse() {
    }

    public BusinessGetListByOwnerResponse(List<BusinessInListDTO> businesses) {
        this.businesses = businesses;
    }

    public List<BusinessInListDTO> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<BusinessInListDTO> businesses) {
        this.businesses = businesses;
    }
}
