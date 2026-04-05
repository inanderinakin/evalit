package com.fullhouse.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullhouse.server.services.ParentSurveyService;

@RestController
@RequestMapping("/parent-survey/report")
public class ParentSurveyReportController {
    private final ParentSurveyService parentsurveyService;

    public ParentSurveyReportController(ParentSurveyService service) {
        this.parentsurveyService = service;
    }
}
