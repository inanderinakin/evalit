package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.SurveyListRequest;
import com.fullhouse.DTOs.SurveyListResponse;
import com.fullhouse.server.services.SurveyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey/getlist")
public class SurveyGetListController {

    private final SurveyService surveyService;

    public SurveyGetListController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping
    public SurveyListResponse getSurveyList(SurveyListRequest request) {
        return surveyService.getSurveyList(request);
    }
}
