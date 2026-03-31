package com.fullhouse.server.services;

public interface BusinessClaimVerificationStore {
    void save(PendingBusinessClaim claim);
    PendingBusinessClaim findByEmail(String email);
    void remove(String email);
}