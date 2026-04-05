package com.fullhouse.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.scene.control.Button;
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
    private final HttpClient httpClient = HttpClient.newHttpClient();

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
        nameLabel.setStyle("-fx-font-weight: bold;");
        Label addressLabel = new Label(business.getAddress() + ", " + business.getCity());
        addressLabel.setStyle("-fx-text-fill: #718096;");
        Label phoneLabel = new Label("Phone: " + business.getPhoneNumber());
        phoneLabel.setStyle("-fx-text-fill: #718096;");
        Label scoreLabel = new Label("Average Score: " + business.getAverageScore());
        scoreLabel.setStyle("-fx-text-fill: #1a8cff;");

        info.getChildren().addAll(nameLabel, addressLabel, phoneLabel, scoreLabel);
        HBox.setHgrow(info, Priority.ALWAYS);

        Button deleteButton = new Button();
        ImageView deleteIcon = new ImageView(new Image("/images/deleteIcon.png"));
        deleteIcon.setFitWidth(20);
        deleteIcon.setFitHeight(20);
        deleteButton.setGraphic(deleteIcon);

        deleteButton.setVisible(App.isAdmin());
        deleteButton.setManaged(App.isAdmin());

        deleteButton.setOnAction(event -> {
            try {
                handleDelete(business, card);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        card.getChildren().addAll(imageView, info, deleteButton);
        return card;
    }

    @FXML
    private void handleDelete(BusinessInListDTO business, HBox card) throws URISyntaxException, IOException, InterruptedException {
        handleDelete(business.getId(), card);
    }

    private void handleDelete(long businessId, HBox card) throws URISyntaxException, IOException, InterruptedException {
        String jsonBody = String.format("{\"businessId\":%d}", businessId);
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/admin/remove-business"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        if (response.statusCode() == 200) {
            Platform.runLater(() -> businessesContainer.getChildren().remove(card));
        }
    }
}
