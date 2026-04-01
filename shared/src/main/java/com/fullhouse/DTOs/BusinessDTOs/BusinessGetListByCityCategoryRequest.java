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

    public BusinessGetListByCityCategoryRequest() {}
    public BusinessGetListByCityCategoryRequest(String category, String city) {
        this.category = category;
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
