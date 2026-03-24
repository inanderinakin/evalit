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
        
    }

    @FXML
    private void handleCreate() throws IOException {
        App.setRoot("createSurveyPage");
    }

    @FXML
    private void handleProfile() throws IOException {
        // Here should be a GET method that gets whether the user is a business owner or not. For now, it only directs to User Profile page.
        App.setRoot("profilePageUser");
    }

    @FXML
    private void handleClaim() {
        
    }

    @FXML
    private void handleResults() {
        
    }

    @FXML
    private void handleSettings() {
        
    }
}