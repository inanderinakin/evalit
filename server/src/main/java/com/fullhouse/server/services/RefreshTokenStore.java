package com.fullhouse.server.services;

import java.io.IOException;

/**
 * The interface Refresh token store.
 */
public interface RefreshTokenStore {
    /**
     * Save.
     *
     * @param refreshToken the refresh token
     * @throws IOException the ıo exception
     */
    void save(String refreshToken) throws IOException;

    /**
     * Load string.
     *
     * @return the string
     * @throws IOException the ıo exception
     */
    String load() throws IOException;
}
