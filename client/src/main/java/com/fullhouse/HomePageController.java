package com.fullhouse;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomePageController {
    @FXML
    public void handleBusinessPopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("businessCardPopup.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Business Profile");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    private void changeToCreateSurveyPage() throws IOException {
        App.setRoot("createSurveyPage");
    }
}
