package cue.edu.co.api.user.dtos;

import cue.edu.co.model.role.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role,
        String phoneNumber,
        String identification,
        LocalDate birthDate,
        String profilePicture,
        LocalDateTime createdAt
) {
}
