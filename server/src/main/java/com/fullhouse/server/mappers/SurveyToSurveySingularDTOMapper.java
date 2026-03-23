package com.fullhouse.server.mappers;

import com.fullhouse.DTOs.SurveyDTOs.SurveySingularResponse;
import com.fullhouse.server.domain.Survey;

/**
 * Maps the {@link Survey} entity to {@link SurveySingularResponse}
 * See {@link com.fullhouse.server.services.SurveyService}
 * and the method getSingularSurvey for further information.
 */
public class SurveyToSurveySingularDTOMapper {

    // TODO: please handle missing fields on this mapper.
//    public SurveySingularResponse surveyToSurveySingularDtoMapper(Survey survey) {
//        return new SurveySingularResponse(survey.getId(),
//                survey.getName(),
//                survey.getBusinessOfSurvey().getName(),
//                survey.getBusinessOfSurvey().getAddress(),
//                survey.getParentSurvey().getPopularity(),
//                survey.getOverallScore(),
//                survey.getParentSurvey().getQuestions()
//                );
//    }
}
