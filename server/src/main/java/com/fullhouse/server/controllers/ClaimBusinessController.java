package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartRequest;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartResponse;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyRequest;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyResponse;
import com.fullhouse.server.services.ClaimBusinessService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business/claim")
public class ClaimBusinessController {

    private final ClaimBusinessService claimBusinessService;

    public ClaimBusinessController(ClaimBusinessService claimBusinessService) {
        this.claimBusinessService = claimBusinessService;
    }

    @PostMapping("/start")
    public ClaimBusinessStartResponse startClaim(@RequestBody ClaimBusinessStartRequest request) {
        return claimBusinessService.startClaim(request);
    }

    @PostMapping("/verify")
    public ClaimBusinessVerifyResponse verifyClaim(@RequestBody ClaimBusinessVerifyRequest request) {
        return claimBusinessService.verifyClaim(request);
    }
}