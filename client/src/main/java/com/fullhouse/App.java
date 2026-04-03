package com.fullhouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static String googleSub;
    private static String userName;
    private static String userEmail;
    private static String profilePictureURL;
    private static boolean isBusinessOwner;
    private static long preSelectedSurveyId = -1;
    private static String preSelectedCity = "";
    private static String preSelectedCategory = "";

    public static long getPreSelectedSurveyId() {
        return preSelectedSurveyId;
    }

    public static void setPreSelectedSurveyId(long id) {
        App.preSelectedSurveyId = id;
    }


    public static String getPreSelectedCity() {
        return preSelectedCity;
    }

    public static void setPreSelectedCity(String preSelectedCity) {
        App.preSelectedCity = preSelectedCity;
    }

    public static String getPreSelectedCategory() {
        return preSelectedCategory;
    }

    public static void setPreSelectedCategory(String preSelectedCategory) {
        App.preSelectedCategory = preSelectedCategory;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Nunito-ExtraBold.ttf"), 14);

        scene = new Scene(loadFXML("loginPage"), 1280, 960);
        stage.setScene(scene);
        stage.setTitle("Eval-it!");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static String getGoogleSub() {
        return googleSub;
    }

    public static void setGoogleSub(String googleSub) {
        App.googleSub = googleSub;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        App.userName = userName;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        App.userEmail = userEmail;
    }

    public static String getProfilePictureURL() {
        return profilePictureURL;
    }

    public static void setProfilePictureURL(String profilePictureURL) {
        App.profilePictureURL = profilePictureURL;
    }

    public static boolean isBusinessOwner() {
        return isBusinessOwner;
    }

    public static void setBusinessOwner(boolean isBusinessOwner) {
        App.isBusinessOwner = isBusinessOwner;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}