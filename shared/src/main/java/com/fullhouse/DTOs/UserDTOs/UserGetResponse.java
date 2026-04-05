package com.fullhouse.DTOs.UserDTOs;

/**
 * The type User get response.
 */
public class UserGetResponse {
    private String googleSub;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean isBanned;
    private boolean isAdmin;
    private boolean isBusinessOwner;

    /**
     * Instantiates a new User get response.
     *
     * @param googleSub       the google sub
     * @param name            the name
     * @param email           the email
     * @param phoneNumber     the phone number
     * @param isBanned        the is banned
     * @param isAdmin         the is admin
     * @param isBusinessOwner the is business owner
     */
    public UserGetResponse(String googleSub, String name, String email, String phoneNumber,
                           boolean isBanned, boolean isAdmin, boolean isBusinessOwner) {
        this.googleSub = googleSub;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isBanned = isBanned;
        this.isAdmin = isAdmin;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
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
     * Is admin boolean.
     *
     * @return the boolean
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Is business owner boolean.
     *
     * @return the boolean
     */
    public boolean isBusinessOwner() {
        return isBusinessOwner;
    }
}