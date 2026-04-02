package com.fullhouse.controllers;

import java.io.IOException;

import com.fullhouse.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SidebarController {

    @FXML private Label lblBusiness;
    @FXML private Button btnResults;

    @FXML
    private void initialize() {
        boolean isBusiness = App.isBusinessOwner();
        lblBusiness.setVisible(isBusiness);
        lblBusiness.setManaged(isBusiness);
        btnResults.setVisible(isBusiness);
        btnResults.setManaged(isBusiness);
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
    private void handleClaim() throws IOException {
        App.setRoot("claimBusinessPage");
    }

    @FXML
    private void handleResults() {
        
    }

    @FXML
    private void handleSettings() throws IOException {
        setActive(btnSettings);
        App.setRoot("settingsPage");
}
}