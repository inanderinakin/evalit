package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListResponse;
import com.fullhouse.server.services.ParentSurveyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller receives a User's id
 * and returns the list of ParentSurveys
 * that are created by that User.
 */

@RestController
@RequestMapping("parent-survey/get-list")
public class ParentSurveyGetListController {
    private ParentSurveyService parentSurveyService;

    public ParentSurveyGetListController(ParentSurveyService parentSurveyService) {
        this.parentSurveyService = parentSurveyService;
    }

    @GetMapping
    public ParentSurveyListResponse getParentSurveyOfUser(@RequestBody ParentSurveyListRequest request) {
        return parentSurveyService.getParentSurveysOfUser(request);
    }
}
