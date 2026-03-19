package com.fullhouse.server.services;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.Credentials;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SurveyResponseHandlerService {

    private final SurveyServiceImpl surveyService;

    private final String projectId = "eval-it-490310";
    private final String subscriptionId = "responses-sub";
    private ProjectSubscriptionName subscriptionName;
    private Subscriber subscriber;
    private MessageReceiver messageReceiver;
    private final Credentials googleCredentials;

    public SurveyResponseHandlerService(SurveyServiceImpl surveyService, Credentials googleCredentials) {
        this.surveyService = surveyService;
        this.googleCredentials = googleCredentials;
    }

    @PostConstruct
    public void startSubscription() {
        subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
        subscriber = Subscriber.newBuilder(subscriptionName, this::handleMessage)
                .setCredentialsProvider(FixedCredentialsProvider.create(googleCredentials))
                .build();
        subscriber.startAsync().awaitRunning();
        System.out.println("Started subscription");
    }

    @PreDestroy
    public void stopSubscription() {
        if (subscriber != null) {
            subscriber.stopAsync().awaitTerminated();
            System.out.println("Stopped subscription");
        }
    }

    /**
     * Helper to handle the responses given
     * to a Survey's Google Form.
     *
     * @param message
     * @param consumer
     */
    private void handleMessage(PubsubMessage message, AckReplyConsumer consumer) {
        String formId = message.getAttributes().get("formId");
        float newOverallScore;
        List<Float> newScoresOfQuestions;
        try {
            newOverallScore = surveyService.computeOverallScore(formId);
            newScoresOfQuestions = surveyService.computeScoresOfQuestions(formId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // TODO: update the Survey that has the form
        //  with the formId in this function. Change its
        //  overallScore with newOverallScore and
        //  scoresOfQuestions with newScoresOfQuestions

        consumer.ack();
    }
}
