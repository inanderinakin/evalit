package com.fullhouse.server.services;

import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyResponse;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListResponse;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListRequest;
import com.fullhouse.server.domain.Business;
import com.fullhouse.server.domain.ParentSurvey;
import com.fullhouse.server.domain.Survey;
import com.fullhouse.server.repositories.BusinessRepository;
import com.fullhouse.server.repositories.ParentSurveyRepository;
import com.fullhouse.server.repositories.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SurveyServiceImplTest {

    @Mock
    private SurveyRepository surveyRepository;
    @Mock
    private BusinessRepository businessRepository;
    @Mock
    private ParentSurveyRepository parentSurveyRepository;
    @Mock
    private GoogleOAuthService googleOAuthService;

    private SurveyServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new SurveyServiceImpl(
                surveyRepository, googleOAuthService, null, businessRepository, parentSurveyRepository);
    }

    @Test
    void applySurveyReturnsEmptyLinkWhenBusinessMissing() {
        when(businessRepository.findById(123L)).thenReturn(Optional.empty());

        SurveyApplyResponse response = service.applySurvey(new SurveyApplyRequest(123L, List.of(1L)));

        assertThat(response.getLink()).isEmpty();
        verifyNoInteractions(surveyRepository, parentSurveyRepository, googleOAuthService);
    }

    @Test
    void getSurveyListMapsRepositoryResultsToDtos() {
        ParentSurvey parentSurvey = new ParentSurvey();
        parentSurvey.setId(10L);
        parentSurvey.setPopularity(3);
        parentSurvey.setQuestions(List.of("Q1"));

        Business business = new Business();
        business.setId(55L);

        Survey survey = new Survey("Coffee Survey", parentSurvey, business, "form-x");
        survey.setId(1L);
        survey.setResponseCount(8);
        survey.setOverallScore(4.2f);
        survey.setScoresOfQuestions(List.of(4.2f));

        when(surveyRepository.findByBusinessOfSurveyId(55L)).thenReturn(List.of(survey));

        SurveyListRequest request = new SurveyListRequest();
        request.setBusinessId(55L);

        SurveyListResponse response = service.getSurveyList(request);

        assertThat(response.getSurveys()).hasSize(1);
        assertThat(response.getSurveys().get(0).getSurveyName()).isEqualTo("Coffee Survey");
        assertThat(response.getSurveys().get(0).getBusinessId()).isEqualTo(55L);
        assertThat(response.getSurveys().get(0).getPopularity()).isEqualTo(3);
    }

    @Test
    void getSurveyListReturnsEmptyListWhenBusinessHasNoSurveys() {
        when(surveyRepository.findByBusinessOfSurveyId(77L)).thenReturn(List.of());

        SurveyListRequest request = new SurveyListRequest();
        request.setBusinessId(77L);

        assertThat(service.getSurveyList(request).getSurveys()).isEmpty();
    }
}
