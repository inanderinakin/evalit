package com.fullhouse.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyRequest;
import com.fullhouse.DTOs.BusinessDTOs.ClaimBusinessVerifyResponse;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class VerificationCodePageController {

    @FXML
    private TextField verificationCodeField;

    private static String businessEmail;

    public static String getBusinessEmail() {
        return businessEmail;
    }

    public static void setBusinessEmail(String businessEmail) {
        VerificationCodePageController.businessEmail = businessEmail;
    }

    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @FXML
    private void handleVerify() {
        String code = verificationCodeField.getText();
        String email = ClaimBusinessPageController.getEmail();
        String googleSub = App.getGoogleSub();

        if (code == null || code.isEmpty() || email == null) {
            return;
        }

        Thread.ofVirtual().start(() -> {
            try {
                ClaimBusinessVerifyRequest verifyRequest = new ClaimBusinessVerifyRequest(googleSub, email, code);
                String jsonBody = mapper.writeValueAsString(verifyRequest);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/business/claim/verify"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    ClaimBusinessVerifyResponse verifyResponse = mapper.readValue(response.body(), ClaimBusinessVerifyResponse.class);
                    if (verifyResponse.isSuccess()) {
                        Platform.runLater(() -> {
                            try {
                                App.setBusinessOwner(true);
                                App.setRoot("homePage");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
