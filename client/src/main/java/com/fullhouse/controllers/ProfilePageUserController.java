package com.fullhouse.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProfilePageUserController implements Initializable{
    @FXML
    private ImageView profilePictureField;

    @FXML
    private Text userNameLabel;

    @FXML
    private Text userEmailLabel;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String profilePictureURL = App.getProfilePictureURL();
        String userName = App.getUserName();
        String userEmail = App.getUserEmail();

        if (profilePictureURL == null || profilePictureURL.isBlank()) {
            profilePictureField.setImage(new Image("https://picsum.photos/200"));
        }
        else {
            profilePictureField.setImage(new Image(profilePictureURL));

        }

        if (userName == null || userName.isBlank()) {
            userNameLabel.setText("User Name");
        }
        else {
            userNameLabel.setText(userName);
        }

        if (userEmail == null || userEmail.isBlank()) {
            userEmailLabel.setText("User Email");
        }
        else {
            userEmailLabel.setText(userEmail);
        }
    }

    @FXML
    public void getSurveys() throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/parent-survey/get-list"))
            .header("Accept", "application/json")
            .GET()
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}

