package com.fullhouse.server.services;

import com.fullhouse.DTOs.LoginDTOs.LoginSuccessResponse;
import com.fullhouse.server.domain.User;
import com.fullhouse.server.repositories.UserRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private volatile LoginSuccessResponse lastLogin;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginSuccessResponse registerLogin(OAuth2User user) {
        String googleSub = user.getAttribute("sub");
        String profilePictureURL = user.getAttribute("picture");
        String name = user.getAttribute("name");
        String email = user.getAttribute("email");

        User dbUser = userRepository.findByGoogleSub(googleSub)
            .orElseGet(() -> userRepository.findByEmail(email).orElseGet(User::new));

        dbUser.setGoogleSub(googleSub);
        dbUser.setProfilePictureURL(profilePictureURL);
        dbUser.setName(name);
        dbUser.setEmail(email);

        boolean isBusinessOwner = dbUser.isBusinessOwner();
        boolean isAdmin = dbUser.isAdmin();
        boolean isBanned = dbUser.isBanned();

        userRepository.save(dbUser);

        LoginSuccessResponse response = new LoginSuccessResponse(googleSub, name, email, profilePictureURL, isBusinessOwner, isAdmin, isBanned);
        lastLogin = response;
        return response;
    }

    @Override
    public LoginSuccessResponse getLastLogin() {
        return lastLogin;
    }

    @Override
    public void clearLastLogin() {
        lastLogin = null;
    }
}