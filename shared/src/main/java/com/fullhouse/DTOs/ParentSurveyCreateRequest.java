package com.fullhouse.DTOs;

import java.util.List;

public class ParentSurveyCreateRequest {
    private List<String> questions;

    public void setQuestion(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getQuestions() {
        return questions;
    }
}
