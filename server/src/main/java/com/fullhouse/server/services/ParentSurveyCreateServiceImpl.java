package com.fullhouse.server.services;

import com.fullhouse.DTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.ParentSurveyCreateResponse;
import com.fullhouse.server.domain.ParentSurvey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentSurveyCreateServiceImpl implements ParentSurveyCreateService{


    @Override
    public ParentSurveyCreateResponse createParentSurvey(ParentSurveyCreateRequest request) {
        List<String> questions = request.getQuestions();

        // TODO:
        //  Please create a new ParentSurvey instance and
        //  save it to the database. The request specifies the
        //  name, creator User ID, and the question list. The
        //  remaining fields can be initialized as:
        //  popularity: 0
        //  childrenSurveys: empty
        //  id: randomly generated

        return new ParentSurveyCreateResponse();
    }
}
