package com.fullhouse.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.LoginDTOs.LoginSuccessResponse;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.Desktop;

public class LoginPageController {

    private static final ObjectMapper mapper = new ObjectMapper();

    @FXML private StackPane slideshowPane;

    private Image[] images;
    private int currentIndex = 0;

    @FXML
    private void initialize() {
        images = new Image[] {
            new Image(getClass().getResourceAsStream("/images/loginPage1.png")),
            new Image(getClass().getResourceAsStream("/images/loginPage2.png")),
            new Image(getClass().getResourceAsStream("/images/loginPage3.png"))
        };

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(slideshowPane.widthProperty());
        clip.heightProperty().bind(slideshowPane.heightProperty());
        slideshowPane.setClip(clip);

        ImageView first = createImageView(images[0]);
        slideshowPane.getChildren().add(first);

        Timeline autoSlide = new Timeline(new KeyFrame(Duration.seconds(5), e -> slideToNext()));
        autoSlide.setCycleCount(Timeline.INDEFINITE);
        autoSlide.play();
    }

    private void slideToNext() {
        int nextIndex = (currentIndex + 1) % images.length;
        ImageView current = (ImageView) slideshowPane.getChildren().get(0);
        ImageView next = createImageView(images[nextIndex]);
        next.setTranslateX(slideshowPane.getWidth());
        slideshowPane.getChildren().add(next);

        Timeline slide = new Timeline(
            new KeyFrame(Duration.millis(600),
                new KeyValue(current.translateXProperty(), -slideshowPane.getWidth()),
                new KeyValue(next.translateXProperty(), 0)
            )
        );
        slide.setOnFinished(e -> slideshowPane.getChildren().remove(current));
        slide.play();
        currentIndex = nextIndex;
    }

    private ImageView createImageView(Image image) {
        ImageView iv = new ImageView(image);
        iv.fitWidthProperty().bind(slideshowPane.widthProperty());
        iv.fitHeightProperty().bind(slideshowPane.heightProperty());
        iv.setPreserveRatio(false);
        return iv;
    }

    @FXML
    private void handleLogin() throws IOException, URISyntaxException {
        // If desktop is supported, open this link in the browser.
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("http://localhost:8080/oauth2/authorization/google"));
        }
        // We use threads because we wait (max 60 seconds) for the server-side to return the JSON of the user, and this makes
        // our application freeze (max 60 seconds). By running these codes in a separate thread apart from the application
        // thread, we keep the application thread working, so no freezing.
        Thread loginThread = new Thread(() -> {
            try {
                HttpClient httpClient = HttpClient.newHttpClient();
                boolean isResponse = false;
                for (int i = 0; i < 60 && !isResponse; i++) {
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://localhost:8080/loginSuccess/client"))
                        .header("Accept", "application/json")
                        .build();

                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                    if (response.statusCode() == 200) {
                        String responseBody = response.body();
                        if (!responseBody.isEmpty()) {
                            LoginSuccessResponse loggedUser = mapper.readValue(responseBody, LoginSuccessResponse.class);
                            App.setGoogleSub(loggedUser.getGoogleSub());
                            App.setUserName(loggedUser.getName());
                            App.setUserEmail(loggedUser.getEmail());
                            App.setProfilePictureURL(loggedUser.getProfilePictureURL());
                            App.setBusinessOwner(loggedUser.isBusinessOwner());
                            Platform.runLater(() -> {
                                try {
                                    App.setRoot("homePage");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            isResponse = true; 
                        }
                    }
                    Thread.sleep(1000);
                }
            } 
            catch (URISyntaxException e) {
                e.printStackTrace();
            } 
            catch (IOException e) {
                e.printStackTrace();
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        loginThread.start();
    }
}
