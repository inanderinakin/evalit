package com.fullhouse.DTOs.BusinessDTOs;

import java.util.List;

/**
 * For the endpoint that receives the id
 * of a ParentSurvey and returns the list
 * of Businesses that are evaluated in
 * the given ParentSurvey
 */
public class BusinessGetListBySurveyResponse {
    private List<BusinessInListDTO> businesses;

    /**
     * Instantiates a new Business get list by survey response.
     */
    public BusinessGetListBySurveyResponse() {}

    /**
     * Instantiates a new Business get list by survey response.
     *
     * @param businesses the businesses
     */
    public BusinessGetListBySurveyResponse(List<BusinessInListDTO> businesses) {
        this.businesses = businesses;
    }

    /**
     * Gets businesses.
     *
     * @return the businesses
     */
    public List<BusinessInListDTO> getBusinesses() { return businesses; }

    /**
     * Sets businesses.
     *
     * @param businesses the businesses
     */
    public void setBusinesses(List<BusinessInListDTO> businesses) { this.businesses = businesses; }
}
