package com.fullhouse.server.services;

import com.fullhouse.DTOs.AdminDTOs.AdminBanUserRequest;
import com.fullhouse.DTOs.AdminDTOs.AdminBanUserResponse;
import com.fullhouse.DTOs.AdminDTOs.AdminRemoveParentSurveyRequest;
import com.fullhouse.DTOs.AdminDTOs.AdminRemoveParentSurveyResponse;
import com.fullhouse.server.domain.User;
import com.fullhouse.server.repositories.ParentSurveyRepository;
import com.fullhouse.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * The type Admin service.
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ParentSurveyRepository parentSurveyRepository;

    /**
     * Instantiates a new Admin service.
     *
     * @param userRepository         the user repository
     * @param parentSurveyRepository the parent survey repository
     */
    public AdminServiceImpl(UserRepository userRepository, ParentSurveyRepository parentSurveyRepository) {
        this.userRepository = userRepository;
        this.parentSurveyRepository = parentSurveyRepository;
    }

    @Override
    public AdminBanUserResponse banUser(AdminBanUserRequest request) {
        User user = userRepository.findById(request.getUserGoogleSub())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setBanned(true);
        userRepository.save(user);

        return new AdminBanUserResponse(true);
    }

    @Override
    public AdminRemoveParentSurveyResponse removeParentSurvey(AdminRemoveParentSurveyRequest request) {
        parentSurveyRepository.deleteById(request.getParentSurveyId());
        return new AdminRemoveParentSurveyResponse(true);
    }
}