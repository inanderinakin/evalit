package com.fullhouse.server.services;


import com.fullhouse.DTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyApplyResponse;
import com.fullhouse.DTOs.SurveyListRequest;
import com.fullhouse.DTOs.SurveyListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface SurveyService {
    SurveyApplyResponse applySurvey(SurveyApplyRequest request) throws GeneralSecurityException, IOException;

    SurveyListResponse getSurveyList(SurveyListRequest request);
}
