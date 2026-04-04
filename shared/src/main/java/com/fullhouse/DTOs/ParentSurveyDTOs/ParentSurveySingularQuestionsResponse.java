package com.fullhouse.DTOs.ParentSurveyDTOs;

import java.util.List;

/**
 * The type Parent survey singular questions response.
 */
public class ParentSurveySingularQuestionsResponse extends ParentSurveySingular {
    private List<String> questions;

    /**
     * Instantiates a new Parent survey singular questions response.
     */
    public ParentSurveySingularQuestionsResponse() {
        
    }

    /**
     * Instantiates a new Parent survey singular questions response.
     *
     * @param name       the name
     * @param id         the id
     * @param category   the category
     * @param popularity the popularity
     * @param questions  the questions
     */
    public ParentSurveySingularQuestionsResponse(String name, long id, String category, int popularity, List<String> questions) {
        super(name, id, category, popularity);
        this.questions = questions;
    }

    /**
     * Gets questions.
     *
     * @return the questions
     */
    public List<String> getQuestions() { return questions; }

    /**
     * Sets questions.
     *
     * @param questions the questions
     */
    public void setQuestions(List<String> questions) { this.questions = questions; }
}
