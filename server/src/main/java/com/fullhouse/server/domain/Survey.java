package com.fullhouse.server.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Survey.
 */
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private Float overallScore;
    private List<Float> scoresOfQuestions;

    @ManyToOne
    private ParentSurvey parentSurvey;

    @ManyToOne
    private Business businessOfSurvey;

    /**
     * Instantiates a new Survey.
     */
    public Survey() {
    }

    /**
     * Instantiates a new Survey.
     *
     * @param name             the name
     * @param parentSurvey     the parent survey
     * @param businessOfSurvey the business of survey
     */
    public Survey(String name, ParentSurvey parentSurvey, Business businessOfSurvey) {
        this.name = name;
        this.parentSurvey = parentSurvey;
        this.businessOfSurvey = businessOfSurvey;
        scoresOfQuestions = new ArrayList<>(Collections.nCopies(parentSurvey.getQuestions().size(), 0.0f));
        overallScore = 0.0f;
    }

    /**
     * Gets ıd.
     *
     * @return the ıd
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets ıd.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
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
     * Gets overall score.
     *
     * @return the overall score
     */
    public Float getOverallScore() {
        return overallScore;
    }

    /**
     * Sets overall score.
     *
     * @param overallScore the overall score
     */
    public void setOverallScore(Float overallScore) {
        this.overallScore = overallScore;
    }

    /**
     * Gets scores of questions.
     *
     * @return the scores of questions
     */
    public List<Float> getScoresOfQuestions() {
        return scoresOfQuestions;
    }

    /**
     * Sets scores of questions.
     *
     * @param scoresOfQuestions the scores of questions
     */
    public void setScoresOfQuestions(List<Float> scoresOfQuestions) {
        this.scoresOfQuestions = scoresOfQuestions;
    }

    /**
     * Gets parent survey.
     *
     * @return the parent survey
     */
    public ParentSurvey getParentSurvey() {
        return parentSurvey;
    }

    /**
     * Sets parent survey.
     *
     * @param parentSurvey the parent survey
     */
    public void setParentSurvey(ParentSurvey parentSurvey) {
        this.parentSurvey = parentSurvey;
    }

    /**
     * Gets business of survey.
     *
     * @return the business of survey
     */
    public Business getBusinessOfSurvey() {
        return businessOfSurvey;
    }

    /**
     * Sets business of survey.
     *
     * @param businessOfSurvey the business of survey
     */
    public void setBusinessOfSurvey(Business businessOfSurvey) {
        this.businessOfSurvey = businessOfSurvey;
    }
    
}
