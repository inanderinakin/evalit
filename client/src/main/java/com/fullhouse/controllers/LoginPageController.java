package com.fullhouse.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.LoginDTOs.LoginSuccessResponse;

import javafx.fxml.FXML;
import javafx.application.Platform;

import java.awt.Desktop;

public class LoginPageController {

    private static final ObjectMapper mapper = new ObjectMapper();

    @FXML
    private void handleLogin() throws IOException, URISyntaxException {
        // If desktop is supported, open this link in the browser.
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("http://localhost:8080/oauth2/authorization/google"));
        }
        // We use threads because we wait (max 60 seconds) for the server-side to return the JSON of the user, and this makes
        // our application freeze (max 60 seconds). By running these codes in a separate thread apart from the application
        // thread, we keep the application thread working, so no freezing.
        Thread loginThread = new Thread(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                boolean isResponse = false;
                for (int i = 0; i < 60 && !isResponse; i++) {
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/loginSuccess/client"))
                        .header("Accept", "application/json")
                        .build();

                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                    if (response.statusCode() == 200) {
                        String responseBody = response.body();
                        if (!responseBody.isEmpty()) {
                            LoginSuccessResponse loggedUser = mapper.readValue(responseBody, LoginSuccessResponse.class);
                            App.setGoogleSub(loggedUser.getGoogleSub());
                            Platform.runLater(() -> {
                                try {
                                    App.setRoot("homePage");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            isResponse = true; 
                        }
                    }
                    Thread.sleep(1000);
                }
            } 
            catch (URISyntaxException e) {
                e.printStackTrace();
            } 
            catch (IOException e) {
                e.printStackTrace();
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        loginThread.start();
    }
}
