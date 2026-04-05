package com.fullhouse.DTOs.ParentSurveyDTOs;

import java.util.List;

public class ParentSurveyReportedSingular extends ParentSurveySingularQuestionsResponse {
    private List<String> reports;

    public ParentSurveyReportedSingular() {}

    public ParentSurveyReportedSingular(String name, long id, int popularity, String category, List<String> questions, List<String> reports) {
        super(name, id, category, popularity, questions);
        this.reports = reports;
    }

    public List<String> getReports() {
        return reports;
    }

    public void setReports(List<String> reports) {
        this.reports = reports;
    }
}
