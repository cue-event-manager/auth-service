package cue.edu.co.model.common.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Event {
    private String id;
    private String type;
    private Object payload;
    private LocalDateTime occurredAt;
}
