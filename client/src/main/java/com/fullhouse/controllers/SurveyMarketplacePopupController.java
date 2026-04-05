package com.fullhouse.controllers;

import com.fullhouse.App;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingular;
import com.fullhouse.Enums.CategoryEnum;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Survey marketplace popup controller.
 */
public class SurveyMarketplacePopupController implements Initializable{

    @FXML private Label surveyNameLabel;
    @FXML private Label categoriesLabel;
    @FXML private Label usesLabel;

    @FXML
    private Button applySurveyButton;

    private ParentSurveySingular survey;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        applySurveyButton.setVisible(App.isBusinessOwner());
        applySurveyButton.setManaged(App.isBusinessOwner());
    }

    /**
     * Sets survey.
     *
     * @param survey the survey
     */
    public void setSurvey(ParentSurveySingular survey) {
        this.survey = survey;
        surveyNameLabel.setText(survey.getName());

        String category = CategoryEnum.fromValue(survey.getCategory());
        categoriesLabel.setText("Category: " + category);
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
        App.setRoot("ParentSurveyQuestionsPage");
    }

    @FXML
    private void handleReport() throws IOException {
        App.setPreSelectedSurveyId(survey.getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/reportSurveyPage.fxml"));
        Parent root = loader.load();
        Stage reportStage = new Stage();
        reportStage.initModality(Modality.APPLICATION_MODAL);
        reportStage.setTitle("Report Survey");
        reportStage.setScene(new Scene(root, 600, 400));
        reportStage.showAndWait();
        Stage stage = (Stage) surveyNameLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleSeeEvaluated() throws Exception {
        App.setPreSelectedSurveyId(survey.getId());
        App.setPreSelectedSurveyName(survey.getName());
        Stage stage = (Stage) surveyNameLabel.getScene().getWindow();
        stage.close();
        App.setRoot("evaluatedFacilitiesPage");
    }
}