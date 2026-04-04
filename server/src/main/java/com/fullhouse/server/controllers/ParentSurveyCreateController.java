package com.fullhouse.server.controllers;


import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateResponse;
import com.fullhouse.server.services.ParentSurveyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Parent survey create controller.
 */
@RestController
@RequestMapping("/parent-survey/create")
public class ParentSurveyCreateController {

    private final ParentSurveyService parentSurveyService;

    /**
     * Instantiates a new Parent survey create controller.
     *
     * @param parentSurveyService the parent survey service
     */
    public ParentSurveyCreateController(ParentSurveyService parentSurveyService) {
        this.parentSurveyService = parentSurveyService;
    }

    /**
     * Create parent survey parent survey create response.
     *
     * @param request the request
     * @return the parent survey create response
     */
    @PostMapping
    public ParentSurveyCreateResponse createParentSurvey(@RequestBody ParentSurveyCreateRequest request) {
        return parentSurveyService.createParentSurvey(request);
    }
}
