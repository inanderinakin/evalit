package com.fullhouse.controllers;

import com.fullhouse.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class ReportSuccessController {

    @FXML private Button btnReturnHome;

    @FXML
    private void handleReturnHome() throws IOException {
        Stage stage = (Stage) btnReturnHome.getScene().getWindow();
        stage.close();
        App.setRoot("homePage");
    }
}