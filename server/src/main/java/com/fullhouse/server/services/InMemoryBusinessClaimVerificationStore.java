package com.fullhouse.server.services;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type In memory business claim verification store.
 */
@Service
public class InMemoryBusinessClaimVerificationStore implements BusinessClaimVerificationStore {

    private final Map<String, PendingBusinessClaim> claims = new ConcurrentHashMap<>();

    @Override
    public void save(PendingBusinessClaim claim) {
        claims.put(claim.getBusinessEmail(), claim);
    }

    @Override
    public PendingBusinessClaim findByEmail(String email) {
        return claims.get(email);
    }

    @Override
    public void remove(String email) {
        claims.remove(email);
    }
}