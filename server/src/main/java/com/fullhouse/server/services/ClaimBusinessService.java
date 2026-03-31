package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartRequest;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartResponse;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyRequest;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyResponse;

public interface ClaimBusinessService {
    ClaimBusinessStartResponse startClaim(ClaimBusinessStartRequest request);
    ClaimBusinessVerifyResponse verifyClaim(ClaimBusinessVerifyRequest request);
}