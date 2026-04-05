package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.ParentSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Parent survey repository.
 */
@Repository
public interface ParentSurveyRepository extends JpaRepository<ParentSurvey, Long> {

    /**
     * Find by name containing and category containing order by popularity desc list.
     *
     * @param name     the name
     * @param category the category
     * @return the list
     */
    List<ParentSurvey> findByNameContainingAndCategoryContainingOrderByPopularityDesc(String name, String category);

    @Query(value = """
            SELECT * FROM parent_survey 
            WHERE JSON_LENGTH(reports) >= :minReportCount 
            ORDER BY JSON_LENGTH(reports) DESC 
            """, nativeQuery = true)
    List<ParentSurvey> findReportedParentSurveys(@Param("minReportCount") int minReportCount);
}
