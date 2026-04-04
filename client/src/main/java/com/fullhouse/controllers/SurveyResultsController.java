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
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsResponse;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SurveyResultsController implements Initializable {

    @FXML private Label surveyNameLabel;
    @FXML private Label surveyIdLabel;
    @FXML private Label businessNameLabel;
    @FXML private Label addressLabel;
    @FXML private Label phoneLabel;
    @FXML private Label categoriesLabel;
    @FXML private Label numOfUsesLabel;
    @FXML private Label overallScoreLabel;
    @FXML private VBox questionsContainer;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        surveyNameLabel.setText(App.getPreSelectedSurveyName());
        surveyIdLabel.setText("Survey ID: S" + App.getPreSelectedSurveyId());
        businessNameLabel.setText(App.getPreSelectedBusinessName());
        addressLabel.setText("Address: " + App.getPreSelectedBusinessAddress()
                + ", " + App.getPreSelectedBusinessCity());
        phoneLabel.setText("Phone: " + App.getPreSelectedBusinessPhone());
        overallScoreLabel.setText(String.format("%.2f", App.getPreSelectedOverallScore()));
        numOfUsesLabel.setText("Number of uses: " + App.getPreSelectedPopularity());

        loadQuestions(App.getPreSelectedParentSurveyId());
    }

    private void loadQuestions(long parentSurveyId) {
        Thread.ofVirtual().start(() -> {
            try {
                ParentSurveySingularQuestionsRequest req =
                        new ParentSurveySingularQuestionsRequest(parentSurveyId);
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/parent-survey/get-singular"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(req)))
                        .build();
                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    ParentSurveySingularQuestionsResponse detail =
                            mapper.readValue(response.body(), ParentSurveySingularQuestionsResponse.class);
                    Platform.runLater(() -> populateQuestionsAndCategory(detail));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void populateQuestionsAndCategory(ParentSurveySingularQuestionsResponse detail) {
        String category = detail.getCategory();
        if (category != null && !category.isBlank()) {
            categoriesLabel.setText("Categories: " + category + ".");
        }

        List<String> questions = detail.getQuestions();
        List<Float> scores = App.getPreSelectedScoresOfQuestions();
        questionsContainer.getChildren().clear();

        if (questions == null || questions.isEmpty()) {
            return;
        }

        for (int i = 0; i < questions.size(); i++) {
            float score = 0f;
            if (scores != null && i < scores.size()) {
                score = scores.get(i);
            }
            questionsContainer.getChildren().add(buildQuestionCard(questions.get(i), score));
        }
    }

    private VBox buildQuestionCard(String questionText, float score) {
        VBox card = new VBox(8.0);
        card.setStyle("-fx-background-color: #BEBEBE; -fx-background-radius: 14px; -fx-padding: 14px 18px;");

        Label questionLabel = new Label("Question : " + questionText);
        questionLabel.setStyle("-fx-font-size: 14px;");
        questionLabel.setWrapText(true);

        HBox averageRow = new HBox(6.0);

        Label averageText = new Label("Average : ");
        averageText.setStyle("-fx-font-size: 14px;");

        Label starLabel = new Label("\u2B50");
        starLabel.setStyle("-fx-font-size: 14px;");

        Label scoreLabel = new Label(String.format("%.2f", score));
        scoreLabel.setStyle("-fx-font-size: 14px;");

        averageRow.getChildren().addAll(averageText, starLabel, scoreLabel);
        card.getChildren().addAll(questionLabel, averageRow);
        return card;
    }
}