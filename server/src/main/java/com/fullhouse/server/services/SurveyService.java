package com.fullhouse.server.services;


import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyResponse;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListRequest;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface SurveyService {
    SurveyApplyResponse applySurvey(SurveyApplyRequest request) throws GeneralSecurityException, IOException;

    SurveyListResponse getSurveyList(SurveyListRequest request);

    void updateSurveysBasedOnTheResponse(String formID);
}
