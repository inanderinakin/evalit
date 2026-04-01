package com.fullhouse.DTOs.ParentSurveyDTOs;

public class ParentSurveyMarketPlaceRequest {
    private String name;
    private String category;

    public ParentSurveyMarketPlaceRequest() {}

    public ParentSurveyMarketPlaceRequest(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
