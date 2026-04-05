package com.fullhouse.DTOs.BusinessDTOs;

/**
 * For the endpoint that receives a name
 * and returns the list of Businesses that
 * contain the String given in the request
 * in their names.
 */
public class BusinessGetListByNameRequest {
    private String name;

    /**
     * Instantiates a new Business get list by name request.
     */
    public BusinessGetListByNameRequest() {
    }

    /**
     * Instantiates a new Business get list by name request.
     *
     * @param name the name
     */
    public BusinessGetListByNameRequest(String name) {
        this.name = name;
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