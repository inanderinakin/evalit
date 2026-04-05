package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.SurveyDTOs.SurveyListRequest;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListResponse;
import com.fullhouse.server.services.SurveyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Survey get list controller.
 */
@RestController
@RequestMapping("/survey/getlist")
public class SurveyGetListController {

    private final SurveyService surveyService;

    /**
     * Instantiates a new Survey get list controller.
     *
     * @param surveyService the survey service
     */
    public SurveyGetListController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    /**
     * Gets survey list.
     *
     * @param request the request
     * @return the survey list
     */
    @GetMapping
    public SurveyListResponse getSurveyList(SurveyListRequest request) {
        return surveyService.getSurveyList(request);
    }
}
