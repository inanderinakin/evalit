package com.fullhouse.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.Base64;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartRequest;
import com.fullhouse.Enums.CityEnum;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ClaimBusinessPageController implements Initializable {
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @FXML
    private TextField businessNameField;
    private String businessName;

    @FXML
    private TextField businessEmailField;
    private String businessEmail;
    private static String businessEmailStatic;

    public static String getEmail() {
        return businessEmailStatic;
    }

    @FXML
    private ChoiceBox<String> cityChoiceBox;

    @FXML
    private TextField businessAddressField;
    private String businessAddress;

    @FXML
    private TextField businessPhoneNumberField;
    private String businessPhoneNumber;

    @FXML
    private ImageView businessLogoField;
    private Image businessLogo;
    private String logoString64;
    private static File selectedLogoFileStatic;

    @FXML
    private Label statusLabel;

    @FXML
    private Button sendCodeButton;

    @FXML
    private ProgressIndicator loadingSpinner;

    private static final String BUSINESS_NAME_REGEX = "^[\\p{L}0-9 .&'()-]{2,50}$";
    private static final String BUSINESS_EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String BUSINESS_ADDRESS_REGEX = "^[\\p{L}0-9.,/:;#()\\-\\s]{5,200}$";
    private static final String BUSINESS_PHONE_REGEX = "^\\+90\\d{10}$";

    public static File getSelectedLogoFileStatic() {
        return selectedLogoFileStatic;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (CityEnum city : CityEnum.values()) {
            cityChoiceBox.getItems().add(city.getDisplayedName());
        }


    }

    @FXML
    public void chooseLogo() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Please choose a logo for your business");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            businessLogo = new Image(file.toURI().toURL().toExternalForm());
            businessLogoField.setImage(businessLogo);
            selectedLogoFileStatic = file;

            byte[] logoByte = Files.readAllBytes(file.toPath());
            logoString64 = Base64.getEncoder().encodeToString(logoByte);
        }
    }

    @FXML
    public void sendVerificationCode() throws IOException {
        businessName = businessNameField.getText() == null ? "" : businessNameField.getText().trim();
        businessEmail = businessEmailField.getText() == null ? "" : businessEmailField.getText().trim();
        businessAddress = businessAddressField.getText() == null ? "" : businessAddressField.getText().trim();
        businessPhoneNumber = businessPhoneNumberField.getText() == null ? "" : businessPhoneNumberField.getText().trim();
        String city = cityChoiceBox.getValue();
        String googleSub = App.getGoogleSub();

        if (businessName.isEmpty() || businessEmail.isEmpty() || businessAddress.isEmpty()
                || businessPhoneNumber.isEmpty() || city == null || logoString64 == null) {
            statusLabel.setText("Please fill in all fields and upload a logo.");
            return;
        }

        if (!businessName.matches(BUSINESS_NAME_REGEX)) {
            statusLabel.setText("Invalid business name.");
            return;
        }

        if (!businessEmail.matches(BUSINESS_EMAIL_REGEX)) {
            statusLabel.setText("Invalid business email.");
            return;
        }

        if (!businessAddress.matches(BUSINESS_ADDRESS_REGEX)) {
            statusLabel.setText("Invalid business address.");
            return;
        }

        if (!businessPhoneNumber.matches(BUSINESS_PHONE_REGEX)) {
            statusLabel.setText("Invalid business phone number.");
            return;
        }

        businessEmailStatic = businessEmail;
        statusLabel.setText("Verification code sending... Please wait");
        sendCodeButton.setVisible(false);
        sendCodeButton.setManaged(false);
        loadingSpinner.setVisible(true);
        loadingSpinner.setManaged(true);

        ClaimBusinessStartRequest claimRequest = new ClaimBusinessStartRequest(
                googleSub,
                businessName,
                businessEmail,
                businessAddress,
                businessPhoneNumber,
                city,
                logoString64
        );

        Thread.ofVirtual().start(() -> {
            try {
                String jsonBody = mapper.writeValueAsString(claimRequest);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/business/claim/start"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Response Body: " + response.body());

                if (response.statusCode() == 200) {
                    Platform.runLater(() -> {
                        try {
                            App.setRoot("VerificationCodePage");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                    Platform.runLater(() -> {
                        loadingSpinner.setVisible(false);
                        loadingSpinner.setManaged(false);
                        sendCodeButton.setVisible(true);
                        sendCodeButton.setManaged(true);
                        statusLabel.setText("Failed to send verification code.");
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    loadingSpinner.setVisible(false);
                    loadingSpinner.setManaged(false);
                    sendCodeButton.setVisible(true);
                    sendCodeButton.setManaged(true);
                    statusLabel.setText("An error occurred while sending the verification code.");
                });
            }
        });
    }
}
