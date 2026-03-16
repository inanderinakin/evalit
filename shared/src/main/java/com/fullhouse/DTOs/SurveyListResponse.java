package com.fullhouse.DTOs;


import java.util.LinkedHashMap;
import java.util.List;

/**
 * This DTO manages the request-response
 * cycle where the client sends a request
 * to fetch the list of surveys that are
 * affiliated with a certain Business. Thus,
 * this DTO returns information about those
 * surveys.
 * See {@link SurveyDTO} for further information.
 */
public class SurveyListResponse {
    private List<SurveyDTO> surveys;

    public SurveyListResponse(List<SurveyDTO> surveys) {
        this.surveys = surveys;
    }

    public List<SurveyDTO> getSurveys() {
        return surveys;
    }
}
