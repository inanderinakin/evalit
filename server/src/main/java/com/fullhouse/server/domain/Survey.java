package com.fullhouse.server.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
    public class Survey {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        private String name;
        private String formOfSurvey; // URL for the google form
        private double overallScore;
        private List<Double> scoresOfQuestions;

        @ManyToOne
        private ParentSurvey parentSurvey;

        @ManyToOne
        private Business businessOfSurvey;

        public Survey(){};

        public Survey(Long id, String name, String formOfSurvey, ParentSurvey parentSurvey, Business businessOfSurvey) {
            this.id = id;
            this.name = name;
            this.formOfSurvey = formOfSurvey;
            this.parentSurvey = parentSurvey;
            this.businessOfSurvey = businessOfSurvey;
        }

    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public List<Double> getScoresOfQuestions() {
        return scoresOfQuestions;
    }

    public void setScoresOfQuestions(List<Double> scoresOfQuestions) {
        this.scoresOfQuestions = scoresOfQuestions;
    }

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

        public void setBusinessOfSurveyId(long businessId) {
            businessOfSurvey.setId(businessId);
        }

        public void setParentSurveyId(Long parentSurveyId) {
            parentSurvey.setId(parentSurveyId);
        }
    }
