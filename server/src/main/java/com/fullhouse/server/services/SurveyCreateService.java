package com.fullhouse.server.services;

import com.fullhouse.DTOs.SurveyCreateRequest;
import com.fullhouse.DTOs.SurveyCreateResponse;

public interface SurveyCreateService {
    SurveyCreateResponse createSurvey(SurveyCreateRequest request);

}
