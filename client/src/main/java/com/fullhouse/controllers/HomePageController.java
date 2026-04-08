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
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByCityCategoryResponse;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The type Home page controller.
 */
public class HomePageController implements Initializable {
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private List<BusinessInListDTO> businessList;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private ChoiceBox<String> cityChoiceBox;

    @FXML
    private TextField searchField;

    @FXML
    private VBox businessListContainer;

    private BusinessInListDTO clickedBusiness;

    private boolean initializing = true;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (CategoryEnum category : CategoryEnum.values()) {
            categoryChoiceBox.getItems().add(category.getDisplayedName());
        }
        categoryChoiceBox.setValue(App.getPreSelectedCategory());

        for (CityEnum city : CityEnum.values()) {
            cityChoiceBox.getItems().add(city.getDisplayedName());
        }
        cityChoiceBox.setValue(App.getPreSelectedCity());

        initializing = false;
        handleCategoryCity();
    }

    /**
     * Handle business popup.
     *
     * @throws IOException the ıo exception
     */
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
    private void handleCategoryCity() {
        if (initializing) {
            return;
        }

        String category;
        if (categoryChoiceBox.getValue() == null) {
            category = "";
        } else {
            category = categoryChoiceBox.getValue();
        }

        String city;
        if (cityChoiceBox.getValue() == null) {
            city = "";
        } else {
            city = cityChoiceBox.getValue();
        }

        App.setPreSelectedCategory(category);
        App.setPreSelectedCity(city);

        Thread.ofVirtual().start(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();

                String jsonBody = String.format("{\"category\":\"%s\", \"city\":\"%s\"}", category, city);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/business/getlist/category-city-search"))
                        .header("Content-Type","application/json")
                        .header("Accept", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                        .build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    BusinessGetListByCityCategoryResponse businessResponse = mapper.readValue(response.body(), BusinessGetListByCityCategoryResponse.class);
                    List<BusinessInListDTO> list = businessResponse.getBusinessInListDTOList();

                    Platform.runLater(() -> {
                        businessListContainer.getChildren().clear();
                        for (BusinessInListDTO business : list) {
                            businessListContainer.getChildren().add(buildBusinessCard(business));
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void handleSearch() {
        String name = searchField.getText();
        if (name == null) {
            name = "";
        }
        final String finalName = name;
        Thread.ofVirtual().start(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                String jsonBody = "{\"name\":\"" + finalName + "\"}";
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/business/getlist/name-search"))
                        .header("Content-Type","application/json")
                        .header("Accept", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    BusinessGetListByNameResponse businessResponse = mapper.readValue(response.body(), BusinessGetListByNameResponse.class);
                    List<BusinessInListDTO> list = businessResponse.getBusinesses();

                    Platform.runLater(() -> {
                        businessListContainer.getChildren().clear();
                        for (BusinessInListDTO business : list) {
                            businessListContainer.getChildren().add(buildBusinessCard(business));
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void getBusinessList() {
        Thread getBusinessListThread = new Thread(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                String emptyBody = mapper.writeValueAsString(new com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByNameRequest(""));
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/business/getlist/name-search"))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(emptyBody))
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

        Image image;
        if (business.getImageURL() != null && !business.getImageURL().isEmpty()) {
            image = new Image("http://localhost:8080" + business.getImageURL(), true);
        } else {
            image = new Image(getClass().getResourceAsStream("/images/fillerImage.png"));
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        VBox textColumn = new VBox(6);
        Text nameText = new Text(business.getName());
        nameText.setStyle("-fx-font-weight: bold;");
        Text addressText = new Text("Address: " + business.getAddress());
        addressText.setStyle("-fx-fill: #718096;");
        Text phoneText = new Text("Phone: " + (business.getPhoneNumber()));
        phoneText.setStyle("-fx-fill: #718096;");
        textColumn.getChildren().addAll(nameText, addressText, phoneText);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Text scoreText;
        if ( ((Math.abs(business.getAverageScore() - 0.0f) < Math.pow(10,-4))) ) {
            scoreText = new Text(String.format("%s", "No Survey Applied"));
        } else {
            scoreText = new Text(String.format("%.1f", business.getAverageScore()));
        }
        scoreText.setStyle("-fx-fill: #1a8cff; -fx-font-weight: bold;");

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

        card.getChildren().addAll(imageView, textColumn, spacer, scoreText, deleteButton);
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
            Platform.runLater(() -> businessListContainer.getChildren().remove(card));
        }
    }
}
