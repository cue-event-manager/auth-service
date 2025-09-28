package cue.edu.co.model.role;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
public class Role {
    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
}
