package com.fullhouse.DTOs;

import java.util.List;

// TODO: not complete (UPDATE: I have added a public constructor, maybe this completes the class? -İnan)
public class ParentSurveyCreateRequest {

    private String name;
    private long creatorUserId;
    private List<String> questions;

    public ParentSurveyCreateRequest() {
    }

    public ParentSurveyCreateRequest(String surveyName, long creatorUserId, List<String> questions) {
        this.name = surveyName;
        this.creatorUserId = creatorUserId;
        this.questions = questions;
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
}
