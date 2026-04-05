package com.fullhouse.server.controllers;

import com.fullhouse.DTOs.UserDTOs.UserSettingsUpdateDTO;
import com.fullhouse.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type User settings controller.
 */
@RestController
@RequestMapping("/api/User")
public class UserSettingsController {
    @Autowired
    private UserRepository userRepository;

    /**
     * Update attributes response entity.
     *
     * @param settings   the settings
     * @param oauth2User the oauth 2 user
     * @return the response entity
     */
    @PatchMapping("/Settings")
    public ResponseEntity<?> updateAttributes(@RequestBody UserSettingsUpdateDTO settings,
                                              @AuthenticationPrincipal OAuth2User oauth2User) {

        String email = oauth2User.getAttribute("email");

        return userRepository.findByEmail(email).map(user -> {



            if(settings.getPhoneNumber() != null) user.setPhoneNumber(settings.getPhoneNumber());


            userRepository.save(user);
            return ResponseEntity.ok("Attributes updated successfully");
        }).orElse(ResponseEntity.status(404).body("User not found"));

    }
}
