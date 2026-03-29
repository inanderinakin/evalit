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

    public BusinessGetListBySurveyResponse(List<BusinessInListDTO> businesses) {
        this.businesses = businesses;
    }

    public List<BusinessInListDTO> businesses() { return businesses; }

    public void setBusinesses(List<BusinessInListDTO> businesses) { this.businesses = businesses; }
}
