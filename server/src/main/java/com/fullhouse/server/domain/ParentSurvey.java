package com.fullhouse.server.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Parent survey.
 */
@Entity
public class ParentSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private int popularity = 0;

    private List<String> questions;

    private String category;

    // Many ParentSurveys can be created by one User
    @ManyToOne(fetch = FetchType.LAZY)
    private User creatorUser;

    // One ParentSurvey template can have many applied Survey instances
    @OneToMany(mappedBy = "parentSurvey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Survey> childrenSurveys = new ArrayList<>();

    /**
     * Instantiates a new Parent survey.
     */
// Default constructor for JPA
    public ParentSurvey() {
    }

    /**
     * Instantiates a new Parent survey.
     *
     * @param id              the id
     * @param name            the name
     * @param popularity      the popularity
     * @param questions       the questions
     * @param creatorUser     the creator user
     * @param childrenSurveys the children surveys
     * @param category        the category
     */
    public ParentSurvey(long id, String name, int popularity, List<String> questions, User creatorUser, List<Survey> childrenSurveys, String category) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.questions = questions;
        this.creatorUser = creatorUser;
        this.childrenSurveys = childrenSurveys;
        this.category = category;
    }

    /**
     * Gets ıd.
     *
     * @return the ıd
     */
// Getters and Setters
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
     * Gets popularity.
     *
     * @return the popularity
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * Sets popularity.
     *
     * @param popularity the popularity
     */
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    /**
     * Gets questions.
     *
     * @return the questions
     */
    public List<String> getQuestions() {
        return questions;
    }

    /**
     * Sets questions.
     *
     * @param questions the questions
     */
    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    /**
     * Gets creator user.
     *
     * @return the creator user
     */
    public User getCreatorUser() {
        return creatorUser;
    }

    /**
     * Sets creator user.
     *
     * @param creatorUser the creator user
     */
    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    /**
     * Gets children surveys.
     *
     * @return the children surveys
     */
    public List<Survey> getChildrenSurveys() {
        return childrenSurveys;
    }

    /**
     * Sets children surveys.
     *
     * @param childrenSurveys the children surveys
     */
    public void setChildrenSurveys(List<Survey> childrenSurveys) {
        this.childrenSurveys = childrenSurveys;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() { return category; }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) { this.category = category; }

    /**
     * Increment popularity.
     */
// Helper method to increase popularity
    public void incrementPopularity() {
        this.popularity++;
    }
}