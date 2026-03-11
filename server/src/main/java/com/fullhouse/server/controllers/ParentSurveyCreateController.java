package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.ParentSurveyCreateResponse;
import com.fullhouse.server.services.ParentSurveyCreateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey/create")
public class ParentSurveyCreateController {

    private final ParentSurveyCreateService surveyCreateService;

    public ParentSurveyCreateController(ParentSurveyCreateService surveyCreateService) {
        this.surveyCreateService = surveyCreateService;
    }

    @PostMapping
    public ParentSurveyCreateResponse createSurvey(@RequestBody ParentSurveyCreateRequest request) {
        return surveyCreateService.createSurvey(request);
    }


}
