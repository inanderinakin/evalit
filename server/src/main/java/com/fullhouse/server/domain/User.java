package com.fullhouse.server.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name = "users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(nullable = false)
        private String name;

        @Column(unique = true, nullable = false)
        private String email;

        private String phoneNumber;

        private boolean isBanned = false;
        private boolean isAdmin = false;
        private boolean isBusinessOwner = false;

        // List for parentSurveys
        @OneToMany(mappedBy = "creatorUser", cascade = CascadeType.ALL)
        private List<ParentSurvey> parentSurveysCreated = new ArrayList<>();

        // List for businesses
        @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
        private List<Business> businesses = new ArrayList<>();

        // Default constructor for JPA
        public User() {}

        // Getters and Setters
        public long getId() { return id; }
        public void setId(long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhoneNumber() { return phoneNumber; }
        public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

        public boolean isBanned() { return isBanned; }
        public void setBanned(boolean banned) { isBanned = banned; }

        public boolean isAdmin() { return isAdmin; }
        public void setAdmin(boolean admin) { isAdmin = admin; }

        public boolean isBusinessOwner() { return isBusinessOwner; }
        public void setBusinessOwner(boolean businessOwner) { isBusinessOwner = businessOwner; }

        public List<ParentSurvey> getParentSurveysCreated() { return parentSurveysCreated; }
        public void setParentSurveysCreated(List<ParentSurvey> parentSurveysCreated) { this.parentSurveysCreated = parentSurveysCreated; }

        public List<Business> getBusinesses() { return businesses; }
        public void setBusinesses(List<Business> businesses) { this.businesses = businesses; }
    }

