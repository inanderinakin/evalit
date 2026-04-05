package com.fullhouse.server.controllers;

import com.fullhouse.server.services.BusinessService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping("/{businessId}/logo")
    public void uploadLogo(@PathVariable("businessId") Long businessId, @RequestBody byte[] logoBytes) {
        businessService.saveLogo(businessId, logoBytes);
    }
}
