package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.ParentSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentSurveyRepository extends JpaRepository<ParentSurvey, Long> {

    List<ParentSurvey> findByNameContainingAndCategoryContainingOrderByPopularityDesc(String name, String category);

    @Query(value = """
            SELECT * FROM parent_survey 
            WHERE JSON_LENGTH(reports) >= :minReportCount 
            ORDER BY JSON_LENGTH(reports) DESC 
            """, nativeQuery = true)
    List<ParentSurvey> findReportedParentSurveys(@Param("minReportCount") int minReportCount);
}
