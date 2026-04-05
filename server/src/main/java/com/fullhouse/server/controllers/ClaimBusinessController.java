package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartRequest;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartResponse;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyRequest;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyResponse;
import com.fullhouse.server.services.ClaimBusinessService;
import org.springframework.web.bind.annotation.*;

/**
 * The type Claim business controller.
 */
@RestController
@RequestMapping("/business/claim")
public class ClaimBusinessController {

    private final ClaimBusinessService claimBusinessService;

    /**
     * Instantiates a new Claim business controller.
     *
     * @param claimBusinessService the claim business service
     */
    public ClaimBusinessController(ClaimBusinessService claimBusinessService) {
        this.claimBusinessService = claimBusinessService;
    }

    /**
     * Start claim claim business start response.
     *
     * @param request the request
     * @return the claim business start response
     */
    @PostMapping("/start")
    public ClaimBusinessStartResponse startClaim(@RequestBody ClaimBusinessStartRequest request) {
        return claimBusinessService.startClaim(request);
    }

    /**
     * Verify claim claim business verify response.
     *
     * @param request the request
     * @return the claim business verify response
     */
    @PostMapping("/verify")
    public ClaimBusinessVerifyResponse verifyClaim(@RequestBody ClaimBusinessVerifyRequest request) {
        return claimBusinessService.verifyClaim(request);
    }
}