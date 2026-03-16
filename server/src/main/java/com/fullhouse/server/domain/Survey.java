package com.fullhouse.server.domain;

import javax.persistence.Entity;

@Entity
public class Survey {
    private String name;
    private long id;
    private int popularity;
    private float overallScore;
    private long businessId;
    // TODO: not complete


    public Survey(String name, long id, int popularity, float overallScore, long businessId) {
        this.name = name;
        this.id = id;
        this.popularity = popularity;
        this.overallScore = overallScore;
        this.businessId = businessId;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPopularity() {
        return popularity;
    }

    public float getOverallScore() {
        return overallScore;
    }

    public long getBusinessId() {
        return this.businessId = businessId;
    }
}
