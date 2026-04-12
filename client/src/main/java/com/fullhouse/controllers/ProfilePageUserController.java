package com.fullhouse.controllers;

import com.fullhouse.utilities.AppConfig;
import java.net.URI;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The type Profile page user controller.
 */
public class ProfilePageUserController implements Initializable{
    @FXML
    private ImageView profilePictureField;

    @FXML
    private Text userNameLabel;

    @FXML
    private Text userEmailLabel;

    @FXML
    private Text userPhoneLabel;

    @FXML
    private VBox parentSurveysContainer;

    private List<ParentSurveySingular> parentSurveyList;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String profilePictureURL = App.getProfilePictureURL();
        String userName = App.getUserName();
        String userEmail = App.getUserEmail();

        Circle clip = new Circle(75, 75, 75);
        profilePictureField.setClip(clip); 

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

        String phone = App.getUserPhoneNumber();
        if (phone != null && !phone.isBlank()) {
            userPhoneLabel.setText(phone);
        }

        getSurveys();
    }

    /**
     * Gets surveys.
     */
    @FXML
    public void getSurveys() {
        Thread.ofVirtual().start(() -> {
            try {
                ParentSurveyListRequest parentSurveyDTO = new ParentSurveyListRequest(App.getGoogleSub());
                String json = mapper.writeValueAsString(parentSurveyDTO);

                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(AppConfig.getServerIP() + "/parent-survey/get-list"))
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

    /**
     * Build survey card v box.
     *
     * @param parentSurvey the parent survey
     * @return the v box
     */
    @FXML
    public VBox buildSurveyCard(ParentSurveySingular parentSurvey) {
        VBox card = new VBox(4);
        card.getStyleClass().add("businessCard");
        HBox nameAndID = new HBox();

        Text parentSurveyName = new Text(parentSurvey.getName());
        parentSurveyName.setStyle("-fx-font-weight: bold;");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Text parentSurveyID = new Text("Survey ID: " + parentSurvey.getId());
        parentSurveyID.setStyle("-fx-fill: #1a8cff;");
        nameAndID.getChildren().addAll(parentSurveyName, spacer, parentSurveyID);

        String category = parentSurvey.getCategory();
        Text parentSurveyCategory = new Text("Survey Category: " + category);
        parentSurveyCategory.setStyle("-fx-fill: #718096;");
        Text parentSurveyNumOfUse = new Text("Number of uses: " + parentSurvey.getPopularity());
        parentSurveyNumOfUse.setStyle("-fx-fill: #718096;");

        card.getChildren().addAll(nameAndID, parentSurveyCategory, parentSurveyNumOfUse);
        card.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/surveyMarketplacePopup.fxml"));
                Parent root = loader.load();
                SurveyMarketplacePopupController controller = loader.getController();
                controller.setSurvey(parentSurvey);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Survey Details");
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return card;
    }
}

