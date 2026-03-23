package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    List<Business> findByNameContainingIgnoreCase(String name);
    List<Business> findByCityAndSurveysParentSurveyCategory(String city, String category);
}
