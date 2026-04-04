package com.fullhouse.server.controllers;

import com.fullhouse.server.services.BusinessService;
import org.springframework.web.bind.annotation.*;

/**
 * The type Business controller.
 */
@RestController
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;

    /**
     * Instantiates a new Business controller.
     *
     * @param businessService the business service
     */
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    /**
     * Upload logo.
     *
     * @param businessId the business ıd
     * @param logoBytes  the logo bytes
     */
    @PostMapping("/{businessId}/logo")
    public void uploadLogo(@PathVariable Long businessId, @RequestBody byte[] logoBytes) {
        businessService.saveLogo(businessId, logoBytes);
    }
}
