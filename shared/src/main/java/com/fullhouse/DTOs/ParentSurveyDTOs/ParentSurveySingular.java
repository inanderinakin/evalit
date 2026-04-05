package com.fullhouse.DTOs.ParentSurveyDTOs;

/**
 * The type Parent survey singular.
 */
public class ParentSurveySingular implements Comparable<ParentSurveySingular> {
    private String name;
    private String category;
    private int popularity;
    private long id;

    /**
     * Instantiates a new Parent survey singular.
     */
    public ParentSurveySingular() {}

    /**
     * Instantiates a new Parent survey singular.
     *
     * @param name       the name
     * @param id         the id
     * @param category   the category
     * @param popularity the popularity
     */
    public ParentSurveySingular(String name, long id, String category, int popularity) {
        this.name = name;
        this.id = id;
        this.category = category;
        this.popularity = popularity;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() { return name; }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() { return category; }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) { this.category = category; }

    /**
     * Gets popularity.
     *
     * @return the popularity
     */
    public int getPopularity() { return popularity; }

    /**
     * Sets popularity.
     *
     * @param popularity the popularity
     */
    public void setPopularity(int popularity) { this.popularity = popularity; }

    /**
     * Gets ıd.
     *
     * @return the ıd
     */
    public long getId() { return id; }

    /**
     * Sets ıd.
     *
     * @param id the id
     */
    public void setId(long id) { this.id = id; }

    @Override
    public int compareTo(ParentSurveySingular other) {
        boolean thisTrending = this.popularity >= 20;
        boolean otherTrending = other.popularity >= 20;
        if (thisTrending && !otherTrending) return -1;
        if (!thisTrending && otherTrending) return 1;
        return 0;
    }
}
