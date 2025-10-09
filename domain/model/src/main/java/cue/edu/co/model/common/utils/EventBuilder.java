package cue.edu.co.model.common.utils;

import cue.edu.co.model.common.constants.EventType;
import cue.edu.co.model.common.models.Event;

import java.time.LocalDateTime;
import java.util.UUID;

public final class EventBuilder {

    private EventBuilder() {}

    public static Event build(EventType type, Object payload) {
        return Event.builder()
                .id(UUID.randomUUID().toString())
                .type(type.getType())
                .payload(payload)
                .occurredAt(LocalDateTime.now())
                .build();
    }
}
