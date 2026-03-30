package com.fullhouse.controllers;

import java.io.IOException;

import com.fullhouse.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SidebarController {

    @FXML private Label userIdLabel;

    @FXML
    private void initialize() {
        String userId = App.getGoogleSub();
        userIdLabel.setText("User ID: " + userId);
    }

    @FXML
    private void handleHome() throws IOException {
        App.setRoot("homePage");
    }

    @FXML
    private void handleMarketplace() throws IOException {
        App.setRoot("surveyMarketplacePage");
    }

    @FXML
    private void handleCreate() throws IOException {
        App.setRoot("createSurveyPage");
    }

    @FXML
    private void handleProfile() throws IOException {
        boolean isBusinessOwner = App.isBusinessOwner();

        if (isBusinessOwner) {
            App.setRoot("profilePageBusiness");
        }
        else {
            App.setRoot("profilePageUser");
        }
    }

    @FXML
    private void handleClaim() {
        // For debugging purposes
        if (App.isBusinessOwner()) {
            App.setBusinessOwner(false);
        }
        else {
            App.setBusinessOwner(true);
        }
    }

    @FXML
    private void handleResults() {
        
    }

    @FXML
    private void handleSettings() {
        
    }
}