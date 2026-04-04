package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByNameRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByNameResponse;
import com.fullhouse.server.services.BusinessService;
import org.springframework.web.bind.annotation.*;

/**
 * The type Business name search controller.
 */
@RestController
@RequestMapping("/business/getlist/name-search")
public class BusinessNameSearchController {

    private final BusinessService businessService;

    /**
     * Instantiates a new Business name search controller.
     *
     * @param businessService the business service
     */
    public BusinessNameSearchController(BusinessService businessService) {
        this.businessService = businessService;
    }

    /**
     * Gets businesses by name.
     *
     * @param request the request
     * @return the businesses by name
     */
    @PostMapping
    public BusinessGetListByNameResponse getBusinessesByName(@RequestBody BusinessGetListByNameRequest request) {
        return businessService.getBusinessesByName(request);
    }
}