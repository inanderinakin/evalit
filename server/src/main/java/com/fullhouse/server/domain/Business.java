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
    private float averageScore;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "businessOfSurvey", cascade = CascadeType.ALL)
    private List<Survey> surveys;

    public Business() {
    }

    public Business(Long id, String name, String address, String phoneNumber, User owner, List<Survey> surveys, String city, float averageScore) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.owner = owner;
        this.surveys = surveys;
        this.city = city;
        this.averageScore = averageScore;
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

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public float getAverageScore() { return averageScore; }

    public void setAverageScore(float averageScore) { this.averageScore = averageScore; }
}
