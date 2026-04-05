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

public class App extends Application {

    private static Scene scene;
    private static String googleSub;
    private static String userName;
    private static String userEmail;
    private static String profilePictureURL;
    private static boolean isBusinessOwner;
    private static boolean isAdmin;
    private static String userPhoneNumber;

    private static long preSelectedSurveyId = -1;
    private static long preSelectedParentSurveyId = -1;
    private static long preSelectedBusinessId = -1;
    private static String preSelectedCity = "";
    private static String preSelectedCategory = "";
    private static String preSelectedSurveyName = "";
    private static String preSelectedBusinessPhone = "";
    private static String preSelectedBusinessName = "";
    private static String preSelectedBusinessAddress = "";
    private static String preSelectedBusinessCity = "";
    private static float preSelectedOverallScore = 0f;
    private static java.util.List<Float> preSelectedScoresOfQuestions;
    private static int preSelectedPopularity = 0;
    private static int preSelectedResponseCount = 0;

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

    public static void setRoot(String fxml) throws IOException {
        pageName = fxml;
        scene.setRoot(loadFXML(fxml));
    }

    public static String getGoogleSub() { return googleSub; }
    public static void setGoogleSub(String googleSub) { App.googleSub = googleSub; }

    public static String getUserName() { return userName; }
    public static void setUserName(String userName) { App.userName = userName; }

    public static String getUserEmail() { return userEmail; }
    public static void setUserEmail(String userEmail) { App.userEmail = userEmail; }

    public static String getProfilePictureURL() { return profilePictureURL; }
    public static void setProfilePictureURL(String profilePictureURL) { App.profilePictureURL = profilePictureURL; }

    public static boolean isBusinessOwner() { return isBusinessOwner; }
    public static void setBusinessOwner(boolean isBusinessOwner) { App.isBusinessOwner = isBusinessOwner; }

    public static long getPreSelectedSurveyId() { return preSelectedSurveyId; }
    public static void setPreSelectedSurveyId(long id) { App.preSelectedSurveyId = id; }

    public static long getPreSelectedParentSurveyId() { return preSelectedParentSurveyId; }
    public static void setPreSelectedParentSurveyId(long id) { App.preSelectedParentSurveyId = id; }

    public static long getPreSelectedBusinessId() { return preSelectedBusinessId; }
    public static void setPreSelectedBusinessId(long id) { App.preSelectedBusinessId = id; }

    public static String getPreSelectedCity() { return preSelectedCity; }
    public static void setPreSelectedCity(String preSelectedCity) { App.preSelectedCity = preSelectedCity; }

    public static String getPreSelectedCategory() { return preSelectedCategory; }
    public static void setPreSelectedCategory(String preSelectedCategory) { App.preSelectedCategory = preSelectedCategory; }

    public static String getPreSelectedSurveyName() { return preSelectedSurveyName; }
    public static void setPreSelectedSurveyName(String preSelectedSurveyName) { App.preSelectedSurveyName = preSelectedSurveyName; }
  
    public static boolean isAdmin() { return isAdmin; }
    public static void setAdmin(boolean isAdmin) { App.isAdmin = isAdmin; }

    public static String getUserPhoneNumber() { return userPhoneNumber; }
    public static void setUserPhoneNumber(String phone) { App.userPhoneNumber = phone; }

    public static String getPreSelectedBusinessPhone() { return preSelectedBusinessPhone; }
    public static void setPreSelectedBusinessPhone(String phone) { App.preSelectedBusinessPhone = phone; }

    public static String getPreSelectedBusinessName() { return preSelectedBusinessName; }
    public static void setPreSelectedBusinessName(String name) { App.preSelectedBusinessName = name; }

    public static String getPreSelectedBusinessAddress() { return preSelectedBusinessAddress; }
    public static void setPreSelectedBusinessAddress(String address) { App.preSelectedBusinessAddress = address; }

    public static String getPreSelectedBusinessCity() { return preSelectedBusinessCity; }
    public static void setPreSelectedBusinessCity(String city) { App.preSelectedBusinessCity = city; }

    public static float getPreSelectedOverallScore() { return preSelectedOverallScore; }
    public static void setPreSelectedOverallScore(float score) { App.preSelectedOverallScore = score; }

    public static int getPreSelectedPopularity() { return preSelectedPopularity; }
    public static void setPreSelectedPopularity(int popularity) { App.preSelectedPopularity = popularity; }

    public static int getPreSelectedResponseCount() { return preSelectedResponseCount; }
    public static void setPreSelectedResponseCount(int count) { App.preSelectedResponseCount = count; }

    public static java.util.List<Float> getPreSelectedScoresOfQuestions() { return preSelectedScoresOfQuestions; }
    public static void setPreSelectedScoresOfQuestions(java.util.List<Float> scores) { App.preSelectedScoresOfQuestions = scores; }

    public static ArrayList<ParentSurveySingularQuestionsResponse> getWillAppliedSurveys() { return willAppliedSurveys; }
    public static void setWillAppliedSurveys(ArrayList<ParentSurveySingularQuestionsResponse> willAppliedSurveys) { App.willAppliedSurveys = willAppliedSurveys; }

    public static String getPageName() { return pageName; }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}