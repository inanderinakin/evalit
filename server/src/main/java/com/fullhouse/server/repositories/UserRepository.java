package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * Find by google sub optional.
     *
     * @param googleSub the google sub
     * @return the optional
     */
    Optional<User> findByGoogleSub(String googleSub);

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);
}

