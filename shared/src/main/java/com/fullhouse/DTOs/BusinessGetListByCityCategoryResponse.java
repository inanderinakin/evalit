package com.fullhouse.DTOs;

import java.util.List;

/**
 * For the endpoint that receives a category and city
 * and returns the list of Businesses that are in that
 * city and have at least 1 Survey in the specified category.
 * The Businesses are sorted by their average scores.
 */
public class BusinessGetListByCityCategoryResponse {
    private List<BusinessInListDTO> businessInListDTOList;

    public BusinessGetListByCityCategoryResponse() {
    }

    public BusinessGetListByCityCategoryResponse(List<BusinessInListDTO> businessInListDTOList) {
        this.businessInListDTOList = businessInListDTOList;
    }

    public List<BusinessInListDTO> getBusinessInListDTOList() {
        return businessInListDTOList;
    }

    public void setBusinessInListDTOList(List<BusinessInListDTO> businessInListDTOList) {
        this.businessInListDTOList = businessInListDTOList;
    }
}
