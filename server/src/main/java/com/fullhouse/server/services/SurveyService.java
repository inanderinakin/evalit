package com.fullhouse.server.services;


import java.io.IOException;
import java.security.GeneralSecurityException;

import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyResponse;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListRequest;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListResponse;

public interface SurveyService {
    SurveyApplyResponse applySurvey(SurveyApplyRequest request) throws GeneralSecurityException, IOException;

    SurveyListResponse getSurveyList(SurveyListRequest request);
}
