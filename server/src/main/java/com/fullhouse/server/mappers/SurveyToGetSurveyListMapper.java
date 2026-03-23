package com.fullhouse.server.mappers;

import com.fullhouse.DTOs.SurveyDTOs.SurveyInListDTO;
import com.fullhouse.server.domain.Survey;

/**
 * Maps the {@link Survey} entity to {@link SurveyInListDTO}
 * See {@link com.fullhouse.server.services.SurveyService}
 * and the method getSurveyList for further information.
 */
public class SurveyToGetSurveyListMapper {

    public static SurveyInListDTO surveyToSurveyDTO(Survey survey) {
        return new SurveyInListDTO(survey.getName(), survey.getId(), survey.getParentSurvey().getPopularity(), survey.getParentSurvey().getPopularity(), survey.getBusinessOfSurvey().getId());

    }
}
