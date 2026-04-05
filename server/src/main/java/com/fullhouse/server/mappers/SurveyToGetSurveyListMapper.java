package com.fullhouse.server.mappers;

import com.fullhouse.DTOs.SurveyDTOs.SurveyInListDTO;
import com.fullhouse.server.domain.Survey;

/**
 * Maps the {@link Survey} entity to {@link SurveyInListDTO}
 * See {@link com.fullhouse.server.services.SurveyService}
 * and the method getSurveyList for further information.
 */
public class SurveyToGetSurveyListMapper {

    /**
     * Survey to survey dto survey ın list dto.
     *
     * @param survey the survey
     * @return the survey ın list dto
     */
    public static SurveyInListDTO surveyToSurveyDTO(Survey survey) {
        return new SurveyInListDTO(
                survey.getName(),
                survey.getId(),
                survey.getParentSurvey().getId(),
                survey.getParentSurvey().getPopularity(),
                survey.getResponseCount(),
                survey.getOverallScore(),
                survey.getBusinessOfSurvey().getId(),
                survey.getScoresOfQuestions()
        );
    }
}