package com.fullhouse.controllers;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.DTOs.BusinessDTOs.BusinessInListDTO;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsResponse;
import com.fullhouse.DTOs.SurveyDTOs.SurveyInListDTO;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListResponse;
import com.fullhouse.utilities.AppConfig;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * The type Business card popup controller.
 */
public class BusinessCardPopupController implements Initializable {
    private BusinessInListDTO business;

    @FXML
    private ImageView businessLogoView;

    @FXML
    private Text businessNameField;

    @FXML
    private Text businessAddressField;

    @FXML
    private Text businessPhoneNumberField;

    @FXML
    private VBox appliedSurveysContainer;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Gets business.
     *
     * @return the business
     */
    public BusinessInListDTO getBusiness() {
        return business;
    }

    /**
     * Sets business.
     *
     * @param business the business
     */
    public void setBusiness(BusinessInListDTO business) {
        this.business = business;
        businessNameField.setText(business.getName());
        businessAddressField.setText(business.getAddress());
        businessPhoneNumberField.setText(business.getPhoneNumber());

        if (business.getImageURL() != null && !business.getImageURL().isEmpty()) {
            businessLogoView.setImage(new Image(AppConfig.getServerIP() + business.getImageURL(), true));
        }

        appliedSurveysContainer.getChildren().clear();
        fetchSurveys(business.getId());
    }

    private void fetchSurveys(long businessId) {
        Thread.ofVirtual().start(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(AppConfig.getServerIP() + "/survey/getlist?businessId=" + businessId))
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    SurveyListResponse surveyListResponse = mapper.readValue(response.body(), SurveyListResponse.class);
                    Platform.runLater(() -> {
                        appliedSurveysContainer.getChildren().clear();
                        for (SurveyInListDTO survey : surveyListResponse.getSurveys()) {
                            appliedSurveysContainer.getChildren().add(buildSurveyCard(survey));
                        }
                    });
                }
            } catch (Exception e) {
                System.out.println("fetchSurveys error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        });
    }

    private VBox buildSurveyCard(SurveyInListDTO survey) {
        VBox card = new VBox(4);
        card.getStyleClass().add("businessCard");

        HBox nameAndScore = new HBox();

        Text surveyName = new Text(survey.getSurveyName());
        surveyName.setStyle("-fx-font-weight: bold;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Text overallScore = new Text("Score: " + survey.getOverallScore());
        overallScore.setStyle("-fx-fill: #1a8cff;");

        Text expandIcon = new Text("->");
        nameAndScore.getChildren().addAll(surveyName, spacer, overallScore, expandIcon);

        Text uses = new Text("Uses: " + survey.getPopularity());
        uses.setStyle("-fx-fill: #718096;");

        VBox questionsDrawer = new VBox(4);
        questionsDrawer.setVisible(false);
        questionsDrawer.setManaged(false);
        questionsDrawer.setStyle("-fx-padding: 8 0 0 12;");

        card.getChildren().addAll(nameAndScore, uses, questionsDrawer);
        card.setStyle(card.getStyle() + "-fx-cursor: hand;");

        card.setOnMouseClicked(event -> {
            if (questionsDrawer.isVisible()) {
                questionsDrawer.setVisible(false);
                questionsDrawer.setManaged(false);
                expandIcon.setText("->");
            } 
            else {
                expandIcon.setText("˅");

                if (questionsDrawer.getChildren().isEmpty()) {
                    Text loading = new Text("Loading questions...");
                    loading.setStyle("-fx-fill: #718096;");
                    questionsDrawer.getChildren().add(loading);

                    questionsDrawer.setVisible(true);
                    questionsDrawer.setManaged(true);

                    Thread.ofVirtual().start(() -> {
                        try {
                            ParentSurveySingularQuestionsRequest dto = new ParentSurveySingularQuestionsRequest(survey.getParentSurveyId());
                            String json = mapper.writeValueAsString(dto);

                            HttpClient httpClient = HttpClient.newHttpClient();
                            HttpRequest request = HttpRequest.newBuilder()
                                    .uri(new URI(AppConfig.getServerIP() + "/parent-survey/get-singular"))
                                    .header("Content-Type", "application/json")
                                    .POST(HttpRequest.BodyPublishers.ofString(json))
                                    .build();

                            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                            if (response.statusCode() == 200 && !response.body().isBlank()) {
                                ParentSurveySingularQuestionsResponse data = mapper.readValue(response.body(), ParentSurveySingularQuestionsResponse.class);
                                Platform.runLater(() -> {
                                    questionsDrawer.getChildren().clear();

                                    Text header = new Text("Questions:");
                                    header.setStyle("-fx-font-weight: bold;");
                                    questionsDrawer.getChildren().add(header);

                                    for (int i = 0; i < data.getQuestions().size(); i++) {
                                        Text q = new Text((i + 1) + ". " + data.getQuestions().get(i));
                                        q.setStyle("-fx-fill: #4a5568;");
                                        
                                        questionsDrawer.getChildren().add(q);
                                    }
                                });
                            }
                        } catch (Exception e) {
                            Platform.runLater(() -> {
                                questionsDrawer.getChildren().clear();
                                questionsDrawer.getChildren().add(new Text("Failed to load questions."));
                            });
                        }
                    });
                } else {
                    questionsDrawer.setVisible(true);
                    questionsDrawer.setManaged(true);
                }
            }
        });

        return card;
    }
}
