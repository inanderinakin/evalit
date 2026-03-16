package com.fullhouse.server.domain;

import com.fullhouse.server.domain.Survey;
import com.fullhouse.server.domain.User;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "parent_surveys")
public class ParentSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private int popularity = 0;

    // Many ParentSurveys can be created by one User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_user_id")
    private User creatorUser;

    // One ParentSurvey template can have many applied Survey instances
    @OneToMany(mappedBy = "parentSurvey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Survey> childrenSurveys = new ArrayList<>();

    // Default constructor for JPA
    public ParentSurvey() {}

    // Getters and Setters
    public long getId() {
        return id;
        }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() { return popularity; }
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public User getCreatorUser() { return creatorUser; }
    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public List<Survey> getChildrenSurveys() { return childrenSurveys; }
    public void setChildrenSurveys(List<Survey> childrenSurveys) {
        this.childrenSurveys = childrenSurveys;
    }

    // Helper method to increase popularity
    public void incrementPopularity() {
        this.popularity++;
    }
}