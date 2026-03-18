package com.fullhouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.DTOs.ParentSurveyCreateRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateSurveyPageController {    
    @FXML
    private TextField surveyName;

    @FXML
    private VBox questionsContainer;

    private int questionCount = 2;
    private ArrayList<TextField> questionFields = new ArrayList<>();

    @FXML
    private void addQuestion() {
        questionCount++;

        VBox questionBox = new VBox(4);
        Label questionLabel = new Label("Question " + questionCount);
        TextField questionInput = new TextField();
        questionInput.setPromptText("Please type...");

        questionFields.add(questionInput);
        questionBox.getChildren().addAll(questionLabel, questionInput);
        questionsContainer.getChildren().add(questionBox);
    }

    @FXML
    private void createSurvey() {
        String surveyTitle = surveyName.getText().trim();

        List<String> questions = new ArrayList<>();

        for (Node node : questionsContainer.getChildren()) {
            if (!(node instanceof VBox questionBox)) {
                continue;
            }

            for (Node child : questionBox.getChildren()) {
                if (child instanceof TextField questionInput) {
                    String rawText = questionInput.getText();
                    String cleanedText = rawText.trim();

                    if (!cleanedText.isEmpty()) {
                        questions.add(cleanedText);
                    }
                }
            }
        }

        ParentSurveyCreateRequest parentSurveyDTO = new ParentSurveyCreateRequest(surveyTitle, (long) 0, questions);

        ObjectMapper mapper = new ObjectMapper();
        
        try {
            String json = mapper.writeValueAsString(parentSurveyDTO);
            System.out.println(json);

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/parent-survey/create"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
            
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Item sent succesfully");
            }
            else {
                System.out.println(response.statusCode());
            }
        } 
        catch (JsonProcessingException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
