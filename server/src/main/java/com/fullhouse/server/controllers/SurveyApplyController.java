package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyApplyResponse;
import com.fullhouse.server.services.SurveyApplyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/survey/apply")
public class SurveyApplyController {

    private final SurveyApplyService surveyCreateService;

    public SurveyApplyController(SurveyApplyService surveyCreateService) {
        this.surveyCreateService = surveyCreateService;
    }

    @PostMapping
    public SurveyApplyResponse applySurvey(@RequestBody SurveyApplyRequest request) {
        try {
            return surveyCreateService.applySurvey(request);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }


}
