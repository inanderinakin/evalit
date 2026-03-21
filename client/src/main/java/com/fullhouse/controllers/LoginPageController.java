package com.fullhouse.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.fullhouse.App;

import javafx.fxml.FXML;

import java.awt.Desktop;

public class LoginPageController {

    @FXML
    private void handleLogin() throws IOException, URISyntaxException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("http://localhost:8080/oauth2/authorization/google"));
        }
        
        App.setRoot("homePage"); 
    }
}
