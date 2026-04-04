package com.fullhouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import com.fullhouse.DTOs.ParentSurveyDTOs.ParentSurveySingularQuestionsResponse;

/**
 * The type app.
 */
public class App extends Application {

    private static Scene scene;
    private static String googleSub;
    private static String userName;
    private static String userEmail;
    private static String profilePictureURL;
    private static boolean isBusinessOwner;

    private static long preSelectedSurveyId = -1;
    private static long preSelectedBusinessId = -1;
    private static String preSelectedCity = "";
    private static String preSelectedCategory = "";
    private static String preSelectedSurveyName = "";

    private static String pageName;

    private static ArrayList<ParentSurveySingularQuestionsResponse> willAppliedSurveys;

    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Nunito-ExtraBold.ttf"), 14);

        scene = new Scene(loadFXML("loginPage"), 1280, 960);
        stage.setScene(scene);
        stage.setTitle("Eval-it!");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/appIcon.png")));

        willAppliedSurveys = new ArrayList<>();
        stage.show();
    }

    /**
     * Sets root.
     *
     * @param fxml the fxml
     * @throws IOException the ıo exception
     */
    public static void setRoot(String fxml) throws IOException {
        pageName = fxml;
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Gets google sub.
     *
     * @return the google sub
     */
    public static String getGoogleSub() {
        return googleSub;
    }

    /**
     * Sets google sub.
     *
     * @param googleSub the google sub
     */
    public static void setGoogleSub(String googleSub) {
        App.googleSub = googleSub;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public static void setUserName(String userName) {
        App.userName = userName;
    }

    /**
     * Gets user email.
     *
     * @return the user email
     */
    public static String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets user email.
     *
     * @param userEmail the user email
     */
    public static void setUserEmail(String userEmail) {
        App.userEmail = userEmail;
    }

    /**
     * Gets profile picture url.
     *
     * @return the profile picture url
     */
    public static String getProfilePictureURL() {
        return profilePictureURL;
    }

    /**
     * Sets profile picture url.
     *
     * @param profilePictureURL the profile picture url
     */
    public static void setProfilePictureURL(String profilePictureURL) {
        App.profilePictureURL = profilePictureURL;
    }

    /**
     * Is business owner boolean.
     *
     * @return the boolean
     */
    public static boolean isBusinessOwner() {
        return isBusinessOwner;
    }

    /**
     * Sets business owner.
     *
     * @param isBusinessOwner the is business owner
     */
    public static void setBusinessOwner(boolean isBusinessOwner) {
        App.isBusinessOwner = isBusinessOwner;
    }

    /**
     * Gets pre selected survey ıd.
     *
     * @return the pre selected survey ıd
     */
    public static long getPreSelectedSurveyId() {
        return preSelectedSurveyId;
    }

    /**
     * Sets pre selected survey ıd.
     *
     * @param id the id
     */
    public static void setPreSelectedSurveyId(long id) {
        App.preSelectedSurveyId = id;
    }

    /**
     * Gets pre selected business ıd.
     *
     * @return the pre selected business ıd
     */
    public static long getPreSelectedBusinessId() {
        return preSelectedBusinessId;
    }

    /**
     * Sets pre selected business ıd.
     *
     * @param id the id
     */
    public static void setPreSelectedBusinessId(long id) {
        App.preSelectedBusinessId = id;
    }

    /**
     * Gets pre selected city.
     *
     * @return the pre selected city
     */
    public static String getPreSelectedCity() {
        return preSelectedCity;
    }

    /**
     * Sets pre selected city.
     *
     * @param preSelectedCity the pre selected city
     */
    public static void setPreSelectedCity(String preSelectedCity) {
        App.preSelectedCity = preSelectedCity;
    }

    /**
     * Gets pre selected category.
     *
     * @return the pre selected category
     */
    public static String getPreSelectedCategory() {
        return preSelectedCategory;
    }

    /**
     * Sets pre selected category.
     *
     * @param preSelectedCategory the pre selected category
     */
    public static void setPreSelectedCategory(String preSelectedCategory) {
        App.preSelectedCategory = preSelectedCategory;
    }

    /**
     * Gets pre selected survey name.
     *
     * @return the pre selected survey name
     */
    public static String getPreSelectedSurveyName() {
        return preSelectedSurveyName;
    }

    /**
     * Sets pre selected survey name.
     *
     * @param preSelectedSurveyName the pre selected survey name
     */
    public static void setPreSelectedSurveyName(String preSelectedSurveyName) {
        App.preSelectedSurveyName = preSelectedSurveyName;
    }

    /**
     * Gets will applied surveys.
     *
     * @return the will applied surveys
     */
    public static ArrayList<ParentSurveySingularQuestionsResponse> getWillAppliedSurveys() {
        return willAppliedSurveys;
    }

    /**
     * Sets will applied surveys.
     *
     * @param willAppliedSurveys the will applied surveys
     */
    public static void setWillAppliedSurveys(ArrayList<ParentSurveySingularQuestionsResponse> willAppliedSurveys) {
        App.willAppliedSurveys = willAppliedSurveys;
    }

    /**
     * Gets page name.
     *
     * @return the page name
     */
    public static String getPageName() {
        return pageName;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }
}