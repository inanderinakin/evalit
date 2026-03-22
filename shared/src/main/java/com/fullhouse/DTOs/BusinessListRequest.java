package com.fullhouse.DTOs;

public class BusinessListRequest {
    private String name;

    public BusinessListRequest() {
    }

    public BusinessListRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}