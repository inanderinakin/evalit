package com.fullhouse.server.mappers;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingular;
import com.fullhouse.server.domain.ParentSurvey;

public class ParentSurveyToParentSurveySingularMapper {

    public static ParentSurveySingular parentSurveyToParentSurveySingular(ParentSurvey p) {
        return new ParentSurveySingular(p.getName(), p.getId(), p.getCategory(), p.getPopularity());
    }
}
