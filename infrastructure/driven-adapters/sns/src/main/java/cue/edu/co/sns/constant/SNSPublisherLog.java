package cue.edu.co.sns.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SNSPublisherLog {

    PUBLISHING("Publishing event [{}] to SNS topic [{}]"),
    PUBLISHED_SUCCESS("SNS event [{}] published successfully | MessageId: {}"),
    SERIALIZATION_ERROR("Error serializing event before publishing to SNS"),
    PUBLISH_ERROR("Failed to publish SNS event: {}");

    private final String message;
}
