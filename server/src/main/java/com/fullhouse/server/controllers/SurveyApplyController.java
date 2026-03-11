package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyApplyResponse;
import com.fullhouse.server.services.SurveyApplyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey/apply")
public class SurveyApplyController {

    private final SurveyApplyService surveyCreateService;

    public SurveyApplyController(SurveyApplyService surveyCreateService) {
        this.surveyCreateService = surveyCreateService;
    }

    @PostMapping
    public SurveyApplyResponse applySurvey(@RequestBody SurveyApplyRequest request) {
        return surveyCreateService.applySurvey(request);
    }


}
