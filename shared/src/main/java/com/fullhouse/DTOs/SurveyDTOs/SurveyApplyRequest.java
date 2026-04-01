package com.fullhouse.DTOs.SurveyDTOs;

import java.util.List;

public class SurveyApplyRequest {

    private long businessId;
    private List<Long> parentSurveyIds;

    public SurveyApplyRequest() {}

    public SurveyApplyRequest(long businessId, List<Long> parentSurveyIds) {
        this.businessId = businessId;
        this.parentSurveyIds = parentSurveyIds;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public List<Long> getParentSurveyIds() {
        return parentSurveyIds;
    }

    public void setParentSurveyIds(List<Long> parentSurveyIds) {
        this.parentSurveyIds = parentSurveyIds;
    }
}
