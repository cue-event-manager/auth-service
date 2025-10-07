package cue.edu.co.model.auth.commands;

import java.util.Optional;

public record UpdateProfileCommand(
        String firstName,
        String lastName,
        String email,
        Optional<String> phoneNumber,
        Optional<String> currentPassword,
        Optional<String> newPassword
) {
}
