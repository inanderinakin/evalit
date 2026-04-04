package com.fullhouse.controllers;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.DTOs.BusinessDTOs.BusinessInListDTO;
import com.fullhouse.DTOs.SurveyDTOs.SurveyInListDTO;
import com.fullhouse.DTOs.SurveyDTOs.SurveyListResponse;

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
            businessLogoView.setImage(new Image("http://localhost:8080" + business.getImageURL(), true));
        }

        appliedSurveysContainer.getChildren().clear();
        fetchSurveys(business.getId());
    }

    private void fetchSurveys(long businessId) {
        Thread.ofVirtual().start(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/survey/getlist?businessId=" + businessId))
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
        VBox card = new VBox();

        HBox nameAndScore = new HBox();
        Text surveyName = new Text(survey.getSurveyName());
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Text overallScore = new Text("Score: " + survey.getOverallScore()); 
        nameAndScore.getChildren().addAll(surveyName, spacer, overallScore);

        Text uses = new Text("Uses: " + survey.getPopularity());

        card.getChildren().addAll(nameAndScore, uses);
        return card;
    }
}
