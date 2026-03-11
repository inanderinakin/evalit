package com.fullhouse.server.services;

import com.fullhouse.DTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.ParentSurveyCreateResponse;

public interface ParentSurveyCreateService {

    ParentSurveyCreateResponse createSurvey(ParentSurveyCreateRequest request);
}
