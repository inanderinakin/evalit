package com.fullhouse.DTOs.BusinessDTOs;

/**
 * The type Claim business start request.
 */
public class ClaimBusinessStartRequest {
    private String googleSub;
    private String businessName;
    private String businessEmail;
    private String address;
    private String phoneNumber;
    private String city;
    private String logoString64;

    /**
     * Instantiates a new Claim business start request.
     */
    public ClaimBusinessStartRequest() {
    }

    /**
     * Instantiates a new Claim business start request.
     *
     * @param googleSub     the google sub
     * @param businessName  the business name
     * @param businessEmail the business email
     * @param address       the address
     * @param phoneNumber   the phone number
     * @param city          the city
     * @param logoString64  the logo string 64
     */
    public ClaimBusinessStartRequest(String googleSub, String businessName, String businessEmail, String address, String phoneNumber, String city, String logoString64) {
        this.googleSub = googleSub;
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.logoString64 = logoString64;
    }

    /**
     * Gets google sub.
     *
     * @return the google sub
     */
    public String getGoogleSub() {
        return googleSub;
    }

    /**
     * Sets google sub.
     *
     * @param googleSub the google sub
     */
    public void setGoogleSub(String googleSub) {
        this.googleSub = googleSub;
    }

    /**
     * Gets business name.
     *
     * @return the business name
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Sets business name.
     *
     * @param businessName the business name
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * Gets business email.
     *
     * @return the business email
     */
    public String getBusinessEmail() {
        return businessEmail;
    }

    /**
     * Sets business email.
     *
     * @param businessEmail the business email
     */
    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    /**
     * Gets logo string 64.
     *
     * @return the logo string 64
     */
    public String getLogoString64() {
        return logoString64;
    }

    /**
     * Sets logo string 64.
     *
     * @param logoString64 the logo string 64
     */
    public void setLogoString64(String logoString64) {
        this.logoString64 = logoString64;
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