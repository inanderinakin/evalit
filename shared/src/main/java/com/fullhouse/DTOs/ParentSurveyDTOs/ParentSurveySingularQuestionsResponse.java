package com.fullhouse.DTOs.ParentSurveyDTOs;

import java.util.List;

public class ParentSurveySingularQuestionsResponse extends ParentSurveySingular {
    private List<String> questions;

    public ParentSurveySingularQuestionsResponse(String name, long id, String category, int popularity, List<String> questions) {
        super(name, id, category, popularity);
        this.questions = questions;
    }

    public List<String> getQuestions() { return questions; }

    public void setQuestions(List<String> questions) { this.questions = questions; }
}
