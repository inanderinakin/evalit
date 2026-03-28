package com.fullhouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static String googleSub;
    private static String userName;
    private static String userEmail;
    private static String profilePictureURL;

    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Nunito-ExtraBold.ttf"), 14);

        scene = new Scene(loadFXML("loginPage"), 640, 480);
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

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}