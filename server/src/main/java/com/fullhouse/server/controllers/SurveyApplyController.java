package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyResponse;
import com.fullhouse.server.services.SurveyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * The type Survey apply controller.
 */
@RestController
@RequestMapping("/survey/apply")
public class SurveyApplyController {

    private final SurveyService surveyService;

    /**
     * Instantiates a new Survey apply controller.
     *
     * @param surveyCreateService the survey create service
     */
    public SurveyApplyController(SurveyService surveyCreateService) {
        this.surveyService = surveyCreateService;
    }

    /**
     * Apply survey survey apply response.
     *
     * @param request the request
     * @return the survey apply response
     */
    @PostMapping
    public SurveyApplyResponse applySurvey(@RequestBody SurveyApplyRequest request) {
        try {
            return surveyService.applySurvey(request);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
