package com.fullhouse.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessStartRequest;
import com.fullhouse.Enums.CityEnum;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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
    private File selectedLogoFile;

    private static String businessEmailStatic;
    private static File selectedLogoFileStatic;

    public static String getBusinessEmailStatic() {
        return businessEmailStatic;
    }

    public static File getSelectedLogoFileStatic() {
        return selectedLogoFileStatic;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (CityEnum city : CityEnum.values()) {
            cityChoiceBox.getItems().add(city.getDisplayedName());
        }
        cityChoiceBox.setValue(CityEnum.ANKARA.getDisplayedName());

        businessNameField.textProperty().addListener((obs, oldVal, newVal) -> businessName = newVal);
        businessEmailField.textProperty().addListener((obs, oldVal, newVal) -> {
            businessEmail = newVal;
            businessEmailStatic = newVal;
        });
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
            selectedLogoFile = file;
            selectedLogoFileStatic = file;
            businessLogo = new Image(file.toURI().toURL().toExternalForm());
            businessLogoField.setImage(businessLogo);
        }
    }

    @FXML
    public void sendVerificationCode() throws IOException {
        if (businessName != null && businessEmail != null && businessAddress != null && businessPhoneNumber != null && selectedLogoFile != null) {
            String googleSub = App.getGoogleSub();
            String city = cityChoiceBox.getValue();
            ClaimBusinessStartRequest startRequest = new ClaimBusinessStartRequest(googleSub, businessName, businessEmail, businessAddress, businessPhoneNumber, city);

            Thread.ofVirtual().start(() -> {
                try {
                    String jsonBody = mapper.writeValueAsString(startRequest);
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
            System.out.println("Validation failed. Please fill all fields and select a logo.");
            System.out.println("Fields: name=" + businessName + ", email=" + businessEmail + 
                               ", address=" + businessAddress + ", phone=" + businessPhoneNumber + 
                               ", logo=" + (selectedLogoFile != null));
        }
    }
}
