package com.fullhouse;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateSurveyPageController {
    @FXML
    private TextField surveyName;

    @FXML
    private VBox questionsContainer;

    private int questionCount = 2;

    @FXML
    private void addQuestion() {
        questionCount++;

        VBox questionBox = new VBox(4);
        Label questionLabel = new Label("Question " + questionCount);
        TextField questionInput = new TextField();
        questionInput.setPromptText("Please type...");

        questionBox.getChildren().addAll(questionLabel, questionInput);
        questionsContainer.getChildren().add(questionBox);
    }
}
