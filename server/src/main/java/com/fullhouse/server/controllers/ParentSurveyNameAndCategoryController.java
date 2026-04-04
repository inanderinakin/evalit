package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyMarketPlaceRequest;
import com.fullhouse.server.services.ParentSurveyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Parent survey name and category controller.
 */
@RestController
@RequestMapping("/parent-survey/getlist/name-category-search")
public class ParentSurveyNameAndCategoryController {

    private final ParentSurveyService parentSurveyService;

    /**
     * Instantiates a new Parent survey name and category controller.
     *
     * @param parentSurveyService the parent survey service
     */
    public ParentSurveyNameAndCategoryController(ParentSurveyService parentSurveyService) {
        this.parentSurveyService = parentSurveyService;
    }

    /**
     * Gets parent surveys of market place.
     *
     * @param request the request
     * @return the parent surveys of market place
     */
    @PostMapping
    public ParentSurveyListResponse getParentSurveysOfMarketPlace(@RequestBody ParentSurveyMarketPlaceRequest request) {
        return parentSurveyService.getParentSurveysOfMarketplace(request);
    }
}
