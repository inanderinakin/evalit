package com.fullhouse.server.repositories;

import com.fullhouse.server.domain.ParentSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
