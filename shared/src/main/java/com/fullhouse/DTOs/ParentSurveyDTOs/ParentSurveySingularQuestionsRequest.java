package com.fullhouse.DTOs.ParentSurveyDTOs;

/**
 * The type Parent survey singular questions request.
 */
public class ParentSurveySingularQuestionsRequest {
    private long id;

    /**
     * Instantiates a new Parent survey singular questions request.
     */
    public ParentSurveySingularQuestionsRequest() {}

    /**
     * Instantiates a new Parent survey singular questions request.
     *
     * @param id the id
     */
    public ParentSurveySingularQuestionsRequest(long id) {
        this.id = id;
    }

    /**
     * Gets ıd.
     *
     * @return the ıd
     */
    public long getId() { return id; }

    /**
     * Sets ıd.
     *
     * @param id the id
     */
    public void setId(long id) { this.id = id; }
}
