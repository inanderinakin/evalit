package com.fullhouse.server.services;

import com.fullhouse.server.domain.Survey;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.auth.Credentials;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.pubsub.v1.SubscriptionAdminSettings;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import org.threeten.bp.Duration;

/**
 * This service subscribes to the Pub/Sub system
 * and updates the scores of {@link Survey}s when
 * there is a response. It does not have an interface
 * since it is not connected with a controller.
 */
@Service
public class SurveyResponseHandlerService {

    private final SurveyService surveyService;
    private final BusinessService businessService;

    private final String projectId = "eval-it-490310";
    private final String subscriptionId = "responses-sub";
    private ProjectSubscriptionName subscriptionName;
    private Subscriber subscriber;
    private final Credentials googleCredentials;

    /**
     * Instantiates a new Survey response handler service.
     *
     * @param surveyService     the survey service
     * @param googleCredentials the google credentials
     * @param businessService   the business service
     */
    public SurveyResponseHandlerService(SurveyServiceImpl surveyService, Credentials googleCredentials, BusinessService businessService) {
        this.surveyService = surveyService;
        this.googleCredentials = googleCredentials;
        this.businessService = businessService;
    }

    /**
     * Starts the subscription. @PostConstruct
     * ensures that when the beans are injected at
     * the start of the build, this method runs.
     */
    @PostConstruct
    public void startSubscription() {
        InstantiatingGrpcChannelProvider channelProvider =
                SubscriptionAdminSettings.defaultGrpcTransportProviderBuilder()
                        .setKeepAliveTime(Duration.ofMinutes(5))
                        .setKeepAliveTimeout(Duration.ofSeconds(20))
                        .setKeepAliveWithoutCalls(true)
                        .build();
        subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
        subscriber = Subscriber.newBuilder(subscriptionName, this::handleMessage)
                .setChannelProvider(channelProvider)
                .setCredentialsProvider(FixedCredentialsProvider.create(googleCredentials))
                .build();
        subscriber.startAsync().awaitRunning();
        System.out.println("Started subscription");
    }

    /**
     * Ends the subscription. @PreDestroy ensures
     * that before termination of the program,
     * this method runs.
     */
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
     */
    private void handleMessage(PubsubMessage message, AckReplyConsumer consumer) {
        String formId = message.getAttributes().get("formId");
        System.out.println("\n\nForm was filled!\n");
        try {
            surveyService.updateSurveysBasedOnTheResponse(formId);
            businessService.updateAverageScoreBasedOnTheResponse(formId);
            consumer.ack();
        } catch (Exception e) {
            e.printStackTrace();
            consumer.nack();
        }

    }
}
