package com.fullhouse.server.mappers;

import com.fullhouse.DTOs.SurveyDTOs.SurveyInListDTO;
import com.fullhouse.server.domain.Business;
import com.fullhouse.server.domain.ParentSurvey;
import com.fullhouse.server.domain.Survey;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SurveyToGetSurveyListMapperTest {

    private Survey buildSurvey(Float overallScore) {
        ParentSurvey parentSurvey = new ParentSurvey();
        parentSurvey.setId(42L);
        parentSurvey.setPopularity(7);
        parentSurvey.setQuestions(List.of("How was the service?", "How was the food?"));

        Business business = new Business();
        business.setId(99L);

        Survey survey = new Survey("Dinner Survey", parentSurvey, business, "form-abc");
        survey.setId(5L);
        survey.setResponseCount(12);
        survey.setOverallScore(overallScore);
        survey.setScoresOfQuestions(List.of(4.5f, 3.0f));
        return survey;
    }

    @Test
    void mapsAllFieldsFromEntityToDto() {
        SurveyInListDTO dto = SurveyToGetSurveyListMapper.surveyToSurveyDTO(buildSurvey(3.75f));

        assertThat(dto.getSurveyName()).isEqualTo("Dinner Survey");
        assertThat(dto.getSurveyId()).isEqualTo(5L);
        assertThat(dto.getParentSurveyId()).isEqualTo(42L);
        assertThat(dto.getPopularity()).isEqualTo(7);
        assertThat(dto.getResponseCount()).isEqualTo(12);
        assertThat(dto.getOverallScore()).isEqualTo(3.75f);
        assertThat(dto.getBusinessId()).isEqualTo(99L);
        assertThat(dto.getScoresOfQuestions()).containsExactly(4.5f, 3.0f);
    }

    @Test
    void mapsNullOverallScoreToZero() {
        SurveyInListDTO dto = SurveyToGetSurveyListMapper.surveyToSurveyDTO(buildSurvey(null));

        assertThat(dto.getOverallScore()).isEqualTo(0.0f);
    }
}
