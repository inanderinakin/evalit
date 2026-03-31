package com.fullhouse.server.services;

import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class VerificationServiceImpl implements VerificationService { 

    private JavaMailSender mailSender;

    @Override
    public int generateVerificationCode(String email) {
        Random random = new Random();
        int verificationCode = random.nextInt(100000, 999999);
        sendCode(email, verificationCode);
        return verificationCode;
    }

    @Override
    public void sendCode(String email, int code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@evalit.com");
        message.setTo(email);
        message.setText("Your verification code is: " + code);
        mailSender.send(message);
    }

    @Override
    public int verifyCode(String email, int code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyCode'");
    }
    
}
