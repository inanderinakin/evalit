package com.fullhouse.server.services;


import com.fullhouse.DTOs.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface SurveyService {
    SurveyApplyResponse applySurvey(SurveyApplyRequest request) throws GeneralSecurityException, IOException;
    SurveyListResponse getSurveyList(SurveyListRequest request);
}
