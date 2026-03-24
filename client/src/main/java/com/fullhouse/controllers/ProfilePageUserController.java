package com.fullhouse.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.fullhouse.App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class ProfilePageUserController implements Initializable{
    @FXML
    private Text userNameLabel;

    @FXML
    private Text userEmailLabel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String userName = App.getUserName();
        String userEmail = App.getUserEmail();

        userNameLabel.setText(userName == null || userName.isBlank() ? "User Name" : userName);
        userEmailLabel.setText(userEmail == null || userEmail.isBlank() ? "User Mail" : userEmail);
    }


}
