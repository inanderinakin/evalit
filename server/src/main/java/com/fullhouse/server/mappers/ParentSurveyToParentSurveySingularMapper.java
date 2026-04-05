package com.fullhouse.server.mappers;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingular;
import com.fullhouse.server.domain.ParentSurvey;

/**
 * The type Parent survey to parent survey singular mapper.
 */
public class ParentSurveyToParentSurveySingularMapper {

    /**
     * Parent survey to parent survey singular parent survey singular.
     *
     * @param p the p
     * @return the parent survey singular
     */
    public static ParentSurveySingular parentSurveyToParentSurveySingular(ParentSurvey p) {
        return new ParentSurveySingular(p.getName(), p.getId(), p.getCategory(), p.getPopularity());
    }
}
