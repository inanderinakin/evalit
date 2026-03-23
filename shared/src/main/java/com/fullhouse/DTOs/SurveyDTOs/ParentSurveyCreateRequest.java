package com.fullhouse.DTOs.SurveyDTOs;

import java.util.List;

public class ParentSurveyCreateRequest {

    private String name;
    private long creatorUserId;
    private List<String> questions;
    private String category;

    public ParentSurveyCreateRequest() {
    }

    public ParentSurveyCreateRequest(String surveyName, long creatorUserId, List<String> questions, String category) {
        this.name = surveyName;
        this.creatorUserId = creatorUserId;
        this.questions = questions;
        this.category = category;
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public long getCreatorUserId() { 
        return creatorUserId; 
    }
    
    public void setCreatorUserId(long creatorUserId) { 
        this.creatorUserId = creatorUserId; 
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}
