package com.fullhouse.server.services;

import com.fullhouse.DTOs.ParentSurveyDTOs.*;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateResponse;

public interface ParentSurveyService {

    ParentSurveyCreateResponse createParentSurvey(ParentSurveyCreateRequest request);

    ParentSurveyListResponse getParentSurveysOfUser(ParentSurveyListRequest request);

    ParentSurveyListResponse getParentSurveysOfMarketplace(ParentSurveyMarketPlaceRequest request);

    ParentSurveySingularQuestionsResponse getQuestionsOfParentSurvey(ParentSurveySingularQuestionsRequest request);

    ParentSurveyReportedResponse getReportedParentSurveys(Integer minReportCount);

    ParentSurveyReportResponse reportParentSurvey(ParentSurveyReportRequest request);
}
