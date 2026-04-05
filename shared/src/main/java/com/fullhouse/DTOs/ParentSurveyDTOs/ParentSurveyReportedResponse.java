package com.fullhouse.DTOs.ParentSurveyDTOs;

import java.util.ArrayList;
import java.util.List;

public class ParentSurveyReportedResponse {
    private List<ParentSurveyReportedSingular> parentSurveyReportedSingulars;

    public ParentSurveyReportedResponse() {
        this.parentSurveyReportedSingulars = new ArrayList<>();
    }
    public ParentSurveyReportedResponse(List<ParentSurveyReportedSingular> parentSurveyReportedSingulars) {
        this.parentSurveyReportedSingulars = parentSurveyReportedSingulars;
    }

    public List<ParentSurveyReportedSingular> getParentSurveyReportedSingulars() {
        return parentSurveyReportedSingulars;
    }

    public void setParentSurveyReportedSingulars(List<ParentSurveyReportedSingular> parentSurveyReportedSingulars) {
        this.parentSurveyReportedSingulars = parentSurveyReportedSingulars;
    }
}
