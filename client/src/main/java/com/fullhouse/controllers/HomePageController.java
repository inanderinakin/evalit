package com.fullhouse.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.BusinessGetListByCityCategoryResponse;
import com.fullhouse.DTOs.BusinessInListDTO;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomePageController {
    private final ObjectMapper mapper = new ObjectMapper();
    private List<BusinessInListDTO> businessList;

    @FXML
    private VBox businessListContainer;

    @FXML
    public void initialize() {
        getBusinessList();
    }

    @FXML
    public void handleBusinessPopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/businessCardPopup.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Business Profile");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    private void changeToCreateSurveyPage() throws IOException {
        App.setRoot("createSurveyPage");
    }

    @FXML
    private void getBusinessList() {
        Thread getBusinessListThread = new Thread(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/home/getBusinessList"))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    BusinessGetListByCityCategoryResponse businessResponse = mapper.readValue(response.body(), BusinessGetListByCityCategoryResponse.class);
                    businessList = businessResponse.getBusinessInListDTOList();

                    Platform.runLater(() -> {
                        businessListContainer.getChildren().clear();

                        for (BusinessInListDTO business : businessList) {
                            // I believe sorting by category should be here.
                            businessListContainer.getChildren().add(buildBusinessCard(business));
                        }
                    });
                }
            } 
            catch (URISyntaxException e) {
                e.printStackTrace();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        });

        getBusinessListThread.start();
    }
    //If you are not viewing no cards, there is a high chance that there aren't any businesses in the database.
    private HBox buildBusinessCard(BusinessInListDTO business) {
        HBox card = new HBox(12);
        card.getStyleClass().add("businessCard");

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/fillerImage.png")));
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        VBox textColumn = new VBox(6);
        Text nameText = new Text(business.getName());
        Text addressText = new Text("Address: " + business.getAddress());
        Text phoneText = new Text("Phone: " + (business.getPhoneNumber()));
        textColumn.getChildren().addAll(nameText, addressText, phoneText);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Text scoreText = new Text(String.format("%.1f", business.getAverageScore()));

        card.getChildren().addAll(imageView, textColumn, spacer, scoreText);
        card.setOnMouseClicked(event -> {
            try {
                handleBusinessPopup();
            } 
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return card;
    }
}
