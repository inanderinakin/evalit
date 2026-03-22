package com.fullhouse.DTOs;

public class BusinessGetListRequest {
    private String category;
    private String city;

    public BusinessGetListRequest(String category, String city) {
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
