package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByNameRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByNameResponse;
import com.fullhouse.server.services.BusinessService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business/getlist/name-search")
public class BusinessNameSearchController {

    private final BusinessService businessService;

    public BusinessNameSearchController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    public BusinessGetListByNameResponse getBusinessesByName(@RequestBody BusinessGetListByNameRequest request) {
        return businessService.getBusinessesByName(request);
    }
}