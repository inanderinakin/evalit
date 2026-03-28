package com.fullhouse.DTOs.ParentSurveyDTOs;

import java.util.List;

public class ParentSurveyListResponse {
    private List<ParentSurveySingular> parentSurveySingularList;

    public ParentSurveyListResponse(List<ParentSurveySingular> parentSurveySingularList) {
        this.parentSurveySingularList = parentSurveySingularList;
    }

    public List<ParentSurveySingular> getParentSurveySingularList() { return parentSurveySingularList; }

    public void setParentSurveySingularList(List<ParentSurveySingular> parentSurveySingularList) { this.parentSurveySingularList = parentSurveySingularList; }
}
