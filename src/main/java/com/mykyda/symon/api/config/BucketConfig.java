package com.mykyda.symon.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class BucketConfig {

    @Value("${aws.access}")
    private String accessKey;

    @Value("${aws.secret}")
    private String secretAccessKey;

    @Bean
    public S3Client getClient() {
        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider
                        .create(AwsBasicCredentials
                                .create(accessKey, secretAccessKey)))
                .region(Region.EU_NORTH_1).build();
    }
}