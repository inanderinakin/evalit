package com.fullhouse.DTOs.SurveyDTOs;

import java.util.List;

public class ParentSurveyCreateRequest {

    private String name;
    private String creatorGoogleSub;
    private List<String> questions;
    private String category;

    public ParentSurveyCreateRequest() {
    }

    public ParentSurveyCreateRequest(String surveyName, String category, String creatorGoogleSub, List<String> questions) {
        this.name = surveyName;
        this.category = category;
        this.creatorGoogleSub = creatorGoogleSub;
        this.questions = questions;
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public String getCreatorGoogleSub() { 
        return creatorGoogleSub; 
    }
    
    public void setCreatorGoogleSub(String creatorGoogleSub) { 
        this.creatorGoogleSub = creatorGoogleSub; 
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
