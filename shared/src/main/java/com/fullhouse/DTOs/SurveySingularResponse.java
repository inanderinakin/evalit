package com.fullhouse.DTOs;

import java.util.List;

/**
 * This DTO is for the singular Survey requests.
 * The difference between this DTO and {@link SurveyInListDTO}
 * is this request&response cycle also features
 * the overall ratings for each question.
 */
public class SurveySingularResponse {
    private long surveyId;
    private String surveyName;
    private String appliedBusinessName;
    private String address;
    private int popularity;
    private float overallScore;
    private List<Float> scoresOfQuestions;

    public SurveySingularResponse(long surveyId, String surveyName, String appliedBusinessName, String address, int popularity, float overallScore, List<Float> scoresOfQuestions) {
        this.surveyId = surveyId;
        this.surveyName = surveyName;
        this.appliedBusinessName = appliedBusinessName;
        this.address = address;
        this.popularity = popularity;
        this.overallScore = overallScore;
        this.scoresOfQuestions = scoresOfQuestions;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public String getAppliedBusinessName() {
        return appliedBusinessName;
    }

    public String getAddress() {
        return address;
    }

    public int getPopularity() {
        return popularity;
    }

    public float getOverallScore() {
        return overallScore;
    }

    public List<Float> getScoresOfQuestions() {
        return scoresOfQuestions;
    }
}
