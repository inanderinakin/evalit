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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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

    public static File getSelectedLogoFileStatic() {
        return selectedLogoFileStatic;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (CityEnum city : CityEnum.values()) {
            cityChoiceBox.getItems().add(city.getDisplayedName());
        }

        businessNameField.textProperty().addListener((obs, oldVal, newVal) -> businessName = newVal);
        businessEmailField.textProperty().addListener((obs, oldVal, newVal) -> {businessEmail = newVal;});
        businessAddressField.textProperty().addListener((obs, oldVal, newVal) -> businessAddress = newVal);
        businessPhoneNumberField.textProperty().addListener((obs, oldVal, newVal) -> businessPhoneNumber = newVal);
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
        if (businessName != null && businessEmail != null && businessAddress != null && businessPhoneNumber != null && businessLogo != null) {
            businessEmailStatic = businessEmail;
            String googleSub = App.getGoogleSub();
            String city = cityChoiceBox.getValue();
            
            statusLabel.setText("Verification code sending... Please wait");

            ClaimBusinessStartRequest claimRequest = new ClaimBusinessStartRequest(googleSub, businessName, businessEmail, businessAddress, businessPhoneNumber, city, logoString64);

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
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.out.println("Fail");
        }
    }
}
