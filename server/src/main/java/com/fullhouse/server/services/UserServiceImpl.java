package com.fullhouse.server.services;

import com.fullhouse.DTOs.UserDTOs.UserGetRequest;
import com.fullhouse.DTOs.UserDTOs.UserGetResponse;
import com.fullhouse.server.domain.User;
import com.fullhouse.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserGetResponse getUser(UserGetRequest request) {
        User user = userRepository.findById(request.getUserGoogleSub())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserGetResponse(
                user.getGoogleSub(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.isBanned(),
                user.isAdmin(),
                user.isBusinessOwner()
        );
    }
}
