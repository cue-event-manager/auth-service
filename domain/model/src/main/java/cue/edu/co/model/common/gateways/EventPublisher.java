package cue.edu.co.model.common.gateways;

import cue.edu.co.model.common.models.Event;

public interface EventPublisher {
    void publish(Event event);
}
