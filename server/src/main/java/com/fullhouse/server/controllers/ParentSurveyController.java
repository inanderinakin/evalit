package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyMarketPlaceRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyReportedResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyReportRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyReportResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsResponse;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateResponse;
import com.fullhouse.server.services.ParentSurveyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parent-survey")
public class ParentSurveyController {

    private final ParentSurveyService parentSurveyService;

    public ParentSurveyController(ParentSurveyService parentSurveyService) {
        this.parentSurveyService = parentSurveyService;
    }

    @PostMapping("/create")
    public ParentSurveyCreateResponse createParentSurvey(@RequestBody ParentSurveyCreateRequest request) {
        return parentSurveyService.createParentSurvey(request);
    }

    @PostMapping("/get-list")
    public ParentSurveyListResponse getParentSurveyOfUser(@RequestBody ParentSurveyListRequest request) {
        return parentSurveyService.getParentSurveysOfUser(request);
    }

    @PostMapping("/get-list/reported")
    public ParentSurveyReportedResponse getReportedParentSurveys(@RequestParam("minReportCount") Integer minReportCount) {
        return parentSurveyService.getReportedParentSurveys(minReportCount);
    }

    @PostMapping("/get-singular")
    public ParentSurveySingularQuestionsResponse getQuestionsOfParentSurvey(@RequestBody ParentSurveySingularQuestionsRequest request) {
        return parentSurveyService.getQuestionsOfParentSurvey(request);
    }

    @PostMapping("/getlist/name-category-search")
    public ParentSurveyListResponse getParentSurveysOfMarketPlace(@RequestBody ParentSurveyMarketPlaceRequest request) {
        return parentSurveyService.getParentSurveysOfMarketplace(request);
    }

    @PostMapping("/report")
    public ParentSurveyReportResponse reportParentSurvey(@RequestBody ParentSurveyReportRequest request) {
        return parentSurveyService.reportParentSurvey(request);
    }
}
