package com.fullhouse.server.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    public Survey() {
    }

    public Survey(String name, ParentSurvey parentSurvey, Business businessOfSurvey) {
        this.name = name;
        this.parentSurvey = parentSurvey;
        this.businessOfSurvey = businessOfSurvey;
        scoresOfQuestions = new ArrayList<>(Collections.nCopies(parentSurvey.getQuestions().size(), 0.0f));
        overallScore = 0.0f;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Float overallScore) {
        this.overallScore = overallScore;
    }

    public List<Float> getScoresOfQuestions() {
        return scoresOfQuestions;
    }

    public void setScoresOfQuestions(List<Float> scoresOfQuestions) {
        this.scoresOfQuestions = scoresOfQuestions;
    }

    public ParentSurvey getParentSurvey() {
        return parentSurvey;
    }

    public void setParentSurvey(ParentSurvey parentSurvey) {
        this.parentSurvey = parentSurvey;
    }

    public Business getBusinessOfSurvey() {
        return businessOfSurvey;
    }

    public void setBusinessOfSurvey(Business businessOfSurvey) {
        this.businessOfSurvey = businessOfSurvey;
    }
    
}
