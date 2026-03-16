package com.fullhouse.server.domain;

import javax.persistence.*;
import java.util.List;
    @Entity
    @Table(name = "businesses")
    public class Business {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @ManyToOne
        @JoinColumn(name = "owner_id")
        private User owner;

        @OneToMany(mappedBy = "businessOfSurvey", cascade = CascadeType.ALL)
        private List<Survey> surveys;

        public List<Survey> getSurveys() {
            return surveys;
        }

        public void setSurveys(List<Survey> surveys) {
            this.surveys = surveys;
        }

        public User getOwner() {
            return owner;
        }

        public void setOwner(User owner) {
            this.owner = owner;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
