package com.fullhouse.DTOs.ParentSurveyDTOs;

import java.util.List;

/**
 * The type Parent survey list response.
 */
public class ParentSurveyListResponse {
    private List<ParentSurveySingular> parentSurveySingularList;

    /**
     * Instantiates a new Parent survey list response.
     */
    public ParentSurveyListResponse() {}

    /**
     * Instantiates a new Parent survey list response.
     *
     * @param parentSurveySingularList the parent survey singular list
     */
    public ParentSurveyListResponse(List<ParentSurveySingular> parentSurveySingularList) {
        this.parentSurveySingularList = parentSurveySingularList;
    }

    /**
     * Gets parent survey singular list.
     *
     * @return the parent survey singular list
     */
    public List<ParentSurveySingular> getParentSurveySingularList() { return parentSurveySingularList; }

    /**
     * Sets parent survey singular list.
     *
     * @param parentSurveySingularList the parent survey singular list
     */
    public void setParentSurveySingularList(List<ParentSurveySingular> parentSurveySingularList) { this.parentSurveySingularList = parentSurveySingularList; }
}
