package com.fullhouse.server.services;

import com.fullhouse.DTOs.ParentSurveyCreateRequest;
import com.fullhouse.DTOs.ParentSurveyCreateResponse;
import com.fullhouse.server.domain.ParentSurvey;
import com.fullhouse.server.repositories.ParentSurveyRepository;
import com.fullhouse.server.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service is for creating a ParentSurvey
 * The client sends a request to create a parent
 * survey. The request contains name, questions and
 * user ID specification. See {@link ParentSurveyCreateRequest}
 * for further information.
 */

@Service
public class ParentSurveyCreateServiceImpl implements ParentSurveyCreateService {

    private final UserRepository userRepository;
    private final ParentSurveyRepository parentSurveyRepository;

    public ParentSurveyCreateServiceImpl(UserRepository userRepository, ParentSurveyRepository parentSurveyRepository) {
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
        userRepository.findById(request.getCreatorUserId()).ifPresent(parentSurvey::setCreatorUser);
        parentSurvey.setChildrenSurveys(null);

        parentSurveyRepository.save(parentSurvey);
        //System.out.println(parentSurvey.getId()); // FOR TESTING
        return new ParentSurveyCreateResponse();
    }
}
