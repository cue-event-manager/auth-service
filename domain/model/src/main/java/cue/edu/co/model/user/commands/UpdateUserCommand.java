package cue.edu.co.model.user.commands;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Optional;

@Builder
public record UpdateUserCommand(
        Long id,
        Optional<String> firstName,
        Optional<String> lastName,
        Optional<String> email,
        Optional<String> phoneNumber,
        Optional<String> identification,
        Optional<LocalDate> birthDate,
        Optional<Long> roleId
) {

    public UpdateUserCommand {
        firstName = firstName != null ? firstName : Optional.empty();
        lastName = lastName != null ? lastName : Optional.empty();
        email = email != null ? email : Optional.empty();
        phoneNumber = phoneNumber != null ? phoneNumber : Optional.empty();
        identification = identification != null ? identification : Optional.empty();
        birthDate = birthDate != null ? birthDate : Optional.empty();
        roleId = roleId != null ? roleId : Optional.empty();
    }
}