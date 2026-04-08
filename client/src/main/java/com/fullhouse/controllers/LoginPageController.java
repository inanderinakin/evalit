package com.fullhouse.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullhouse.App;
import com.fullhouse.DTOs.LoginDTOs.LoginSuccessResponse;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.Desktop;

/**
 * The type Login page controller.
 */
public class LoginPageController {

    private static final ObjectMapper mapper = new ObjectMapper();

    @FXML
    private StackPane slideshowPane;

    @FXML
    private Label messageLabel;

    private Image[] images;
    private int currentIndex = 0;
    private boolean animating = false;

    @FXML
    private void initialize() {
        images = new Image[] {
            new Image(getClass().getResourceAsStream("/images/loginPage1.png")),
            new Image(getClass().getResourceAsStream("/images/loginPage2.png")),
            new Image(getClass().getResourceAsStream("/images/loginPage3.png"))
        };

        images[0].progressProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() >= 1.0) {
                bindAspectRatio();
            }
        });

        if (images[0].getProgress() >= 1.0){
            bindAspectRatio();
        }

        slideshowPane.getChildren().add(createRegion(images[0]));

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(slideshowPane.widthProperty());
        clip.heightProperty().bind(slideshowPane.heightProperty());
        slideshowPane.setClip(clip);

        Timeline autoSlide = new Timeline(new KeyFrame(Duration.seconds(5), e -> slideToNext()));
        autoSlide.setCycleCount(Timeline.INDEFINITE);
        autoSlide.play();
    }

    private void bindAspectRatio() {
        double ratio = images[0].getWidth() / images[0].getHeight();
        slideshowPane.prefWidthProperty().bind(slideshowPane.heightProperty().multiply(ratio));
    }

    private void slideToNext() {
        if (animating) return;
        animating = true;

        int nextIndex = (currentIndex + 1) % images.length;
        Region current = (Region) slideshowPane.getChildren().get(slideshowPane.getChildren().size() - 1);
        Region next = createRegion(images[nextIndex]);

        next.setTranslateX(slideshowPane.getWidth());
        slideshowPane.getChildren().add(next);

        Timeline slide = new Timeline(
            new KeyFrame(Duration.millis(600),
                new KeyValue(current.translateXProperty(), -slideshowPane.getWidth()),
                new KeyValue(next.translateXProperty(), 0)
            )
        );

        slide.setOnFinished(e -> {
            slideshowPane.getChildren().remove(current);
            animating = false;
        });

        slide.play();
        currentIndex = nextIndex;
    }

    private Region createRegion(Image image) {
        Region region = new Region();
        region.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        BackgroundImage bg = new BackgroundImage(
            image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(100, 100, true, true, false, true) // cover
        );

        region.setBackground(new Background(bg));
        return region;
    }

    @FXML
    private void handleLogin() throws IOException, URISyntaxException {
        String loginToken = UUID.randomUUID().toString();

        // If desktop is supported, open this link in the browser.
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("http://31.57.156.36.nip.io:8080/oauth2/authorization/google?loginToken=" + loginToken));
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
                        .uri(new URI("http://31.57.156.36:8080/loginSuccess/client?loginToken=" + loginToken))
                        .header("Accept", "application/json")
                        .build();

                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                    if (response.statusCode() == 200) {
                        String responseBody = response.body();
                        if (!responseBody.isEmpty()) {
                            LoginSuccessResponse loggedUser = mapper.readValue(responseBody, LoginSuccessResponse.class);
                            if (loggedUser.isBanned()) {
                                Platform.runLater(() -> messageLabel.setText("This account is banned."));
                                isResponse = true;
                            } else {
                                App.setGoogleSub(loggedUser.getGoogleSub());
                                App.setUserName(loggedUser.getName());
                                App.setUserEmail(loggedUser.getEmail());
                                App.setProfilePictureURL(loggedUser.getProfilePictureURL());
                                App.setBusinessOwner(loggedUser.isBusinessOwner());
                                App.setAdmin(loggedUser.isAdmin());
                                App.setUserPhoneNumber(loggedUser.getPhoneNumber());
                                App.setLoginToken(loginToken);
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
