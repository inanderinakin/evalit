package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessDTOs.*;

/**
 * The interface Business service.
 */
public interface BusinessService {
    /**
     * Gets businesses by name.
     *
     * @param request the request
     * @return the businesses by name
     */
    BusinessGetListByNameResponse getBusinessesByName(BusinessGetListByNameRequest request);

    /**
     * Gets businesses by category and city.
     *
     * @param request the request
     * @return the businesses by category and city
     */
    BusinessGetListByCityCategoryResponse getBusinessesByCategoryAndCity(BusinessGetListByCityCategoryRequest request);

    /**
     * Gets businesses by survey.
     *
     * @param request the request
     * @return the businesses by survey
     */
    BusinessGetListBySurveyResponse getBusinessesBySurvey(BusinessGetListBySurveyRequest request);

    /**
     * Gets businesses by owner.
     *
     * @param request the request
     * @return the businesses by owner
     */
    BusinessGetListByOwnerResponse getBusinessesByOwner(BusinessGetListByOwnerRequest request);

    /**
     * Save logo.
     *
     * @param businessId the business ıd
     * @param logoBytes  the logo bytes
     */
    void saveLogo(Long businessId, byte[] logoBytes);

    /**
     * Update average score based on the response.
     *
     * @param formId the form ıd
     */
    void updateAverageScoreBasedOnTheResponse(String formId);
}