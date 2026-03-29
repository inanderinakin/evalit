package com.fullhouse.DTOs.ParentSurveyDTOs;

public class ParentSurveySingular {
    private String name;
    private String category;
    private int popularity;
    private long id;

    public ParentSurveySingular() {}

    public ParentSurveySingular(String name, long id, String category, int popularity) {
        this.name = name;
        this.id = id;
        this.category = category;
        this.popularity = popularity;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public int getPopularity() { return popularity; }

    public void setPopularity(int popularity) { this.popularity = popularity; }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }
}
