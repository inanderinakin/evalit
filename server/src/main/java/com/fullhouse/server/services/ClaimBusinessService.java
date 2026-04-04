package com.fullhouse.server.services;

import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartRequest;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartResponse;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyRequest;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyResponse;

/**
 * The interface Claim business service.
 */
public interface ClaimBusinessService {
    /**
     * Start claim claim business start response.
     *
     * @param request the request
     * @return the claim business start response
     */
    ClaimBusinessStartResponse startClaim(ClaimBusinessStartRequest request);

    /**
     * Verify claim claim business verify response.
     *
     * @param request the request
     * @return the claim business verify response
     */
    ClaimBusinessVerifyResponse verifyClaim(ClaimBusinessVerifyRequest request);
}