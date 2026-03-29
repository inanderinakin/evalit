package com.fullhouse.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PickCategoryController {

    @FXML private TextField searchField;
    @FXML private VBox categoriesContainer;

    @FXML
    private void handleClose() {
        Stage stage = (Stage) searchField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCategorySelect(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String selectedCategory = clickedButton.getText();
        System.out.println("Selected category: " + selectedCategory);
        Stage stage = (Stage) searchField.getScene().getWindow();
        stage.close();
    }
}