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
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByNameResponse;
import com.fullhouse.DTOs.BusinessDTOs.BusinessInListDTO;
import com.fullhouse.Enums.CategoryEnum;
import com.fullhouse.Enums.CityEnum;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomePageController implements Initializable {
    private final ObjectMapper mapper = new ObjectMapper();
    private List<BusinessInListDTO> businessList;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private ChoiceBox<String> cityChoiceBox;

    @FXML
    private VBox businessListContainer;

    private BusinessInListDTO clickedBusiness;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (CategoryEnum category : CategoryEnum.values()) {
            categoryChoiceBox.getItems().add(category.name());
        }

        for (CityEnum city : CityEnum.values()) {
            cityChoiceBox.getItems().add(city.name());
        }

        getBusinessList();
    }

    @FXML
    public void handleBusinessPopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/businessCardPopup.fxml"));
        Parent root = loader.load();
        BusinessCardPopupController controller = loader.getController();
        controller.setBusiness(clickedBusiness);

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
                    .uri(new URI("http://localhost:8080/business/getlist/name-search"))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    BusinessGetListByNameResponse businessResponse = mapper.readValue(response.body(), BusinessGetListByNameResponse.class);
                    businessList = businessResponse.getBusinesses();

                    Platform.runLater(() -> {
                        businessListContainer.getChildren().clear();

                        for (BusinessInListDTO business : businessList) {
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
                clickedBusiness = business;
                handleBusinessPopup();
            } 
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return card;
    }
}
