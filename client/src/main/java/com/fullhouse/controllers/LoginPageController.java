package com.fullhouse.controllers;

import java.io.IOException;

import com.fullhouse.App;

import javafx.fxml.FXML;

public class LoginPageController {

    @FXML
    private void handleLogin() throws IOException {
        App.setRoot("homePage"); 
    }
}
