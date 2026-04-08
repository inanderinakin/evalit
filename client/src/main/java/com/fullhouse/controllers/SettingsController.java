package com.fullhouse.controllers;

import com.fullhouse.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

/**
 * The type Settings controller.
 */
public class SettingsController {

    @FXML private Label nameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private ImageView profileImage;

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        nameLabel.setText(App.getUserName());
        emailLabel.setText(App.getUserEmail());
        String phone = App.getUserPhoneNumber();
        if (phone != null && !phone.isBlank()) {
            phoneLabel.setText(phone);
        }
        if (App.getProfilePictureURL() != null) {
            profileImage.setImage(new Image(App.getProfilePictureURL()));
        }
    }

    @FXML
    private void handleEditPhone() {
        String initialPhone;
        if (phoneLabel.getText().equals("Not set")) {
            initialPhone = "";
        } else {
            initialPhone = phoneLabel.getText();
        }
        TextInputDialog dialog = new TextInputDialog(initialPhone);
        dialog.setTitle("Edit Phone Number");
        dialog.setHeaderText("Enter your phone number:");
        dialog.setContentText("Phone number:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String phone = result.get();
            if (!phone.trim().isEmpty()) {
                try {
                    String json = "{\"googleSub\":\"" + App.getGoogleSub() + "\",\"phoneNumber\":\"" + phone.trim() + "\"}";
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://31.57.156.36:8080/api/User/Settings"))
                        .header("Content-Type", "application/json")
                        .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
                        .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    if (response.statusCode() == 200) {
                        phoneLabel.setText(phone.trim());
                        App.setUserPhoneNumber(phone.trim());
                        System.out.println("Phone number updated successfully");
                    } else {
                        System.out.println("Failed to update: " + response.statusCode());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void handleLogout() throws IOException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://31.57.156.36:8080/logout/client"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        App.setGoogleSub(null);
        App.setUserName(null);
        App.setUserEmail(null);
        App.setProfilePictureURL(null);
        App.setBusinessOwner(false);
        App.setPreSelectedSurveyId(-1);
        App.setPreSelectedCity("");
        App.setPreSelectedCategory("");
        App.setRoot("loginPage");
    }
}