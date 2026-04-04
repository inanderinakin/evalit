package com.fullhouse.server.services;

import com.fullhouse.DTOs.ParentSurveyDTOs.*;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateResponse;

/**
 * The interface Parent survey service.
 */
public interface ParentSurveyService {

    /**
     * Create parent survey parent survey create response.
     *
     * @param request the request
     * @return the parent survey create response
     */
    ParentSurveyCreateResponse createParentSurvey(ParentSurveyCreateRequest request);

    /**
     * Gets parent surveys of user.
     *
     * @param request the request
     * @return the parent surveys of user
     */
    ParentSurveyListResponse getParentSurveysOfUser(ParentSurveyListRequest request);

    /**
     * Gets parent surveys of marketplace.
     *
     * @param request the request
     * @return the parent surveys of marketplace
     */
    ParentSurveyListResponse getParentSurveysOfMarketplace(ParentSurveyMarketPlaceRequest request);

    /**
     * Gets questions of parent survey.
     *
     * @param request the request
     * @return the questions of parent survey
     */
    ParentSurveySingularQuestionsResponse getQuestionsOfParentSurvey(ParentSurveySingularQuestionsRequest request);
}
