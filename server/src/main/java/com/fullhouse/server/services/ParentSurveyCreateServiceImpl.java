package com.fullhouse.server.services;

import com.fullhouse.DTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.ParentSurveyCreateResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentSurveyCreateServiceImpl implements ParentSurveyCreateService{


    @Override
    public ParentSurveyCreateResponse createParentSurvey(ParentSurveyCreateRequest request) {
        List<String> questions = request.getQuestions();

        // TODO: Please create a new ParentSurvey and add it to the database.
        //  Also, the ParentSurvey entity must maintain a question set too.

        return new ParentSurveyCreateResponse();
    }
}
