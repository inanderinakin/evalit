package com.fullhouse.DTOs;

import java.util.List;

// TODO: not complete
public class ParentSurveyCreateRequest {

    private String name;
    private long creatorUserId;
    private List<String> questions;

    public List<String> getQuestions() {
        return questions;
    }
    public void setQuestion(List<String> questions) {
        this.questions = questions;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getCreatorUserId() { return creatorUserId; }
    public void setCreatorUserId(long creatorUserId) { this.creatorUserId = creatorUserId; }
}
