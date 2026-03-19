package com.fullhouse.server.services;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.Credentials;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;


@Service
public class SurveyResponseHandlerService {

    private SurveyServiceImpl surveyService;

    private String projectId = "eval-it-490310";
    private String subscriptionId = "responses-sub";
    private ProjectSubscriptionName subscriptionName;
    private Subscriber subscriber;
    private MessageReceiver messageReceiver;
    private Credentials googleCredentials;

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
        if( subscriber != null ) {
            subscriber.stopAsync().awaitTerminated();
            System.out.println("Stopped subscription");
        }
    }

    /**
     * Helper to handle the responses given
     * to a Survey's Google Form.
     * @param message
     * @param consumer
     */
    private void handleMessage(PubsubMessage message, AckReplyConsumer consumer) {
        String formId = message.getAttributes().get("formId");
        float newOverallScore;
        try {
            newOverallScore = surveyService.computeOverallScore(formId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // TODO: update the Survey that has the form
        //  with the formId in this function. Change its
        //  overallScore with newOverallScore.

        consumer.ack();
    }
}
