package com.fullhouse.DTOs.BusinessDTOs;

import java.util.List;

/**
 * For the endpoint that receives a category and city
 * and returns the list of Businesses that are in that
 * city and have at least 1 Survey in the specified category.
 * The Businesses are sorted by their average scores.
 */
public class BusinessGetListByCityCategoryResponse {
    private List<BusinessInListDTO> businessInListDTOList;

    /**
     * Instantiates a new Business get list by city category response.
     */
    public BusinessGetListByCityCategoryResponse() {
    }

    /**
     * Instantiates a new Business get list by city category response.
     *
     * @param businessInListDTOList the business ın list dto list
     */
    public BusinessGetListByCityCategoryResponse(List<BusinessInListDTO> businessInListDTOList) {
        this.businessInListDTOList = businessInListDTOList;
    }

    /**
     * Gets business ın list dto list.
     *
     * @return the business ın list dto list
     */
    public List<BusinessInListDTO> getBusinessInListDTOList() {
        return businessInListDTOList;
    }

    /**
     * Sets business ın list dto list.
     *
     * @param businessInListDTOList the business ın list dto list
     */
    public void setBusinessInListDTOList(List<BusinessInListDTO> businessInListDTOList) {
        this.businessInListDTOList = businessInListDTOList;
    }
}
