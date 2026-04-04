package com.fullhouse.server.services;

/**
 * The interface Business claim verification store.
 */
public interface BusinessClaimVerificationStore {
    /**
     * Save.
     *
     * @param claim the claim
     */
    void save(PendingBusinessClaim claim);

    /**
     * Find by email pending business claim.
     *
     * @param email the email
     * @return the pending business claim
     */
    PendingBusinessClaim findByEmail(String email);

    /**
     * Remove.
     *
     * @param email the email
     */
    void remove(String email);
}