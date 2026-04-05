package com.fullhouse.DTOs.BusinessDTOs;

// TODO: lacks the logos. In fact the server
//  does not store or manage logos at all right now.

/**
 * This DTO determines the fields that are
 * going to be sent as a list in the DTO:
 * {@link BusinessGetListByCityCategoryResponse}.
 * <p>
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

    /**
     * Instantiates a new Business ın list dto.
     */
    public BusinessInListDTO() {
    }

    /**
     * Instantiates a new Business ın list dto.
     *
     * @param id           the id
     * @param name         the name
     * @param address      the address
     * @param phoneNumber  the phone number
     * @param imageURL     the image url
     * @param averageScore the average score
     * @param city         the city
     */
    public BusinessInListDTO(long id, String name, String address, String phoneNumber, String imageURL, float averageScore, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.averageScore = averageScore;
        this.city = city;
    }

    /**
     * Gets ıd.
     *
     * @return the ıd
     */
    public long getId() {
        return id;
    }

    /**
     * Sets ıd.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
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

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets ımage url.
     *
     * @return the ımage url
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Sets ımage url.
     *
     * @param imageURL the image url
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Gets average score.
     *
     * @return the average score
     */
    public float getAverageScore() {
        return averageScore;
    }

    /**
     * Sets average score.
     *
     * @param averageScore the average score
     */
    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
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
