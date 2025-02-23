package com.example.yamlvalidator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsConfig {
    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;
    
    @Bean
    public SnsClient snsClient() {
          AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretKey);
        return SnsClient.builder()
                .region(Region.of(region)) 
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }
}