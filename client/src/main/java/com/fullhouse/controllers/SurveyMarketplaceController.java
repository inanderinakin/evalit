package com.fullhouse.controllers;

import com.fullhouse.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class SurveyMarketplaceController {

    @FXML private TextField searchField;
    @FXML private VBox surveysContainer;

    @FXML
    private void handleCategories() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/pickCategoryPopup.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Pick a Category");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    private void handleSurveyClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/surveyMarketplacePopup.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Survey Details");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}