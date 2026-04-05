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
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByOwnerRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByOwnerResponse;
import com.fullhouse.DTOs.BusinessDTOs.BusinessInListDTO;
import com.fullhouse.DTOs.SurveyDTOs.SurveyInListDTO;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListResponse;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SurveyResultsPickerController implements Initializable {

    @FXML private VBox surveysContainer;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBusinessesAndSurveys();
    }

    private void loadBusinessesAndSurveys() {
        Thread.ofVirtual().start(() -> {
            try {
                HttpClient client = HttpClient.newHttpClient();

                BusinessGetListByOwnerRequest ownerReq =
                        new BusinessGetListByOwnerRequest(App.getGoogleSub());
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/business/getlist/owner"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(ownerReq)))
                        .build();
                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200 || response.body().isBlank()) {
                    return;
                }

                BusinessGetListByOwnerResponse ownerResp =
                        mapper.readValue(response.body(), BusinessGetListByOwnerResponse.class);
                List<BusinessInListDTO> businesses = ownerResp.getBusinesses();

                if (businesses == null || businesses.isEmpty()) {
                    Platform.runLater(() -> {
                        Label lbl = new Label("You don't own any businesses yet.");
                        surveysContainer.getChildren().add(lbl);
                    });
                    return;
                }

                for (BusinessInListDTO business : businesses) {
                    HttpRequest surveyReq = HttpRequest.newBuilder()
                            .uri(new URI("http://localhost:8080/survey/getlist?businessId="
                                    + business.getId()))
                            .GET()
                            .build();
                    HttpResponse<String> surveyResp =
                            client.send(surveyReq, HttpResponse.BodyHandlers.ofString());

                    if (surveyResp.statusCode() != 200 || surveyResp.body().isBlank()) {
                        continue;
                    }

                    SurveyListResponse surveyListResp =
                            mapper.readValue(surveyResp.body(), SurveyListResponse.class);
                    List<SurveyInListDTO> surveys = surveyListResp.getSurveys();

                    Platform.runLater(() -> buildSection(business, surveys));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void buildSection(BusinessInListDTO business, List<SurveyInListDTO> surveys) {
        Label businessLabel = new Label(business.getName());
        businessLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10 0 4 0;");
        surveysContainer.getChildren().add(businessLabel);

        if (surveys == null || surveys.isEmpty()) {
            Label none = new Label("  No surveys applied yet.");
            none.setStyle("-fx-text-fill: #888888;");
            surveysContainer.getChildren().add(none);
            return;
        }

        for (SurveyInListDTO survey : surveys) {
            surveysContainer.getChildren().add(buildSurveyRow(survey, business));
        }
    }

    private HBox buildSurveyRow(SurveyInListDTO survey, BusinessInListDTO business) {
        HBox card = new HBox(10.0);
        card.getStyleClass().add("businessCard");

        VBox info = new VBox(4.0);
        HBox.setHgrow(info, Priority.ALWAYS);

        Label nameLabel = new Label(survey.getSurveyName());
        nameLabel.setStyle("-fx-font-size: 14px;");

        Label scoreLabel = new Label(String.format("Overall score: %.2f  •  %d responses",
                survey.getOverallScore(), survey.getPopularity()));
        scoreLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555;");

        info.getChildren().addAll(nameLabel, scoreLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label arrow = new Label("›");
        arrow.setStyle("-fx-font-size: 20px; -fx-text-fill: #888888;");

        card.getChildren().addAll(info, spacer, arrow);

        card.setOnMouseClicked(e -> {
            try {
                App.setPreSelectedSurveyId(survey.getSurveyId());
                App.setPreSelectedSurveyName(survey.getSurveyName());
                App.setPreSelectedParentSurveyId(survey.getParentSurveyId());
                App.setPreSelectedBusinessPhone(business.getPhoneNumber());
                App.setPreSelectedBusinessName(business.getName());
                App.setPreSelectedBusinessAddress(business.getAddress());
                App.setPreSelectedBusinessCity(business.getCity());
                App.setPreSelectedOverallScore(survey.getOverallScore());
                App.setPreSelectedPopularity(survey.getPopularity());
                App.setPreSelectedScoresOfQuestions(survey.getScoresOfQuestions());
                App.setPreSelectedResponseCount(survey.getResponseCount());
                App.setRoot("surveyResultsPage");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return card;
    }
}