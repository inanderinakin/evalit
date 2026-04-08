package com.fullhouse.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyReportRequest;
import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveyReportResponse;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The type Report survey controller.
 */
public class ReportSurveyController {

    @FXML private TextArea reportField;

    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    private void handleSubmit() {
        long surveyId = App.getPreSelectedSurveyId();
        String reportText = reportField.getText();
        Stage stage = (Stage) reportField.getScene().getWindow();
        Thread.ofVirtual().start(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                ParentSurveyReportRequest dto = new ParentSurveyReportRequest(surveyId, reportText);
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://31.57.156.36:8080/parent-survey/report"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(dto)))
                    .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                System.out.println(response.statusCode());

                if (response.statusCode() == 200 && !response.body().isBlank()) {
                    ParentSurveyReportResponse reportResponse = mapper.readValue(response.body(), ParentSurveyReportResponse.class);
                    if (reportResponse.isSuccess()) {
                        Platform.runLater(() -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullhouse/reportSuccessPopup.fxml"));
                                Parent root = loader.load();
                                Stage successStage = new Stage();
                                successStage.initModality(Modality.APPLICATION_MODAL);
                                successStage.setTitle("Report Submitted");
                                successStage.setScene(new Scene(root, 400, 300));
                                successStage.showAndWait();
                                stage.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}