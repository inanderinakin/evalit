package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByCityCategoryRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByCityCategoryResponse;
import com.fullhouse.server.services.BusinessService;
import org.springframework.web.bind.annotation.*;

/**
 * The type Business category city search controller.
 */
@RestController
@RequestMapping("/business/getlist")
public class BusinessCategoryCitySearchController {

    private final BusinessService businessService;

    /**
     * Instantiates a new Business category city search controller.
     *
     * @param businessService the business service
     */
    public BusinessCategoryCitySearchController(BusinessService businessService) {
        this.businessService = businessService;
    }

    /**
     * Gets businesses by name.
     *
     * @param request the request
     * @return the businesses by name
     */
    @PostMapping("/category-city-search")
    public BusinessGetListByCityCategoryResponse getBusinessesByName(@RequestBody BusinessGetListByCityCategoryRequest request) {
        return businessService.getBusinessesByCategoryAndCity(request);
    }
}