package com.fullhouse.DTOs.ParentSurveyDTOs;

/**
 * The type Parent survey market place request.
 */
public class ParentSurveyMarketPlaceRequest {
    private String name;
    private String category;

    /**
     * Instantiates a new Parent survey market place request.
     */
    public ParentSurveyMarketPlaceRequest() {}

    /**
     * Instantiates a new Parent survey market place request.
     *
     * @param name     the name
     * @param category the category
     */
    public ParentSurveyMarketPlaceRequest(String name, String category) {
        this.name = name;
        this.category = category;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
