package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessDTOs.*;

public interface BusinessService {
    BusinessGetListByNameResponse getBusinessesByName(BusinessGetListByNameRequest request);
    BusinessGetListByCityCategoryResponse getBusinessesByCategoryAndCity(BusinessGetListByCityCategoryRequest request);
    BusinessGetListBySurveyResponse getBusinessesBySurvey(BusinessGetListBySurveyRequest request);
    void saveLogo(Long businessId, byte[] logoBytes);
    void updateAverageScoreBasedOnTheResponse(String formId);
}