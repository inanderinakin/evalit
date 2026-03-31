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
    private String imageURL;
    private float averageScore;
    private String city;

    public BusinessInListDTO() {
    }

    public BusinessInListDTO(long id, String name, String address, String phoneNumber, String imageURL, float averageScore, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.averageScore = averageScore;
        this.city = city;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
