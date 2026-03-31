package com.fullhouse.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        businessNameField.textProperty().addListener((obs, oldVal, newVal) -> businessName = newVal);
        businessEmailField.textProperty().addListener((obs, oldVal, newVal) -> businessEmail = newVal);
        businessAddressField.textProperty().addListener((obs, oldVal, newVal) -> businessAddress = newVal);
        businessPhoneNumberField.textProperty().addListener((obs, oldVal, newVal) -> businessPhoneNumber = newVal);
    }

    @FXML
    public void sendVerificationCode() {
        System.out.println(businessName + businessEmail + businessAddress + businessPhoneNumber);
    }
}
