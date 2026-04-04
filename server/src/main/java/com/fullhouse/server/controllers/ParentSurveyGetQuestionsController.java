package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsResponse;
import com.fullhouse.server.services.ParentSurveyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Parent survey get questions controller.
 */
@RestController
@RequestMapping("/parent-survey/get-singular")
public class ParentSurveyGetQuestionsController {
    private final ParentSurveyService parentSurveyService;

    /**
     * Instantiates a new Parent survey get questions controller.
     *
     * @param parentSurveyService the parent survey service
     */
    public ParentSurveyGetQuestionsController(ParentSurveyService parentSurveyService) {
        this.parentSurveyService = parentSurveyService;
    }

    /**
     * Gets questions of parent survey.
     *
     * @param request the request
     * @return the questions of parent survey
     */
    @PostMapping
    public ParentSurveySingularQuestionsResponse getQuestionsOfParentSurvey(@RequestBody ParentSurveySingularQuestionsRequest request) {
        return parentSurveyService.getQuestionsOfParentSurvey(request);
    }
}
