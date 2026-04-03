package com.fullhouse.controllers;

import com.fullhouse.App;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SurveyCreatedPopupController {

    @FXML private Text surveyCreatedText;

    @FXML
    private void handleApplySurvey() throws Exception {
        Stage stage = (Stage) surveyCreatedText.getScene().getWindow();
        stage.close();
        App.setRoot("applySurveyPage");
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) surveyCreatedText.getScene().getWindow();
        stage.close();
    }
}
