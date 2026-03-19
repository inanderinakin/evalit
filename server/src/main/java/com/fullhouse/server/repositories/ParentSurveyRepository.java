package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.ParentSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentSurveyRepository extends JpaRepository<ParentSurvey, Long> {
}
