package com.fullhouse.server.services;

import com.fullhouse.DTOs.UserGetRequest;
import com.fullhouse.DTOs.UserGetResponse;
import com.fullhouse.server.domain.User;
import com.fullhouse.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserGetResponse getUser(UserGetRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserGetResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.isBanned(),
                user.isAdmin(),
                user.isBusinessOwner()
        );
    }
}
