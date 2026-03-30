package com.fullhouse.server.services;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyMarketPlaceRequest;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateResponse;

public interface ParentSurveyService {

    ParentSurveyCreateResponse createParentSurvey(ParentSurveyCreateRequest request);

    ParentSurveyListResponse getParentSurveysOfUser(ParentSurveyListRequest request);

    ParentSurveyListResponse getParentSurveysOfMarketplace(ParentSurveyMarketPlaceRequest request);
}
