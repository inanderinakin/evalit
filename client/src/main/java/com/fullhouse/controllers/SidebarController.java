package com.fullhouse.controllers;

import com.fullhouse.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class SidebarController {

    @FXML private Button btnHome;
    @FXML private Button btnMarketplace;
    @FXML private Button btnCreate;
    @FXML private Button btnProfile;
    @FXML private Button btnClaim;
    @FXML private Button btnResults;
    @FXML private Button btnSettings;

    private void setActive(Button activeBtn) {
        Button[] allButtons = {btnHome, btnMarketplace, btnCreate,
                               btnProfile, btnClaim, btnResults, btnSettings};
        for (Button btn : allButtons) {
            btn.setStyle("");
        }
        activeBtn.setStyle("-fx-background-color: #d0d0d0; -fx-background-radius: 12;");
    }

    @FXML
    private void handleHome() throws IOException {
        setActive(btnHome);
        App.setRoot("homePage");
    }

    @FXML
    private void handleMarketplace() throws IOException {
        setActive(btnMarketplace);
        App.setRoot("surveyMarketplacePage");
    }

    @FXML
    private void handleCreate() throws IOException {
        setActive(btnCreate);
        App.setRoot("createSurveyPage");
    }

    @FXML
    private void handleProfile() {
        setActive(btnProfile);
    }

    @FXML
    private void handleClaim() {
        setActive(btnClaim);
    }

    @FXML
    private void handleResults() {
        setActive(btnResults);
    }

    @FXML
    private void handleSettings() {
        setActive(btnSettings);
    }
}