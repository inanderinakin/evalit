package com.fullhouse.server.services;

public interface VerificationService {
    public int generateVerificationCode(String email);

    public void sendCode(String email, int code);

    public int verifyCode(String email, int code);
}
