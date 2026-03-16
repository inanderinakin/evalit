package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {


    List<Survey> findByBusinessOfSurveyId(long businessId);


    List<Survey> findByParentSurveyId(long parentSurveyId);
}