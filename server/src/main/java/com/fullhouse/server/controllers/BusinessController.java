package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.BusinessListRequest;
import com.fullhouse.DTOs.BusinessListResponse;
import com.fullhouse.server.services.BusinessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business/getlist")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping
    public BusinessListResponse getBusinesses(BusinessListRequest request) {
        return businessService.getBusinesses(request);
    }
}