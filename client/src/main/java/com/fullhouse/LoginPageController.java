package com.fullhouse;

import java.io.IOException;

import javafx.fxml.FXML;

public class LoginPageController {

    @FXML
    private void handleLogin() throws IOException {
        App.setRoot("homePage"); 
    }
}
