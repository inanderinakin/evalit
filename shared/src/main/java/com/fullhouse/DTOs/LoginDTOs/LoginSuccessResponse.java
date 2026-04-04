package com.fullhouse.DTOs.LoginDTOs;

/**
 * The type Login success response.
 */
public class LoginSuccessResponse {
    private String googleSub;
    private String name;
    private String email;
    private String profilePictureURL;
    private boolean isBusinessOwner;

    /**
     * Instantiates a new Login success response.
     */
    public LoginSuccessResponse() {
    }

    /**
     * Instantiates a new Login success response.
     *
     * @param googleSub         the google sub
     * @param name              the name
     * @param email             the email
     * @param profilePictureURL the profile picture url
     * @param isBusinessOwner   the is business owner
     */
    public LoginSuccessResponse(String googleSub, String name, String email, String profilePictureURL, boolean isBusinessOwner) {
        this.googleSub = googleSub;
        this.name = name;
        this.email = email;
        this.profilePictureURL = profilePictureURL;
        this.isBusinessOwner = isBusinessOwner;
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
     * @param isBusinessOwner the is business owner
     */
    public void setBusinessOwner(boolean isBusinessOwner) {
        this.isBusinessOwner = isBusinessOwner;
    }
}
