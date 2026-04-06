package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * The interface Business repository.
 */
@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    /**
     * Find by name containing ıgnore case order by average score desc list.
     *
     * @param name the name
     * @return the list
     */
    List<Business> findByNameContainingIgnoreCaseOrderByAverageScoreDesc(String name);

    /**
     * Find by city and dynamic category check list.
     *
     * @param city     the city
     * @param category the category
     * @return the list
     */
    @Query("SELECT m FROM Business m " +
            "WHERE m.city LIKE CONCAT('%',:city,'%')" +
            "AND (" +
            "    (m.surveys IS NOT EMPTY AND :category = '') " +
            "    OR EXISTS (" +
            "        SELECT 1 FROM m.surveys s " +
            "        JOIN s.parentSurvey p " +
            "        WHERE p.category LIKE CONCAT('%',:category,'%')" +
            "    )" +
            ") " +
            "ORDER BY m.averageScore DESC")
    List<Business> findByCityAndDynamicCategoryCheck(
            @Param("city") String city,
            @Param("category") String category
    );

    /**
     * Find by form ıd business.
     *
     * @param formId the form ıd
     * @return the business
     */
//    List<Business> findByCityContainingAndSurveysParentSurveyCategoryContainingOrderByAverageScoreDesc(String city, String category);
    Business findByFormId(String formId);

    /**
     * Find by surveys parent survey ıd list.
     *
     * @param id the id
     * @return the list
     */
    List<Business> findBySurveysParentSurveyId(Long id);

    /**
     * Find by owner google sub list.
     *
     * @param googleSub the google sub
     * @return the list
     */
    List<Business> findByOwnerGoogleSub(String googleSub);
}
