package com.fullhouse.DTOs.SurveyDTOs;

import java.util.List;

/**
 * The type Parent survey create request.
 */
public class ParentSurveyCreateRequest {

    private String name;
    private String creatorGoogleSub;
    private List<String> questions;
    private String category;

    /**
     * Instantiates a new Parent survey create request.
     */
    public ParentSurveyCreateRequest() {
    }

    /**
     * Instantiates a new Parent survey create request.
     *
     * @param surveyName       the survey name
     * @param category         the category
     * @param creatorGoogleSub the creator google sub
     * @param questions        the questions
     */
    public ParentSurveyCreateRequest(String surveyName, String category, String creatorGoogleSub, List<String> questions) {
        this.name = surveyName;
        this.category = category;
        this.creatorGoogleSub = creatorGoogleSub;
        this.questions = questions;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name; 
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name; 
    }

    /**
     * Gets creator google sub.
     *
     * @return the creator google sub
     */
    public String getCreatorGoogleSub() {
        return creatorGoogleSub; 
    }

    /**
     * Sets creator google sub.
     *
     * @param creatorGoogleSub the creator google sub
     */
    public void setCreatorGoogleSub(String creatorGoogleSub) {
        this.creatorGoogleSub = creatorGoogleSub; 
    }

    /**
     * Gets questions.
     *
     * @return the questions
     */
    public List<String> getQuestions() {
        return questions;
    }

    /**
     * Sets questions.
     *
     * @param questions the questions
     */
    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
