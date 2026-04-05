package com.fullhouse.server.services;

/**
 * The interface Email service.
 */
public interface EmailService {
    /**
     * Send verification email.
     *
     * @param to   the to
     * @param code the code
     */
    void sendVerificationEmail(String to, String code);
}