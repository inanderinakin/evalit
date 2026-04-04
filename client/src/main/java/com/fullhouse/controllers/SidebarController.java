package com.fullhouse.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fullhouse.App;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;

public class SidebarController {

    @FXML
    private Label lblBusiness;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnMarketplace;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnClaim;

    @FXML
    private Button btnResults;

    @FXML
    private Button btnSettings;

    private ArrayList<Button> buttonsList;

    private static String selectedPage;

    @FXML
    private void initialize() {
        boolean isBusiness = App.isBusinessOwner();
        lblBusiness.setVisible(isBusiness);
        lblBusiness.setManaged(isBusiness);
        btnResults.setVisible(isBusiness);
        btnResults.setManaged(isBusiness);

        String userName = App.getUserName();
        if (userName != null && !userName.isBlank()) {
            btnProfile.setText("   " + userName);
        }

        selectedPage = App.getPageName();

        buttonsList = new ArrayList<>();
        buttonsList.addAll(Arrays.asList(btnHome, btnMarketplace, btnCreate, btnProfile, btnClaim, btnResults, btnSettings));

        Map<String, String> pageToButtonText = new HashMap<>();
        pageToButtonText.put("homePage", "Home");
        pageToButtonText.put("surveyMarketplacePage", "Survey Marketplace");
        pageToButtonText.put("createSurveyPage", "Create a Survey");
        String profileButtonText = btnProfile.getText();
        pageToButtonText.put("profilePageBusiness", profileButtonText);
        pageToButtonText.put("profilePageUser", profileButtonText);
        pageToButtonText.put("claimBusinessPage", "Claim your business");
        pageToButtonText.put("evaluatedFacilitiesPage", "Survey Results");
        pageToButtonText.put("settingsPage", "Settings");

        String activeButton = pageToButtonText.get(selectedPage);
        if (activeButton != null) {
            handleButtonStyle(activeButton);
        }
    }

    private void handleButtonStyle(String buttonName) {
        ColorAdjust whiten = new ColorAdjust(0, -1, 1, 0);
        for (Button button : buttonsList) {
            button.getStyleClass().remove("selectedButton");
            Node graphic = button.getGraphic();
            if (button.getText().trim().equals(buttonName.trim())) {
                button.getStyleClass().add("selectedButton");
                if (graphic != null) graphic.setEffect(whiten);
            } 
            else {
                if (graphic != null) graphic.setEffect(null);
            }
        }   
    }

    @FXML
    private void handleHome() throws IOException {
        App.setRoot("homePage");
        handleButtonStyle("Home");
    }

    @FXML
    private void handleMarketplace() throws IOException {
        App.setRoot("surveyMarketplacePage");
        handleButtonStyle("Survey Marketplace");
    }

    @FXML
    private void handleCreate() throws IOException {
        App.setRoot("createSurveyPage");
        handleButtonStyle("Create a Survey");
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

        handleButtonStyle("Profile");
    }

    @FXML
    private void handleClaim() throws IOException {
        App.setRoot("claimBusinessPage");
        handleButtonStyle("Claim your business");
    }

    @FXML
    private void handleResults() throws IOException {
        App.setRoot("evaluatedFacilitiesPage");
        handleButtonStyle("Survey Results");
    }

    @FXML
    private void handleSettings() throws IOException {
        App.setRoot("settingsPage");
        handleButtonStyle("Settings");
    }
}