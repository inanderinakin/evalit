package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListBySurveyRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListBySurveyResponse;
import com.fullhouse.server.services.BusinessService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business/getlist/survey")
public class BusinessGetListBySurveyController {
    private BusinessService businessService;

    public BusinessGetListBySurveyController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    public BusinessGetListBySurveyResponse getBusinessesBySurvey(@RequestBody BusinessGetListBySurveyRequest request) {
        return businessService.getBusinessesBySurvey(request);
    }
}
