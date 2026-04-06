package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Survey repository.
 */
@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {


    /**
     * Find by business of survey ıd list.
     *
     * @param businessId the business ıd
     * @return the list
     */
    List<Survey> findByBusinessOfSurveyId(long businessId);

    /**
     * Find by parent survey ıd list.
     *
     * @param parentSurveyId the parent survey ıd
     * @return the list
     */
    List<Survey> findByParentSurveyId(long parentSurveyId);

    /**
     * Find by form of Survey.
     * @param formId the id of the Google form
     * @return the list of surveys
     */
    List<Survey> findByFormId(String formId);
}