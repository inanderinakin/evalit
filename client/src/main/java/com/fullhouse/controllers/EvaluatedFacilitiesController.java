package com.fullhouse.controllers;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListBySurveyResponse;
import com.fullhouse.DTOs.BusinessDTOs.BusinessInListDTO;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * The type Evaluated facilities controller.
 */
public class EvaluatedFacilitiesController implements Initializable {

    @FXML private VBox businessesContainer;
    @FXML private Label surveyNameLabel;
    @FXML private Label surveyInfoLabel;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        surveyNameLabel.setText("Evaluated Facilities: " + App.getPreSelectedSurveyName());
        loadBusinesses();
    }

    private void loadBusinesses() {
        long surveyId = App.getPreSelectedSurveyId();
        Thread.ofVirtual().start(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();

                String jsonBody = String.format("{\"id\":%d}", surveyId);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/business/getlist/survey"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    BusinessGetListBySurveyResponse surveyResponse = mapper.readValue(response.body(), BusinessGetListBySurveyResponse.class);
                    List<BusinessInListDTO> businesses = surveyResponse.getBusinesses();

                    Platform.runLater(() -> {
                        businessesContainer.getChildren().clear();
                        if (businesses == null || businesses.isEmpty()) {
                            Label emptyLabel = new Label("No facilities have been evaluated with this survey yet.");
                            businessesContainer.getChildren().add(emptyLabel);
                        } else {
                            for (BusinessInListDTO business : businesses) {
                                businessesContainer.getChildren().add(buildBusinessCard(business));
                            }
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private HBox buildBusinessCard(BusinessInListDTO business) {
        HBox card = new HBox(12);
        card.getStyleClass().add("businessCard");

        Image image;
        if (business.getImageURL() != null && !business.getImageURL().isEmpty()) {
            image = new Image("http://localhost:8080" + business.getImageURL(), true);
        } else {
            image = new Image(getClass().getResourceAsStream("/images/fillerImage.png"));
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        VBox info = new VBox(6);
        HBox.setHgrow(info, Priority.ALWAYS);

        Label nameLabel = new Label(business.getName());
        Label addressLabel = new Label(business.getAddress() + ", " + business.getCity());
        Label phoneLabel = new Label("Phone: " + business.getPhoneNumber());
        Label scoreLabel = new Label("Average Score: " + business.getAverageScore());

        info.getChildren().addAll(nameLabel, addressLabel, phoneLabel, scoreLabel);
        card.getChildren().addAll(imageView, info);

        return card;
    }
}
