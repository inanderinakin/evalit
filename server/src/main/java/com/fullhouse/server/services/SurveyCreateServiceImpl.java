package com.fullhouse.server.services;

import com.fullhouse.DTOs.SurveyCreateRequest;
import com.fullhouse.DTOs.SurveyCreateResponse;
import org.springframework.stereotype.Service;

@Service
public class SurveyCreateServiceImpl implements SurveyCreateService{

    @Override
    public SurveyCreateResponse createSurvey(SurveyCreateRequest request) {
        return new SurveyCreateResponse(request.getQuestions().get(3));
    }

}
