package cue.edu.co.model.user.events;

import cue.edu.co.model.role.Role;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserCreatedEvent(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role,
        LocalDate birthDate
) {
}
