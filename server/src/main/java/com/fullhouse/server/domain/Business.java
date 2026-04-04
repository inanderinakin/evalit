package com.fullhouse.server.domain;

import jakarta.persistence.*;

import java.util.List;

/**
 * The type Business.
 */
@Entity
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String city;
    private String imageURL;

    private Float averageScore = 0.0f;

    private String formOfSurvey; // URL for the Google form
    private String formId;

    @ManyToOne
    private User owner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "businessOfSurvey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Survey> surveys;

    /**
     * Instantiates a new Business.
     */
    public Business() {
    }

    /**
     * Instantiates a new Business.
     *
     * @param name         the name
     * @param address      the address
     * @param phoneNumber  the phone number
     * @param email        the email
     * @param imageURL     the image url
     * @param owner        the owner
     * @param surveys      the surveys
     * @param city         the city
     * @param averageScore the average score
     * @param formOfSurvey the form of survey
     * @param formId       the form ıd
     */
    public Business(String name, String address, String phoneNumber, String email, String imageURL, User owner, List<Survey> surveys, String city, float averageScore, String formOfSurvey, String formId) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.imageURL = imageURL;
        this.owner = owner;
        this.surveys = surveys;
        this.city = city;
        this.averageScore = averageScore;
        this.formId = formId;
        this.formOfSurvey = formOfSurvey;
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
     * Gets ıd.
     *
     * @return the ıd
     */
    public Long getId() { return id; }

    /**
     * Sets ıd.
     *
     * @param id the id
     */
    public void setId(Long id) {
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Gets surveys.
     *
     * @return the surveys
     */
    public List<Survey> getSurveys() {
        return surveys;
    }

    /**
     * Sets surveys.
     *
     * @param surveys the surveys
     */
    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
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
     * Gets form ıd.
     *
     * @return the form ıd
     */
    public String getFormId() {
        return formId;
    }

    /**
     * Sets form ıd.
     *
     * @param formId the form ıd
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    /**
     * Gets form of survey.
     *
     * @return the form of survey
     */
    public String getFormOfSurvey() {
        return formOfSurvey;
    }

    /**
     * Sets form of survey.
     *
     * @param formOfSurvey the form of survey
     */
    public void setFormOfSurvey(String formOfSurvey) {
        this.formOfSurvey = formOfSurvey;
    }
}
