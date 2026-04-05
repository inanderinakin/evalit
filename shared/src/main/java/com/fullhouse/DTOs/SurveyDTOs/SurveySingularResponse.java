package com.fullhouse.DTOs.SurveyDTOs;

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

    /**
     * Instantiates a new Survey singular response.
     *
     * @param surveyId            the survey ıd
     * @param surveyName          the survey name
     * @param appliedBusinessName the applied business name
     * @param address             the address
     * @param popularity          the popularity
     * @param overallScore        the overall score
     * @param scoresOfQuestions   the scores of questions
     */
    public SurveySingularResponse(long surveyId, String surveyName, String appliedBusinessName, String address, int popularity, float overallScore, List<Float> scoresOfQuestions) {
        this.surveyId = surveyId;
        this.surveyName = surveyName;
        this.appliedBusinessName = appliedBusinessName;
        this.address = address;
        this.popularity = popularity;
        this.overallScore = overallScore;
        this.scoresOfQuestions = scoresOfQuestions;
    }

    /**
     * Gets survey ıd.
     *
     * @return the survey ıd
     */
    public long getSurveyId() {
        return surveyId;
    }

    /**
     * Gets survey name.
     *
     * @return the survey name
     */
    public String getSurveyName() {
        return surveyName;
    }

    /**
     * Gets applied business name.
     *
     * @return the applied business name
     */
    public String getAppliedBusinessName() {
        return appliedBusinessName;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets popularity.
     *
     * @return the popularity
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * Gets overall score.
     *
     * @return the overall score
     */
    public float getOverallScore() {
        return overallScore;
    }

    /**
     * Gets scores of questions.
     *
     * @return the scores of questions
     */
    public List<Float> getScoresOfQuestions() {
        return scoresOfQuestions;
    }
}
