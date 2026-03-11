package com.fullhouse.server.services;

import com.fullhouse.DTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.ParentSurveyCreateResponse;
import org.springframework.stereotype.Service;

@Service
public class ParentSurveyCreateServiceImpl implements ParentSurveyCreateService{


    @Override
    public ParentSurveyCreateResponse createSurvey(ParentSurveyCreateRequest request) {
        return new ParentSurveyCreateResponse("abc");
    }
}
