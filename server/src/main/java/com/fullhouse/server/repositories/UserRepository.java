package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByGoogleSub(String googleSub);

	Optional<User> findByEmail(String email);
}

