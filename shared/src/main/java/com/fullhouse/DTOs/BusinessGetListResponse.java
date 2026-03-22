package com.fullhouse.DTOs;

import java.util.List;

public class BusinessGetListResponse {
    private List<BusinessInListDTO> businessInListDTOList;

    public BusinessGetListResponse() {
    }

    public BusinessGetListResponse(List<BusinessInListDTO> businessInListDTOList) {
        this.businessInListDTOList = businessInListDTOList;
    }

    public List<BusinessInListDTO> getBusinessInListDTOList() {
        return businessInListDTOList;
    }

    public void setBusinessInListDTOList(List<BusinessInListDTO> businessInListDTOList) {
        this.businessInListDTOList = businessInListDTOList;
    }
}
