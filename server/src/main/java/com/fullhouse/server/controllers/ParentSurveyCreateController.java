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

    private final ParentSurveyCreateService parentSurveyCreateService;

    public ParentSurveyCreateController(ParentSurveyCreateService parentSurveyCreateService) {
        this.parentSurveyCreateService = parentSurveyCreateService;
    }

    @PostMapping
    public ParentSurveyCreateResponse createParentSurvey(@RequestBody ParentSurveyCreateRequest request) {
        return parentSurveyCreateService.createParentSurvey(request);
    }
}
