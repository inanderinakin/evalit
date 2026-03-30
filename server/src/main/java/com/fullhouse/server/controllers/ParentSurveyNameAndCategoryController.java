package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyMarketPlaceRequest;
import com.fullhouse.server.services.ParentSurveyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parent-survey/getlist/name-category-search")
public class ParentSurveyNameAndCategoryController {

    private final ParentSurveyService parentSurveyService;

    public ParentSurveyNameAndCategoryController(ParentSurveyService parentSurveyService) {
        this.parentSurveyService = parentSurveyService;
    }

    public ParentSurveyListResponse getParentSurveysOfMarketPlace(ParentSurveyMarketPlaceRequest request) {
        return parentSurveyService.getParentSurveysOfMarketplace(request);
    }
}
