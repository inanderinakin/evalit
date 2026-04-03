package com.fullhouse.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.SurveyDTOs.ParentSurveyCreateRequest;
import com.fullhouse.Enums.CategoryEnum;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateSurveyPageController implements Initializable{    
    @FXML
    private TextField surveyTitleField;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

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
        String surveyTitle = surveyTitleField.getText().trim();
        String surveyCategory = categoryChoiceBox.getValue().toString();

        List<String> questions = new ArrayList<>();

        for (Node node : questionsContainer.getChildren()) {
            if (!(node instanceof VBox)) {
                continue;
            }
            VBox questionBox = (VBox) node;

            for (Node child : questionBox.getChildren()) {
                if (child instanceof TextField) {
                    TextField questionInput = (TextField) child;
                    String rawText = questionInput.getText();
                    String cleanedText = rawText.trim();

                    if (!cleanedText.isEmpty()) {
                        questions.add(cleanedText);
                    }
                }
            }
        }

        ParentSurveyCreateRequest parentSurveyDTO = new ParentSurveyCreateRequest(surveyTitle, surveyCategory, App.getGoogleSub(), questions);

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (CategoryEnum category : CategoryEnum.values()) {
            if (category == CategoryEnum.DEFAULT) {
                continue;
            }
            categoryChoiceBox.getItems().add(category.getDisplayedName());
        }
    }
}
