package com.fullhouse.server.services;

import com.fullhouse.DTOs.ParentSurveyDTOs.*;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateResponse;
import com.fullhouse.Enums.CategoryEnum;
import com.fullhouse.server.domain.ParentSurvey;
import com.fullhouse.server.domain.User;
import com.fullhouse.server.mappers.ParentSurveyToParentSurveySingularMapper;
import com.fullhouse.server.repositories.ParentSurveyRepository;
import com.fullhouse.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This service is for creating a ParentSurvey
 * The client sends a request to create a parent
 * survey. The request contains name, questions and
 * user ID specification. See {@link ParentSurveyCreateRequest}
 * for further information.
 */

@Service
public class ParentSurveyServiceImpl implements ParentSurveyService {

    private final UserRepository userRepository;
    private final ParentSurveyRepository parentSurveyRepository;

    public ParentSurveyServiceImpl(UserRepository userRepository, ParentSurveyRepository parentSurveyRepository) {
        this.userRepository = userRepository;
        this.parentSurveyRepository = parentSurveyRepository;
    }

    /**
     * Creates a ParentSurvey instance and saves it
     * to the database. See {@link ParentSurveyCreateRequest}
     * for the fields that are specified by the client.
     *
     * @param request
     * @return response
     */
    @Override
    public ParentSurveyCreateResponse createParentSurvey(ParentSurveyCreateRequest request) {
        List<String> questions = request.getQuestions();

        ParentSurvey parentSurvey = new ParentSurvey();
        parentSurvey.setName(request.getName());
        parentSurvey.setPopularity(0);
        parentSurvey.setQuestions(request.getQuestions());
        parentSurvey.setCategory(CategoryEnum.fromDisplayedName(request.getCategory()).name());
        userRepository.findById(request.getCreatorGoogleSub()).ifPresent(parentSurvey::setCreatorUser);
        parentSurvey.setChildrenSurveys(null);

        parentSurveyRepository.save(parentSurvey);
        //System.out.println(parentSurvey.getId()); // FOR TESTING
        return new ParentSurveyCreateResponse();
    }

    // TODO: 
    // WARNING: lacks testing.
    @Override
    public ParentSurveyListResponse getParentSurveysOfUser(ParentSurveyListRequest request) {
        User user;
        if(userRepository.findByGoogleSub(request.getUserId()).isPresent())
            user = userRepository.findByGoogleSub(request.getUserId()).get();
        else return null;

        List<ParentSurvey> parentSurveys = user.getParentSurveysCreated();

        ParentSurveyListResponse response = new ParentSurveyListResponse(new ArrayList<>());

        for(ParentSurvey ps : parentSurveys) {
            response.getParentSurveySingularList().add(ParentSurveyToParentSurveySingularMapper.parentSurveyToParentSurveySingular(ps));
        }
        return response;
    }

    @Override
    public ParentSurveyListResponse getParentSurveysOfMarketplace(ParentSurveyMarketPlaceRequest request) {
        String name = "";
        String category = "";
        if (request.getName() == null) {
            name = "";
        }
        else {
            name = request.getName();
        }
        
        if (request.getCategory() == null || request.getCategory().isBlank()) {
            category = "";
        }
        else {
            category = CategoryEnum.fromDisplayedName(request.getCategory()).name();
        }

        List<ParentSurvey> parentSurveys = parentSurveyRepository.findByNameContainingAndCategoryContainingOrderByPopularityDesc(name, category);

        List<ParentSurveySingular> parentSurveySingularList = new ArrayList<>();

        for (ParentSurvey parentSurveyInList : parentSurveys) {
            parentSurveySingularList.add(ParentSurveyToParentSurveySingularMapper.parentSurveyToParentSurveySingular(parentSurveyInList));
        }

        return new ParentSurveyListResponse(parentSurveySingularList);
    }

    @Override
    public ParentSurveySingularQuestionsResponse getQuestionsOfParentSurvey(ParentSurveySingularQuestionsRequest request) {
        ParentSurvey parentSurvey = parentSurveyRepository.findById(request.getId()).get();
        return new ParentSurveySingularQuestionsResponse(parentSurvey.getName(), parentSurvey.getId(), parentSurvey.getCategory(), parentSurvey.getPopularity(), parentSurvey.getQuestions());
    }
}
