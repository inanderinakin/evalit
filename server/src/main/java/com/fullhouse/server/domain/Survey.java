package com.fullhouse.server.domain;

import javax.persistence.*;
import java.util.List;
    @Entity
    public class Survey {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String formOfSurvey; // URL for the google form

        @ManyToOne
        @JoinColumn(name = "parent_survey_id")
        private ParentSurvey parentSurvey;

        @ManyToOne
        @JoinColumn(name = "business_id")
        private Business businessOfSurvey;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFormOfSurvey() {
            return formOfSurvey;
        }

        public void setFormOfSurvey(String formOfSurvey) {
            this.formOfSurvey = formOfSurvey;
        }

        public ParentSurvey getParentSurvey() {
            return parentSurvey;
        }

        public void setParentSurvey(ParentSurvey parentSurvey) {
            this.parentSurvey = parentSurvey;
        }

        public Business getBusinessOfSurvey() {
            return businessOfSurvey;
        }

        public void setBusinessOfSurvey(Business businessOfSurvey) {
            this.businessOfSurvey = businessOfSurvey;
        }
    }
