package com.fullhouse.controllers;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.fxml.Initializable;

public class ParentSurveyQuestionsPageController implements Initializable{

    private long selectedParentSurveyID;

    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        HttpClient httpClient = HttpClient.newHttpClient();
        String jsonBody = "{\"parentSurveyID\":\"" + selectedParentSurveyID + "\"}";
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(""))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
    }



    public long getSelectedParentSurveyID() {
        return selectedParentSurveyID;
    }



    public void setSelectedParentSurveyID(long selectedParentSurveyID) {
        this.selectedParentSurveyID = selectedParentSurveyID;
    }
    
}
