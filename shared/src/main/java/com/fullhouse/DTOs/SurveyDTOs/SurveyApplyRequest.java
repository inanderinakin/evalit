package com.fullhouse.DTOs.SurveyDTOs;

import java.util.List;

public class SurveyApplyRequest {

    private long businessId;
    private List<Long> parentSurveyIds;

    public long getBusinessId() { return businessId;}

    public List<Long> getParentSurveyIds() { return parentSurveyIds; }
}
