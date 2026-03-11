package com.fullhouse.server.domain;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class ParentSurvey {
    private List<String> questions;
    private long id;
    private int popularity;
    private List<Facility> evaluatedFacilities;
}
