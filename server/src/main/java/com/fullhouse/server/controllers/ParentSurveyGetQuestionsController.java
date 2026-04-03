package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsResponse;
import com.fullhouse.server.services.ParentSurveyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parent-survey/get-singular")
public class ParentSurveyGetQuestionsController {
    private final ParentSurveyService parentSurveyService;

    public ParentSurveyGetQuestionsController(ParentSurveyService parentSurveyService) {
        this.parentSurveyService = parentSurveyService;
    }

    @PostMapping
    public ParentSurveySingularQuestionsResponse getQuestionsOfParentSurvey(@RequestBody ParentSurveySingularQuestionsRequest request) {
        return parentSurveyService.getQuestionsOfParentSurvey(request);
    }
}
