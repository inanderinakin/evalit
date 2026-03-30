package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByCityCategoryRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByCityCategoryResponse;
import com.fullhouse.server.services.BusinessService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business/getlist/category-city-search")
public class BusinessCategoryCitySearchController {

    private final BusinessService businessService;

    public BusinessCategoryCitySearchController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    public BusinessGetListByCityCategoryResponse getBusinessesByName(@RequestBody BusinessGetListByCityCategoryRequest request) {
        return businessService.getBusinessesByCategoryAndCity(request);
    }
}