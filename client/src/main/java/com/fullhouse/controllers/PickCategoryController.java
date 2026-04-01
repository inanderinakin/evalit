package com.fullhouse.controllers;

import com.fullhouse.Enums.CategoryEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PickCategoryController {

    @FXML private TextField searchField;
    @FXML private VBox categoriesContainer;

    private String selectedCategory = "";

    public String getSelectedCategory() {
        return selectedCategory;
    }

    @FXML
    private void initialize() {
        populateCategories("");
        searchField.textProperty().addListener((obs, oldVal, newVal) -> populateCategories(newVal));
    }

    private void populateCategories(String filter) {
        categoriesContainer.getChildren().clear();
        String lowerFilter = filter == null ? "" : filter.toLowerCase();

        for (CategoryEnum category : CategoryEnum.values()) {
            if (category == CategoryEnum.DEFAULT) {
                continue;
            }

            String displayName = category.getDisplayedName();

            if (!lowerFilter.isEmpty() && !displayName.toLowerCase().contains(lowerFilter)) {
                continue;
            }
            Button btn = new Button(displayName);

            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setMnemonicParsing(false);
            btn.setOnAction(this::handleCategorySelect);
            
            categoriesContainer.getChildren().add(btn);
        }
    }

    @FXML
    private void handleClose() {
        selectedCategory = "";
        Stage stage = (Stage) searchField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCategorySelect(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        selectedCategory = clickedButton.getText();
        Stage stage = (Stage) searchField.getScene().getWindow();
        stage.close();
    }
}