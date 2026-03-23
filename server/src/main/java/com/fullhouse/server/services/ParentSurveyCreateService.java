package com.fullhouse.server.services;

import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateResponse;

public interface ParentSurveyCreateService {

    ParentSurveyCreateResponse createParentSurvey(ParentSurveyCreateRequest request);
}
