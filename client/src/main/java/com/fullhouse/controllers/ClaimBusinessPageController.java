package com.fullhouse.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ClaimBusinessPageController implements Initializable {
    @FXML
    private TextField businessNameField;
    private String businessName;

    @FXML
    private TextField businessEmailField;
    private String businessEmail;

    @FXML
    private TextField businessAddressField;
    private String businessAddress;

    @FXML
    private TextField businessPhoneNumberField;
    private String businessPhoneNumber;

    @FXML
    private ImageView businessLogoField;
    private Image businessLogo;
    private File selectedLogoFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        businessNameField.textProperty().addListener((obs, oldVal, newVal) -> businessName = newVal);
        businessEmailField.textProperty().addListener((obs, oldVal, newVal) -> businessEmail = newVal);
        businessAddressField.textProperty().addListener((obs, oldVal, newVal) -> businessAddress = newVal);
        businessPhoneNumberField.textProperty().addListener((obs, oldVal, newVal) -> businessPhoneNumber = newVal);
    }

    @FXML
    public void chooseLogo() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Please choose a logo for your business");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            selectedLogoFile = file;
            businessLogo = new Image(file.toURI().toURL().toExternalForm());
            businessLogoField.setImage(businessLogo);
        }
    }

    @FXML
    public void sendVerificationCode() throws IOException {
        if (businessName != null && businessEmail != null && businessAddress != null & businessPhoneNumber!= null && selectedLogoFile != null) {
            Path logosDir = Paths.get("..", "shared", "src", "main", "resources", "logos");
            Files.createDirectories(logosDir);
            try (InputStream in = selectedLogoFile.toURI().toURL().openStream()) {
                Files.copy(in, logosDir.resolve(businessName + ".png"), StandardCopyOption.REPLACE_EXISTING);
            }
        }
        System.out.println(businessName + " " + businessEmail + " " + businessAddress + " " + businessPhoneNumber + " " + (businessLogo != null ? businessLogo.getUrl() : "no logo"));
    }
}
