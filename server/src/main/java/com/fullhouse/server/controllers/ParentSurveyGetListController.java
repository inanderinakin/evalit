package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyReportedResponse;
import com.fullhouse.server.services.ParentSurveyService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ParentSurveyListResponse getParentSurveyOfUser(@RequestBody ParentSurveyListRequest request) {
        return parentSurveyService.getParentSurveysOfUser(request);
    }

    @PostMapping("/reported")
    public ParentSurveyReportedResponse getReportedParentSurveys(@RequestParam Integer minReportCount) {
        return parentSurveyService.getReportedParentSurveys(minReportCount);
    }
}
