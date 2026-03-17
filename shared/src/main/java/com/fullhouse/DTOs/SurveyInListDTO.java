package com.fullhouse.DTOs;

/**
 * This DTO determines the fields of
 * surveys that are going to be transmitted
 * with the {@link SurveyListResponse} DTO.
 * Note that {@link SurveyListResponse} sends
 * a list of this DTO.
 */
public class SurveyInListDTO {
    private String surveyName;
    private long surveyId;
    private int popularity;
    private float overallScore;
    private long businessId;

    public SurveyInListDTO(String surveyName, long surveyId, int popularity, float overallScore, long businessId) {
        this.surveyName = surveyName;
        this.surveyId = surveyId;
        this.popularity = popularity;
        this.overallScore = overallScore;
        this.businessId = businessId;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public int getPopularity() {
        return popularity;
    }

    public float getOverallScore() {
        return overallScore;
    }

    public long getBusinessId() {
        return businessId;
    }
}
