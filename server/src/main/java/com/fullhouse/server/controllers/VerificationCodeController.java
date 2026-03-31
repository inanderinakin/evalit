package com.fullhouse.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullhouse.server.services.VerificationServiceImpl;

@RestController
public class VerificationCodeController {
    @GetMapping("/verification/generate")
    public int generateVerificationCode(@RequestParam String email) {
        VerificationServiceImpl verificationService = new VerificationServiceImpl();
        return verificationService.generateVerificationCode(email);
    }


}
