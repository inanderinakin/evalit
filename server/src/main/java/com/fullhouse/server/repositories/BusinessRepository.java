package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
}
