package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByOwnerRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByOwnerResponse;
import com.fullhouse.server.services.BusinessService;
import org.springframework.web.bind.annotation.*;

/**
 * The type Business get list by owner controller.
 */
@RestController
@RequestMapping("/business/getlist/owner")
public class BusinessGetListByOwnerController {

    private final BusinessService businessService;

    /**
     * Instantiates a new Business get list by owner controller.
     *
     * @param businessService the business service
     */
    public BusinessGetListByOwnerController(BusinessService businessService) {
        this.businessService = businessService;
    }

    /**
     * Gets businesses by owner.
     *
     * @param request the request
     * @return the businesses by owner
     */
    @PostMapping
    public BusinessGetListByOwnerResponse getBusinessesByOwner(@RequestBody BusinessGetListByOwnerRequest request) {
        return businessService.getBusinessesByOwner(request);
    }
}
