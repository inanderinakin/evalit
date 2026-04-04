package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListBySurveyRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListBySurveyResponse;
import com.fullhouse.server.services.BusinessService;
import org.springframework.web.bind.annotation.*;

/**
 * The type Business get list by survey controller.
 */
@RestController
@RequestMapping("/business/getlist/survey")
public class BusinessGetListBySurveyController {
    private BusinessService businessService;

    /**
     * Instantiates a new Business get list by survey controller.
     *
     * @param businessService the business service
     */
    public BusinessGetListBySurveyController(BusinessService businessService) {
        this.businessService = businessService;
    }

    /**
     * Gets businesses by survey.
     *
     * @param request the request
     * @return the businesses by survey
     */
    @PostMapping
    public BusinessGetListBySurveyResponse getBusinessesBySurvey(@RequestBody BusinessGetListBySurveyRequest request) {
        return businessService.getBusinessesBySurvey(request);
    }
}
