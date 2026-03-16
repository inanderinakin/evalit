package com.fullhouse.DTOs;

/**
 * This DTO is for the singular Survey requests.
 * The difference between this DTO and {@link SurveyInListDTO}
 * is this request&response cycle also features
 * the overall ratings for each question.
 */
public class SurveySingularRequest {
    private long surveyId;

    public long getSurveyId() {
        return surveyId;
    }
}
