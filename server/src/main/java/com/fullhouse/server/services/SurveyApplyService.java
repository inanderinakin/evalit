package com.fullhouse.server.services;

import com.fullhouse.DTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.ParentSurveyCreateResponse;
import com.fullhouse.DTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyApplyResponse;

public interface SurveyApplyService {
    SurveyApplyResponse applySurvey(SurveyApplyRequest request);

}
