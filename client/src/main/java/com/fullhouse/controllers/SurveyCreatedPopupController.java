package com.fullhouse.controllers;

import com.fullhouse.App;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The type Survey created popup controller.
 */
public class SurveyCreatedPopupController {

    @FXML private Text surveyCreatedText;

    @FXML
    private void handleApplySurvey() throws Exception {
        Stage stage = (Stage) surveyCreatedText.getScene().getWindow();
        stage.close();
        App.setRoot("applySurveyPage");
    }

    @FXML
    private void handleBackToMarketplace() throws Exception {
        Stage stage = (Stage) surveyCreatedText.getScene().getWindow();
        stage.close();
        App.setRoot("surveyMarketplacePage");
    }
}
