package com.fullhouse.server.services;


import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyResponse;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListRequest;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * The interface Survey service.
 */
public interface SurveyService {
    /**
     * Apply survey survey apply response.
     *
     * @param request the request
     * @return the survey apply response
     * @throws GeneralSecurityException the general security exception
     * @throws IOException              the ıo exception
     */
    SurveyApplyResponse applySurvey(SurveyApplyRequest request) throws GeneralSecurityException, IOException;

    /**
     * Gets survey list.
     *
     * @param request the request
     * @return the survey list
     */
    SurveyListResponse getSurveyList(SurveyListRequest request);

    /**
     * Update surveys based on the response.
     *
     * @param formID the form ıd
     */
    void updateSurveysBasedOnTheResponse(String formID);
}
