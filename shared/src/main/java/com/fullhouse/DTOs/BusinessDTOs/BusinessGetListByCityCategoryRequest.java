package com.fullhouse.DTOs.BusinessDTOs;

/**
 * For the endpoint that receives a category and city
 * and returns the list of Businesses that are in that
 * city and have at least 1 Survey in the specified category.
 * The Businesses are sorted by their average scores.
 */
public class BusinessGetListByCityCategoryRequest {
    private String category;
    private String city;

    /**
     * Instantiates a new Business get list by city category request.
     */
    public BusinessGetListByCityCategoryRequest() {}

    /**
     * Instantiates a new Business get list by city category request.
     *
     * @param category the category
     * @param city     the city
     */
    public BusinessGetListByCityCategoryRequest(String category, String city) {
        this.category = category;
        this.city = city;
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
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }
}
