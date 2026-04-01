package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByOwnerRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByOwnerResponse;
import com.fullhouse.server.services.BusinessService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business/getlist/owner")
public class BusinessGetListByOwnerController {

    private final BusinessService businessService;

    public BusinessGetListByOwnerController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    public BusinessGetListByOwnerResponse getBusinessesByOwner(@RequestBody BusinessGetListByOwnerRequest request) {
        return businessService.getBusinessesByOwner(request);
    }
}
