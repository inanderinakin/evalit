package com.fullhouse.DTOs.SurveyDTOs;

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

    /**
     * Instantiates a new Survey ın list dto.
     */
    public SurveyInListDTO() {}

    /**
     * Instantiates a new Survey ın list dto.
     *
     * @param surveyName   the survey name
     * @param surveyId     the survey ıd
     * @param popularity   the popularity
     * @param overallScore the overall score
     * @param businessId   the business ıd
     */
    public SurveyInListDTO(String surveyName, long surveyId, int popularity, float overallScore, long businessId) {
        this.surveyName = surveyName;
        this.surveyId = surveyId;
        this.popularity = popularity;
        this.overallScore = overallScore;
        this.businessId = businessId;
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
     * Gets survey ıd.
     *
     * @return the survey ıd
     */
    public long getSurveyId() {
        return surveyId;
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
     * Gets business ıd.
     *
     * @return the business ıd
     */
    public long getBusinessId() {
        return businessId;
    }
}
