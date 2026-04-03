package com.fullhouse.controllers;

import java.io.IOException;
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
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingular;
import com.fullhouse.Enums.CategoryEnum;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class ProfilePageBusinessController implements Initializable {
    @FXML
    private ImageView profilePictureField;

    @FXML
    private Text userNameLabel;

    @FXML
    private Text userEmailLabel;

    @FXML
    private VBox parentSurveysContainer;

    @FXML
    private VBox businessesContainer;

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

        getSurveys();
        getBusinesses();
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

        String category = CategoryEnum.fromValue(parentSurvey.getCategory());
        Text parentSurveyCategory = new Text("Survey Category: " + category);
        Text parentSurveyNumOfUse = new Text("Number of uses: " + parentSurvey.getPopularity());

        card.getChildren().addAll(nameAndID, parentSurveyCategory, parentSurveyNumOfUse);
        card.getStyleClass().add("businessCard");
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

    private void getBusinesses() {
        Thread.ofVirtual().start(() -> {
            try {
                BusinessGetListByOwnerRequest dto = new BusinessGetListByOwnerRequest(App.getGoogleSub());
                String json = mapper.writeValueAsString(dto);

                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/business/getlist/owner"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    BusinessGetListByOwnerResponse businessResponse = mapper.readValue(response.body(), BusinessGetListByOwnerResponse.class);
                    List<BusinessInListDTO> businesses = businessResponse.getBusinesses();

                    Platform.runLater(() -> {
                        businessesContainer.getChildren().clear();
                        for (BusinessInListDTO business : businesses) {
                            businessesContainer.getChildren().add(buildBusinessCard(business));
                        }
                    });
                }
            } catch (Exception e) {
                System.out.println("getBusinesses error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        });
    }

    private HBox buildBusinessCard(BusinessInListDTO business) {
        HBox card = new HBox(10);
        card.getStyleClass().add("businessCard");

        ImageView logoView = new ImageView();
        logoView.setFitHeight(60);
        logoView.setFitWidth(60);
        logoView.setPreserveRatio(true);
        if (business.getImageURL() != null && !business.getImageURL().isEmpty()) {
            logoView.setImage(new Image("http://localhost:8080" + business.getImageURL(), true));
        }

        VBox info = new VBox(4);
        Label nameLabel = new Label(business.getName());
        Label addressLabel = new Label(business.getAddress());
        Label phoneLabel = new Label(business.getPhoneNumber());
        Label scoreLabel = new Label("Score: " + String.format("%.1f", business.getAverageScore()));
        info.getChildren().addAll(nameLabel, addressLabel, phoneLabel, scoreLabel);
        HBox.setHgrow(info, Priority.ALWAYS);

        Button manageSurveysBtn = new Button("Manage Surveys");
        manageSurveysBtn.setOnAction(event -> {
            App.setPreSelectedBusinessId(business.getId());
            try {
                App.setRoot("applySurveyPage");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        card.getChildren().addAll(logoView, info, manageSurveysBtn);
        card.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/businessCardPopup.fxml"));
                Parent root = loader.load();
                BusinessCardPopupController controller = loader.getController();
                controller.setBusiness(business);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle(business.getName());
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return card;
    }
}
