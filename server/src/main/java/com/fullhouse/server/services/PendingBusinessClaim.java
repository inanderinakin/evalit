package com.fullhouse.server.services;

/**
 * The type Pending business claim.
 */
public class PendingBusinessClaim {
    private String googleSub;
    private String businessName;
    private String businessEmail;
    private String imageURL;
    private String address;
    private String phoneNumber;
    private String city;
    private String verificationCode;
    private String logoString64;

    /**
     * Instantiates a new Pending business claim.
     *
     * @param googleSub        the google sub
     * @param businessName     the business name
     * @param businessEmail    the business email
     * @param address          the address
     * @param phoneNumber      the phone number
     * @param city             the city
     * @param imageURL         the image url
     * @param verificationCode the verification code
     * @param logoString64     the logo string 64
     */
    public PendingBusinessClaim(String googleSub, String businessName, String businessEmail, String address, String phoneNumber, String city, String imageURL, String verificationCode, String logoString64) {
        this.googleSub = googleSub;
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.imageURL = imageURL;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.verificationCode = verificationCode;
        this.logoString64 = logoString64;
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
     * Gets google sub.
     *
     * @return the google sub
     */
    public String getGoogleSub() {
        return googleSub;
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
     * Gets business email.
     *
     * @return the business email
     */
    public String getBusinessEmail() {
        return businessEmail;
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
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
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
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets verification code.
     *
     * @return the verification code
     */
    public String getVerificationCode() {
        return verificationCode;
    }
}