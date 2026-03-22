package com.fullhouse.DTOs;

// TODO: lacks the logos. In fact the server
//  does not store or manage logos at all right now.
public class BusinessInListDTO {
    private String name;
    private String address;
    private String phoneNumber;
    private float averageScore;

    public BusinessInListDTO(String name, String address, String phoneNumber, float averageScore) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.averageScore = averageScore;
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
