package cue.edu.co.sns;

import com.fasterxml.jackson.databind.ObjectMapper;
import cue.edu.co.model.common.gateways.EventPublisher;
import cue.edu.co.model.common.models.Event;
import cue.edu.co.sns.config.SNSSenderProperties;
import cue.edu.co.sns.constant.SNSPublisherLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class SNSPublisherAdapter implements EventPublisher {
    private final SnsAsyncClient snsClient;
    private final SNSSenderProperties properties;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(Event event) {
        try {
            log.info(SNSPublisherLog.PUBLISHING.getMessage(), event.getType(), properties.topicArn());

            String message = objectMapper.writeValueAsString(event);

            PublishRequest request = PublishRequest.builder()
                    .topicArn(properties.topicArn())
                    .message(message)
                    .build();

            snsClient.publish(request)
                    .thenAccept(response ->
                            log.info(SNSPublisherLog.PUBLISHED_SUCCESS.getMessage(),
                                    event.getType(),
                                    response.messageId())
                    )
                    .exceptionally(ex -> {
                        log.error(SNSPublisherLog.PUBLISH_ERROR.getMessage(), ex.getMessage(), ex);
                        return null;
                    });

        } catch (Exception e) {
            log.error(SNSPublisherLog.SERIALIZATION_ERROR.getMessage(), e);
        }
    }
}
