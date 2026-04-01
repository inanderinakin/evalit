package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    List<Business> findByNameContainingIgnoreCaseOrderByAverageScoreDesc(String name);
    @Query("SELECT m FROM Business m " +
            "WHERE m.city LIKE %:city% " +
            "AND (" +
            "    (m.surveys IS EMPTY AND :category = '') " +
            "    OR EXISTS (" +
            "        SELECT 1 FROM m.surveys s " +
            "        JOIN s.parentSurvey p " +
            "        WHERE p.category LIKE %:category%" +
            "    )" +
            ") " +
            "ORDER BY m.averageScore DESC")
    List<Business> findByCityAndDynamicCategoryCheck(
            @Param("city") String city,
            @Param("category") String category
    );
//    List<Business> findByCityContainingAndSurveysParentSurveyCategoryContainingOrderByAverageScoreDesc(String city, String category);
    Business findByFormId(String formId);
    List<Business> findBySurveysParentSurveyId(Long id);
}
