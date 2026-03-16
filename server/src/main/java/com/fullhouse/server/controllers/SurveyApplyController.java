package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyApplyResponse;
import com.fullhouse.server.services.SurveyService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/survey/apply")
public class SurveyApplyController {

    private final SurveyService surveyService;

    public SurveyApplyController(SurveyService surveyCreateService) {
        this.surveyService = surveyCreateService;
    }

    @PostMapping
    public SurveyApplyResponse applySurvey(@RequestBody SurveyApplyRequest request) {
        try {
            return surveyService.applySurvey(request);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
