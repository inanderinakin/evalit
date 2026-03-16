package com.fullhouse.server.mappers;

import com.fullhouse.DTOs.SurveyDTO;
import com.fullhouse.server.domain.Survey;

/**
 * Maps the {@link Survey} entity to {@link SurveyDTO}
 * See {@link com.fullhouse.server.services.SurveyService}
 * and the method getSurveyList for further information.
 */
public class SurveyToGetSurveyListMapper {

    public static SurveyDTO surveyToSurveyDTO(Survey survey) {
        return new SurveyDTO(survey.getName(), survey.getId(), survey.getPopularity(), survey.getOverallScore(), survey.getBusinessId());

    }
}
