package com.fullhouse.server.services;

/**
 * The interface Verification service.
 */
public interface VerificationService {
    /**
     * Generate verification code int.
     *
     * @param email the email
     * @return the int
     */
    public int generateVerificationCode(String email);

    /**
     * Send code.
     *
     * @param email the email
     * @param code  the code
     */
    public void sendCode(String email, int code);

    /**
     * Verify code int.
     *
     * @param email the email
     * @param code  the code
     * @return the int
     */
    public int verifyCode(String email, int code);
}
