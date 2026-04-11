package com.fullhouse.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsResponse;
import com.fullhouse.utilities.AppConfig;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The type Parent survey questions page controller.
 */
public class ParentSurveyQuestionsPageController implements Initializable {

    @FXML private Label surveyNameText;
    @FXML private Label surveyCategoryText;
    @FXML private Label surveyPopularityText;
    @FXML private VBox questionsContainer;

    private final ObjectMapper mapper = new ObjectMapper();
    private ParentSurveySingularQuestionsResponse pageSurveyData;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        long surveyId = App.getPreSelectedSurveyId();
        Thread.ofVirtual().start(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                ParentSurveySingularQuestionsRequest dto = new ParentSurveySingularQuestionsRequest(surveyId);
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(AppConfig.getServerIP() + "/parent-survey/get-singular"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(dto)))
                    .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    ParentSurveySingularQuestionsResponse surveyData = mapper.readValue(response.body(), ParentSurveySingularQuestionsResponse.class);
                    pageSurveyData = surveyData;
                    Platform.runLater(() -> {
                        surveyNameText.setText(surveyData.getName());
                        surveyCategoryText.setText("Category: " + surveyData.getCategory());
                        surveyPopularityText.setText("Number of uses: " + surveyData.getPopularity());
                        questionsContainer.getChildren().clear();
                        for (int i = 0; i < surveyData.getQuestions().size(); i++) {
                            questionsContainer.getChildren().add(new Label((i + 1) + ". " + surveyData.getQuestions().get(i)));
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void handleAdd() throws IOException {
        if (pageSurveyData != null) {
            boolean alreadyAdded = false;
            for (int i = 0; i < App.getWillAppliedSurveys().size(); i++) {
                if (App.getWillAppliedSurveys().get(i).getId() == pageSurveyData.getId()) {
                    alreadyAdded = true;
                }
            }
            if (!alreadyAdded) {
                App.getWillAppliedSurveys().add(pageSurveyData);
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/surveyAddedPopup.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Survey Added");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
