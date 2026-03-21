package com.fullhouse.server.services;

import java.io.IOException;

public interface RefreshTokenStore {
    void save(String refreshToken) throws IOException;

    String load() throws IOException;
}
