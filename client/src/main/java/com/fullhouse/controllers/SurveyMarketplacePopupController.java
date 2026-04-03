package com.fullhouse.controllers;

import com.fullhouse.App;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingular;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class SurveyMarketplacePopupController {

    @FXML private Label surveyNameLabel;
    @FXML private Label categoriesLabel;
    @FXML private Label usesLabel;

    private ParentSurveySingular survey;

    public void setSurvey(ParentSurveySingular survey) {
        this.survey = survey;
        surveyNameLabel.setText(survey.getName());
        categoriesLabel.setText("Category: " + survey.getCategory());
        usesLabel.setText("Number of uses: " + survey.getPopularity());
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) surveyNameLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleApply() throws IOException {
        App.setPreSelectedSurveyId(survey.getId());
        Stage stage = (Stage) surveyNameLabel.getScene().getWindow();
        stage.close();
        App.setRoot("applySurveyPage");
    }

    @FXML
    private void handleReport() throws IOException {
        Stage stage = (Stage) surveyNameLabel.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/reportSurveyPage.fxml"));
        Parent root = loader.load();
        Stage reportStage = new Stage();
        reportStage.initModality(Modality.APPLICATION_MODAL);
        reportStage.setTitle("Report Survey");
        reportStage.setScene(new Scene(root, 600, 400));
        reportStage.show();
        root.requestLayout();
        root.layout();
    }

    @FXML
    private void handleSeeEvaluated() {
        System.out.println("See evaluated facilities clicked");
    }
}