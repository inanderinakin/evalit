package com.fullhouse.server.services;

public interface EmailService {
    void sendVerificationEmail(String to, String code);
}