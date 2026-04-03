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

public class SettingsController {

    @FXML private Label nameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private ImageView profileImage;

    @FXML
    public void initialize() {
        nameLabel.setText(App.getUserName());
        emailLabel.setText(App.getUserEmail());
        if (App.getProfilePictureURL() != null) {
            profileImage.setImage(new Image(App.getProfilePictureURL()));
        }
    }

    @FXML
    private void handleEditPhone() {
        TextInputDialog dialog = new TextInputDialog(phoneLabel.getText().equals("Not set") ? "" : phoneLabel.getText());
        dialog.setTitle("Edit Phone Number");
        dialog.setHeaderText("Enter your phone number:");
        dialog.setContentText("Phone number:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String phone = result.get();
            if (!phone.trim().isEmpty()) {
                try {
                    String json = "{\"phoneNumber\":\"" + phone.trim() + "\"}";
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/api/User/Settings"))
                        .header("Content-Type", "application/json")
                        .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
                        .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    if (response.statusCode() == 200) {
                        phoneLabel.setText(phone.trim());
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
    private void handleShare() {
        System.out.println("Share profile picture clicked");
    }

    @FXML
    private void handleLogout() throws IOException {
        App.setRoot("loginPage");
    }
}