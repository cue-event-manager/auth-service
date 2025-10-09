package cue.edu.co.sns.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.sns")
public record SNSSenderProperties(
        String region,
        String topicArn
) {}