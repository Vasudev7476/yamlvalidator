package com.example.yamlvalidator.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
public class SnsNotificationService {
    private final SnsClient snsClient;
    private final String topicArn = "arn:aws:sns:ap-south-1:585008061439:yml_error_notification";

    // ✅ Inject SnsClient via constructor
    public SnsNotificationService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void sendFailureNotification(String message) {
        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .message("YAML Validation Failed: " + message)
                .build();
        snsClient.publish(request);
    }
}