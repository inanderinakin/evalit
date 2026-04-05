package com.fullhouse.server.services;

import com.fullhouse.DTOs.AdminDTOs.*;
import com.fullhouse.server.domain.Survey;
import com.fullhouse.server.domain.User;
import java.util.List;
import com.fullhouse.server.repositories.BusinessRepository;
import com.fullhouse.server.repositories.ParentSurveyRepository;
import com.fullhouse.server.repositories.SurveyRepository;
import com.fullhouse.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ParentSurveyRepository parentSurveyRepository;
    private final SurveyRepository surveyRepository;
    private final BusinessRepository businessRepository;

    public AdminServiceImpl(UserRepository userRepository, ParentSurveyRepository parentSurveyRepository, SurveyRepository surveyRepository, BusinessRepository businessRepository) {
        this.userRepository = userRepository;
        this.parentSurveyRepository = parentSurveyRepository;
        this.surveyRepository = surveyRepository;
        this.businessRepository = businessRepository;
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
        parentSurveyRepository.findById(request.getParentSurveyId()).ifPresent(parentSurvey -> {
            User creator = parentSurvey.getCreatorUser();
            if (creator != null) {
                creator.setBanned(true);
                userRepository.save(creator);
            }
        });
        List<Survey> childSurveys = surveyRepository.findByParentSurveyId(request.getParentSurveyId());
        surveyRepository.deleteAll(childSurveys);
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

    @Override
    public AdminRemoveBusinessResponse removeBusiness(AdminRemoveBusinessRequest request) {
        List<Survey> businessSurveys = surveyRepository.findByBusinessOfSurveyId(request.getBusinessId());
        surveyRepository.deleteAll(businessSurveys);
        businessRepository.deleteById(request.getBusinessId());
        return new AdminRemoveBusinessResponse(true);
    }
}