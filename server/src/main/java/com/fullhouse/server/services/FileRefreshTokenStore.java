package com.fullhouse.server.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Implements {@link RefreshTokenStore}
 * This class is used to save the refresh token
 * locally to a txt file. Note that the location
 * of the file is not parameterized.
 */
@Service
public class FileRefreshTokenStore implements RefreshTokenStore {

    private static final Path TOKEN_PATH = Path.of("secrets/google-refresh-token.txt");

    /**
     * Saves the generated string to
     * the given directory.
     *
     * @param refreshToken
     * @throws IOException
     */
    @Override
    public void save(String refreshToken) throws IOException {
        Files.createDirectories(TOKEN_PATH.getParent());
        Files.writeString(TOKEN_PATH, refreshToken);
    }

    /**
     * Returns the resulting refresh token
     *
     * @return String
     * @throws IOException
     */
    @Override
    public String load() throws IOException {
        if (!Files.exists(TOKEN_PATH)) {
            return null;
        }
        return Files.readString(TOKEN_PATH).trim();
    }
}