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
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingular;

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

public class ProfilePageUserController implements Initializable{
    @FXML
    private ImageView profilePictureField;

    @FXML
    private Text userNameLabel;

    @FXML
    private Text userEmailLabel;

    @FXML
    private VBox parentSurveysContainer;

    private List<ParentSurveySingular> parentSurveyList;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String profilePictureURL = App.getProfilePictureURL();
        String userName = App.getUserName();
        String userEmail = App.getUserEmail();

        if (profilePictureURL == null || profilePictureURL.isBlank()) {
            profilePictureField.setImage(new Image("https://picsum.photos/200"));
        }
        else {
            profilePictureField.setImage(new Image(profilePictureURL));

        }

        if (userName == null || userName.isBlank()) {
            userNameLabel.setText("User Name");
        }
        else {
            userNameLabel.setText(userName);
        }

        if (userEmail == null || userEmail.isBlank()) {
            userEmailLabel.setText("User Email");
        }
        else {
            userEmailLabel.setText(userEmail);
        }

        getSurveys();
    }

    @FXML
    public void getSurveys() {
        Thread.ofVirtual().start(() -> {
            try {
                ParentSurveyListRequest parentSurveyDTO = new ParentSurveyListRequest(App.getGoogleSub());
                String json = mapper.writeValueAsString(parentSurveyDTO);

                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/parent-survey/get-list"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    ParentSurveyListResponse parentSurveyResponse = mapper.readValue(response.body(), ParentSurveyListResponse.class);
                    parentSurveyList = parentSurveyResponse.getParentSurveySingularList();

                    Platform.runLater(() -> {
                        parentSurveysContainer.getChildren().clear();
                        for (ParentSurveySingular parentSurvey : parentSurveyList) {
                            parentSurveysContainer.getChildren().add(buildSurveyCard(parentSurvey));
                        }
                    });
                }
            } catch (Exception e) {
                System.out.println("getSurveys error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        });
    }

    @FXML
    public VBox buildSurveyCard(ParentSurveySingular parentSurvey) {
        VBox card = new VBox();
        HBox nameAndID = new HBox();

        Text parentSurveyName = new Text(parentSurvey.getName());
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Text parentSurveyID = new Text("Survey ID: " + parentSurvey.getId());
        nameAndID.getChildren().addAll(parentSurveyName, spacer, parentSurveyID);

        Text parentSurveyCategory = new Text("Survey Category: " + parentSurvey.getCategory());
        Text parentSurveyNumOfUse = new Text("Number of uses: " + parentSurvey.getPopularity());

        card.getChildren().addAll(nameAndID, parentSurveyCategory, parentSurveyNumOfUse);
        return card;
    }
}

