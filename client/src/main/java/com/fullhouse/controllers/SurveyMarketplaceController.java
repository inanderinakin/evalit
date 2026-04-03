package com.fullhouse.controllers;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingular;
import com.fullhouse.Enums.CategoryEnum;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SurveyMarketplaceController implements Initializable {

    @FXML private TextField searchField;
    @FXML private VBox surveysContainer;

    private final ObjectMapper mapper = new ObjectMapper();
    private String selectedCategory = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSurveys("", "");
        searchField.textProperty().addListener((obs, oldVal, newVal) -> loadSurveys(newVal, selectedCategory));
    }

    @FXML
    private void handleSearch() {
        loadSurveys(searchField.getText() == null ? "" : searchField.getText().trim(), selectedCategory);
    }

    private void loadSurveys(String nameFilter, String categoryFilter) {
        Thread.ofVirtual().start(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();

                String safeName = nameFilter.replace("\"", "\\\"");
                String safeCategory = categoryFilter.replace("\"", "\\\"");
                String jsonBody = String.format("{\"name\":\"%s\",\"category\":\"%s\"}", safeName, safeCategory);
                
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/parent-survey/getlist/name-category-search"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    ParentSurveyListResponse surveyResponse = mapper.readValue(response.body(), ParentSurveyListResponse.class);
                    List<ParentSurveySingular> surveys = surveyResponse.getParentSurveySingularList();

                    Platform.runLater(() -> {
                        surveysContainer.getChildren().clear();
                        for (ParentSurveySingular survey : surveys) {
                            surveysContainer.getChildren().add(buildSurveyCard(survey));
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private HBox buildSurveyCard(ParentSurveySingular survey) {
        HBox card = new HBox(10);
        card.getStyleClass().add("businessCard");

        VBox info = new VBox(4);
        Label nameLabel = new Label(survey.getName());
        String category = CategoryEnum.fromValue(survey.getCategory());
        Label categoryLabel = new Label("Category: " + category);
        
        Label popularityLabel = new Label("Number of uses: " + survey.getPopularity());
        info.getChildren().addAll(nameLabel, categoryLabel, popularityLabel);
        HBox.setHgrow(info, Priority.ALWAYS);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        card.getChildren().addAll(info, spacer);
        card.setOnMouseClicked(event -> {
            try {
                openPopup(survey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return card;
    }

    private void openPopup(ParentSurveySingular survey) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/surveyMarketplacePopup.fxml"));
        Parent root = loader.load();
        SurveyMarketplacePopupController controller = loader.getController();
        controller.setSurvey(survey);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Survey Details");
        stage.setScene(new Scene(root, 700, 400));
        stage.show();
        root.requestLayout();
        root.layout();
    }

    @FXML
    private void handleCategories() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/pickCategoryPopup.fxml"));
        Parent root = loader.load();
        PickCategoryController controller = loader.getController();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Pick a Category");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
        root.requestLayout();
        root.layout();

        selectedCategory = controller.getSelectedCategory();
        loadSurveys(searchField.getText() == null ? "" : searchField.getText().trim(), selectedCategory);
    }
}