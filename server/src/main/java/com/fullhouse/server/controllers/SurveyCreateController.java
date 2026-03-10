package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.SurveyCreateRequest;
import com.fullhouse.DTOs.SurveyCreateResponse;
import com.fullhouse.server.services.SurveyCreateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey/create")
public class SurveyCreateController {

    private final SurveyCreateService surveyCreateService;

    public SurveyCreateController(SurveyCreateService surveyCreateService) {
        this.surveyCreateService = surveyCreateService;
    }

    @PostMapping
    public SurveyCreateResponse createSurvey(@RequestBody SurveyCreateRequest request) {
        return surveyCreateService.createSurvey(request);
    }


}
