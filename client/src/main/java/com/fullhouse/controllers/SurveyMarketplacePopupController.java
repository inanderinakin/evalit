package com.fullhouse.controllers;

import com.fullhouse.App;
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

    @FXML
    private void handleClose() {
        Stage stage = (Stage) surveyNameLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleApply() throws IOException {
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
        reportStage.setScene(new Scene(root));
        reportStage.showAndWait();
    }

    @FXML
    private void handleSeeEvaluated() {
        System.out.println("See evaluated facilities clicked");
    }
}