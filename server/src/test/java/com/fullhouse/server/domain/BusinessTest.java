package com.fullhouse.server.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BusinessTest {
    private ParentSurvey parentSurvey(long id) {
        ParentSurvey ps = new ParentSurvey();
        ps.setId(id);
        ps.setQuestions(List.of("Q1"));
        return ps;
    }

    @Test
    void returnsExistingSurveyWhenParentSurveyAlreadyApplied() {
        Business business = new Business();
        ParentSurvey applied = parentSurvey(1L);
        Survey existing = new Survey("Applied", applied, business, "form-1");
        business.setSurveys(new ArrayList<>(List.of(existing)));
        assertThat(business.getAppliedSurveyOfBusiness(applied)).isSameAs(existing);
    }

    @Test
    void returnsNullWhenParentSurveyNotApplied() {
        Business business = new Business();
        Survey existing = new Survey("Applied", parentSurvey(1L), business, "form-1");
        business.setSurveys(new ArrayList<>(List.of(existing)));
        assertThat(business.getAppliedSurveyOfBusiness(parentSurvey(2L))).isNull();
    }

    @Test
    void returnsNullWhenBusinessHasNoSurveys() {
        Business business = new Business();
        business.setSurveys(new ArrayList<>());
        assertThat(business.getAppliedSurveyOfBusiness(parentSurvey(1L))).isNull();
    }
}
