package com.fullhouse.server.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phoneNumber;
    private String city;
    private String imageURL;

    private Float averageScore = 0.0f;

    private String formOfSurvey; // URL for the Google form
    private String formId;

    @ManyToOne
    private User owner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "businessOfSurvey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Survey> surveys;

    public Business() {
    }

    public Business(String name, String address, String phoneNumber, String imageURL, User owner, List<Survey> surveys, String city, float averageScore, String formOfSurvey, String formId) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.owner = owner;
        this.surveys = surveys;
        this.city = city;
        this.averageScore = averageScore;
        this.formId = formId;
        this.formOfSurvey = formOfSurvey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormOfSurvey() {
        return formOfSurvey;
    }

    public void setFormOfSurvey(String formOfSurvey) {
        this.formOfSurvey = formOfSurvey;
    }
}
