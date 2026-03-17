package com.fullhouse.DTOs;


import java.util.List;

/**
 * This DTO manages the request-response
 * cycle where the client sends a request
 * to fetch the list of surveys that are
 * affiliated with a certain Business. Thus,
 * this DTO returns information about those
 * surveys.
 * See {@link SurveyInListDTO} for further information.
 */
public class SurveyListResponse {
    private List<SurveyInListDTO> surveys;

    public SurveyListResponse(List<SurveyInListDTO> surveys) {
        this.surveys = surveys;
    }

    public List<SurveyInListDTO> getSurveys() {
        return surveys;
    }
}
