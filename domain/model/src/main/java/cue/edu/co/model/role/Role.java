package cue.edu.co.model.role;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Role {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
