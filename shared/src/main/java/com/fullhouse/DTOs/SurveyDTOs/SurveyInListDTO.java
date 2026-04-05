package com.fullhouse.DTOs.SurveyDTOs;

import java.util.List;

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
    private long parentSurveyId;
    private int popularity;
    private int responseCount;
    private float overallScore;
    private long businessId;
    private List<Float> scoresOfQuestions;

    public SurveyInListDTO() {}

    public SurveyInListDTO(String surveyName, long surveyId, long parentSurveyId,
                           int popularity, int responseCount, float overallScore, long businessId,
                           List<Float> scoresOfQuestions) {
        this.surveyName = surveyName;
        this.surveyId = surveyId;
        this.parentSurveyId = parentSurveyId;
        this.popularity = popularity;
        this.responseCount = responseCount;
        this.overallScore = overallScore;
        this.businessId = businessId;
        this.scoresOfQuestions = scoresOfQuestions;
    }

    public String getSurveyName() { return surveyName; }

    public long getSurveyId() { return surveyId; }

    public long getParentSurveyId() { return parentSurveyId; }

    public int getPopularity() { return popularity; }

    public int getResponseCount() { return responseCount; }

    public float getOverallScore() { return overallScore; }

    public long getBusinessId() { return businessId; }

    public List<Float> getScoresOfQuestions() { return scoresOfQuestions; }
}