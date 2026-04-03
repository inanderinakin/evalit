package com.fullhouse.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class ReportSurveyController {

    @FXML private TextArea reportField;

    @FXML
    private void handleSubmit() throws IOException {
        Stage stage = (Stage) reportField.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/reportSuccessPopup.fxml"));
        if (loader.getLocation() == null) {
            System.out.println("ERROR: reportSuccessPopup.fxml not found!");
            return;
        }
        Parent root = loader.load();
        Stage successStage = new Stage();
        successStage.initModality(Modality.APPLICATION_MODAL);
        successStage.setTitle("Report Submitted");
        successStage.setScene(new Scene(root, 400, 300));
        stage.close();
        successStage.show();
        root.requestLayout();
        root.layout();
    }
}