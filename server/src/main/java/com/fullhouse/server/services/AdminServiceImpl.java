package com.fullhouse.server.services;

import com.fullhouse.DTOs.AdminDTOs.*;
import com.fullhouse.server.domain.Survey;
import com.fullhouse.server.domain.User;
import com.fullhouse.server.repositories.ParentSurveyRepository;
import com.fullhouse.server.repositories.SurveyRepository;
import com.fullhouse.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ParentSurveyRepository parentSurveyRepository;
    private final SurveyRepository surveyRepository;

    public AdminServiceImpl(UserRepository userRepository, ParentSurveyRepository parentSurveyRepository, SurveyRepository surveyRepository) {
        this.userRepository = userRepository;
        this.parentSurveyRepository = parentSurveyRepository;
        this.surveyRepository = surveyRepository;
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

    @Override
    public AdminRemoveSurveyResponse removeSurvey(long id) {
        try {
            surveyRepository.deleteById(id);
        } catch (Exception _) {
            return new AdminRemoveSurveyResponse(false);
        }
        return new AdminRemoveSurveyResponse(true);
    }
}