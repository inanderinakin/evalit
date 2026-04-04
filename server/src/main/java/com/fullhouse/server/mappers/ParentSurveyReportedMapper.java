package com.fullhouse.server.mappers;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyReportedResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyReportedSingular;
import com.fullhouse.server.domain.ParentSurvey;
import java.util.List;

public class ParentSurveyReportedMapper {

    public static ParentSurveyReportedResponse parentSurveyReportedMapper(List<ParentSurvey> parentSurveys) {
        ParentSurveyReportedResponse response = new ParentSurveyReportedResponse();
        for (ParentSurvey ps : parentSurveys) {
            ParentSurveyReportedSingular psrs = new ParentSurveyReportedSingular(ps.getName(), ps.getId(), ps.getPopularity(), ps.getCategory(), ps.getQuestions(), ps.getReports());
            response.getParentSurveyReportedSingulars().add(psrs);
        }
        return response;
    }
}
