package com.fullhouse.controllers;

import com.fullhouse.App;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SurveyAddedPopupController {

    @FXML private Text surveyAddedText;

    @FXML
    private void handleGoToApply() throws Exception {
        closePopup();
        App.setRoot("applySurveyPage");
    }

    @FXML
    private void handleBackToMarketplace() throws Exception {
        closePopup();
        App.setRoot("surveyMarketplacePage");
    }

    @FXML
    private void handleClose() {
        closePopup();
    }

    private void closePopup() {
        Stage stage = (Stage) surveyAddedText.getScene().getWindow();
        stage.close();
    }
}
