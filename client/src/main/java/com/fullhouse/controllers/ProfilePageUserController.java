package com.fullhouse.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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


}
