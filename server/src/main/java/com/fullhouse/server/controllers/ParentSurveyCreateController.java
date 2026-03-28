package com.fullhouse.server.controllers;


import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateResponse;
import com.fullhouse.server.services.ParentSurveyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parent-survey/create")
public class ParentSurveyCreateController {

    private final ParentSurveyService parentSurveyService;

    public ParentSurveyCreateController(ParentSurveyService parentSurveyService) {
        this.parentSurveyService = parentSurveyService;
    }

    @PostMapping
    public ParentSurveyCreateResponse createParentSurvey(@RequestBody ParentSurveyCreateRequest request) {
        return parentSurveyService.createParentSurvey(request);
    }
}
