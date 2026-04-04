package com.fullhouse.server.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(unique = true, nullable = false)
    private String googleSub;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String profilePictureURL;

    private String phoneNumber;

    private boolean isBanned = false;
    private boolean isAdmin = false;
    private boolean isBusinessOwner = false;

    @OneToMany(mappedBy = "creatorUser", cascade = CascadeType.ALL)
    private List<ParentSurvey> parentSurveysCreated = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Business> businesses = new ArrayList<>();

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param googleSub            the google sub
     * @param name                 the name
     * @param email                the email
     * @param profilePictureURL    the profile picture url
     * @param phoneNumber          the phone number
     * @param isBanned             the is banned
     * @param isAdmin              the is admin
     * @param isBusinessOwner      the is business owner
     * @param parentSurveysCreated the parent surveys created
     * @param businesses           the businesses
     */
    public User(String googleSub, String name, String email, String profilePictureURL, String phoneNumber, boolean isBanned, boolean isAdmin, boolean isBusinessOwner, List<ParentSurvey> parentSurveysCreated, List<Business> businesses) {
        this.googleSub = googleSub;
        this.name = name;
        this.email = email;
        this.profilePictureURL = profilePictureURL;
        this.phoneNumber = phoneNumber;
        this.isBanned = isBanned;
        this.isAdmin = isAdmin;
        this.isBusinessOwner = isBusinessOwner;
        this.parentSurveysCreated = parentSurveysCreated;
        this.businesses = businesses;
    }

    /**
     * Gets google sub.
     *
     * @return the google sub
     */
// Getters and Setters
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
     * Gets profile picture url.
     *
     * @return the profile picture url
     */
    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    /**
     * Sets profile picture url.
     *
     * @param profilePictureURL the profile picture url
     */
    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
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
     * Is banned boolean.
     *
     * @return the boolean
     */
    public boolean isBanned() {
        return isBanned;
    }

    /**
     * Sets banned.
     *
     * @param banned the banned
     */
    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    /**
     * Is admin boolean.
     *
     * @return the boolean
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Sets admin.
     *
     * @param admin the admin
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * Is business owner boolean.
     *
     * @return the boolean
     */
    public boolean isBusinessOwner() {
        return isBusinessOwner;
    }

    /**
     * Sets business owner.
     *
     * @param businessOwner the business owner
     */
    public void setBusinessOwner(boolean businessOwner) {
        isBusinessOwner = businessOwner;
    }

    /**
     * Gets parent surveys created.
     *
     * @return the parent surveys created
     */
    public List<ParentSurvey> getParentSurveysCreated() {
        return parentSurveysCreated;
    }

    /**
     * Sets parent surveys created.
     *
     * @param parentSurveysCreated the parent surveys created
     */
    public void setParentSurveysCreated(List<ParentSurvey> parentSurveysCreated) {
        this.parentSurveysCreated = parentSurveysCreated;
    }

    /**
     * Gets businesses.
     *
     * @return the businesses
     */
    public List<Business> getBusinesses() {
        return businesses;
    }

    /**
     * Sets businesses.
     *
     * @param businesses the businesses
     */
    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }
}

