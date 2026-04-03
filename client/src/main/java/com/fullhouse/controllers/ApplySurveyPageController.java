package com.fullhouse.controllers;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByOwnerRequest;
import com.fullhouse.DTOs.BusinessDTOs.BusinessGetListByOwnerResponse;
import com.fullhouse.DTOs.BusinessDTOs.BusinessInListDTO;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyListResponse;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingular;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsResponse;
import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyRequest;
import com.fullhouse.DTOs.SurveyDTOs.SurveyApplyResponse;
import com.fullhouse.utilities.QRCodeGenerator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ApplySurveyPageController implements Initializable {

    @FXML private ChoiceBox<String> businessChoiceBox;
    @FXML private TextField searchField;
    @FXML private VBox surveysContainer;
    @FXML private VBox qrSection;
    @FXML private ImageView qrCodeView;
    @FXML private Label statusLabel;

    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<String, Long> businessNameToId = new HashMap<>();
    private List<ParentSurveySingular> allSurveys = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBusinesses();
        loadSurveys("");
    }

    private void loadBusinesses() {
        Thread.ofVirtual().start(() -> {
            try {
                BusinessGetListByOwnerRequest dto = new BusinessGetListByOwnerRequest(App.getGoogleSub());
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/business/getlist/owner"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(dto)))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    BusinessGetListByOwnerResponse businessResponse = mapper.readValue(response.body(), BusinessGetListByOwnerResponse.class);
                    List<BusinessInListDTO> businesses = businessResponse.getBusinesses();

                    Platform.runLater(() -> {
                        String preSelectName = null;
                        for (BusinessInListDTO business : businesses) {
                            businessNameToId.put(business.getName(), business.getId());
                            businessChoiceBox.getItems().add(business.getName());
                            if (business.getId() == App.getPreSelectedBusinessId()) {
                                preSelectName = business.getName();
                            }
                        }
                        if (!businessChoiceBox.getItems().isEmpty()) {
                            if (preSelectName != null) {
                                businessChoiceBox.setValue(preSelectName);
                            } else {
                                businessChoiceBox.setValue(businessChoiceBox.getItems().get(0));
                            }
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void loadSurveys(String nameFilter) {
        Thread.ofVirtual().start(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                String jsonBody = String.format("{\"name\":\"%s\",\"category\":\"\"}", nameFilter);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/parent-survey/getlist/name-category-search"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    ParentSurveyListResponse surveyResponse = mapper.readValue(response.body(), ParentSurveyListResponse.class);
                    allSurveys = surveyResponse.getParentSurveySingularList();

                    Platform.runLater(() -> {
                        surveysContainer.getChildren().clear();
                        for (ParentSurveySingular survey : allSurveys) {
                            surveysContainer.getChildren().add(buildSurveyCard(survey));
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private HBox buildSurveyCard(ParentSurveySingular survey) {
        HBox card = new HBox(10);
        card.getStyleClass().add("businessCard");

        CheckBox checkBox = new CheckBox();
        checkBox.setUserData(survey.getId());

        boolean isInCart = false;
        ArrayList<ParentSurveySingularQuestionsResponse> cart = App.getWillAppliedSurveys();
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId() == survey.getId()) {
                isInCart = true;
            }
        }

        if (isInCart) {
            checkBox.setSelected(true);
        }

        VBox info = new VBox(4);
        Label nameLabel = new Label(survey.getName());
        Label categoryLabel = new Label("Category: " + survey.getCategory());
        Label popularityLabel = new Label("Popularity: " + survey.getPopularity());
        info.getChildren().addAll(nameLabel, categoryLabel, popularityLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        card.getChildren().addAll(checkBox, info, spacer);
        return card;
    }

    @FXML
    private void handleSearch() {
        String filter;
        if (searchField.getText() == null) {
            filter = "";
        }
        else {
            filter = searchField.getText().trim();
        }
        
        loadSurveys(filter);
    }

    @FXML
    private void handleApply() {
        String selectedBusiness = businessChoiceBox.getValue();
        if (selectedBusiness == null || !businessNameToId.containsKey(selectedBusiness)) {
            return;
        }

        long businessId = businessNameToId.get(selectedBusiness);

        List<Long> selectedIds = new ArrayList<>();
        for (int i = 0; i < surveysContainer.getChildren().size(); i++) {
            if (surveysContainer.getChildren().get(i) instanceof HBox) {
                HBox card = (HBox) surveysContainer.getChildren().get(i);
                for (int j = 0; j < card.getChildren().size(); j++) {
                    if (card.getChildren().get(j) instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) card.getChildren().get(j);
                        if (checkBox.isSelected()) {
                            selectedIds.add((Long) checkBox.getUserData());
                        }
                    }
                }
            }
        }

        if (selectedIds.isEmpty()) {
            return;
        }

        SurveyApplyRequest applyRequest = new SurveyApplyRequest(businessId, selectedIds);

        statusLabel.setText("Generating QR code... Please wait");

        Thread.ofVirtual().start(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                String jsonBody = mapper.writeValueAsString(applyRequest);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/survey/apply"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    SurveyApplyResponse applyResponse = mapper.readValue(response.body(), SurveyApplyResponse.class);
                    String formLink = applyResponse.getLink();

                    if (formLink != null && !formLink.isBlank()) {
                        javafx.scene.image.Image qrImage = QRCodeGenerator.createQRImage(formLink);
                        Platform.runLater(() -> {
                            qrCodeView.setImage(qrImage);
                            qrSection.setVisible(true);
                            qrSection.setManaged(true);
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
