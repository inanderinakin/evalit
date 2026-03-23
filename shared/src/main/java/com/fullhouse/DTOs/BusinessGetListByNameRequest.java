package com.fullhouse.DTOs;

/**
 * For the endpoint that receives a name
 * and returns the list of Businesses that
 * contain the String given in the request
 * in their names.
 */
public class BusinessGetListByNameRequest {
    private String name;

    public BusinessGetListByNameRequest() {
    }

    public BusinessGetListByNameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}