package com.fullhouse.DTOs.BusinessDTOs;

/**
 * For the endpoint that receives the id
 * of a ParentSurvey and returns the list
 * of Businesses that are evaluated in
 * the given ParentSurvey
 */
public class BusinessGetListBySurveyRequest {
    private long id;

    /**
     * Instantiates a new Business get list by survey request.
     */
    public BusinessGetListBySurveyRequest() {}

    /**
     * Instantiates a new Business get list by survey request.
     *
     * @param id the id
     */
    public BusinessGetListBySurveyRequest(long id) {
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
