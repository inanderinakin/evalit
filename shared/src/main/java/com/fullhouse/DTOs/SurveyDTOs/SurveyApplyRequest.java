package com.fullhouse.DTOs.SurveyDTOs;

import java.util.List;

/**
 * The type Survey apply request.
 */
public class SurveyApplyRequest {

    private long businessId;
    private List<Long> parentSurveyIds;

    /**
     * Instantiates a new Survey apply request.
     */
    public SurveyApplyRequest() {}

    /**
     * Instantiates a new Survey apply request.
     *
     * @param businessId      the business ıd
     * @param parentSurveyIds the parent survey ıds
     */
    public SurveyApplyRequest(long businessId, List<Long> parentSurveyIds) {
        this.businessId = businessId;
        this.parentSurveyIds = parentSurveyIds;
    }

    /**
     * Gets business ıd.
     *
     * @return the business ıd
     */
    public long getBusinessId() {
        return businessId;
    }

    /**
     * Sets business ıd.
     *
     * @param businessId the business ıd
     */
    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    /**
     * Gets parent survey ıds.
     *
     * @return the parent survey ıds
     */
    public List<Long> getParentSurveyIds() {
        return parentSurveyIds;
    }

    /**
     * Sets parent survey ıds.
     *
     * @param parentSurveyIds the parent survey ıds
     */
    public void setParentSurveyIds(List<Long> parentSurveyIds) {
        this.parentSurveyIds = parentSurveyIds;
    }
}
