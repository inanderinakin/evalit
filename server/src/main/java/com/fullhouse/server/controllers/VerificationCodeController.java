package com.fullhouse.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullhouse.server.services.VerificationServiceImpl;

/**
 * The type Verification code controller.
 */
@RestController
public class VerificationCodeController {
    /**
     * Generate verification code int.
     *
     * @param email the email
     * @return the int
     */
    @GetMapping("/verification/generate")
    public int generateVerificationCode(@RequestParam String email) {
        VerificationServiceImpl verificationService = new VerificationServiceImpl();
        return verificationService.generateVerificationCode(email);
    }


}
