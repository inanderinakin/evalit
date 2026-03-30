package com.fullhouse.DTOs.BusinessDTOs;

// TODO: lacks the logos. In fact the server
//  does not store or manage logos at all right now.

/**
 * This DTO determines the fields that are
 * going to be sent as a list in the DTO:
 * {@link BusinessGetListByCityCategoryResponse}.
 *
 * See {@link BusinessGetListByCityCategoryResponse}
 * for further information.
 */
public class BusinessInListDTO {
    private long id;
    private String name;
    private String address;
    private String phoneNumber;
    private float averageScore;

    public BusinessInListDTO() {
    }

    public BusinessInListDTO(long id, String name, String address, String phoneNumber, float averageScore) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.averageScore = averageScore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }
}
