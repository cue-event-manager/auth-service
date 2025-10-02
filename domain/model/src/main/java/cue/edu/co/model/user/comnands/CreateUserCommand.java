package cue.edu.co.model.user.comnands;

import cue.edu.co.model.role.Role;
import cue.edu.co.model.user.User;

import java.time.LocalDate;
import java.util.Optional;

public record CreateUserCommand(
        String firstName,
        String lastName,
        String email,
        String password,
        Long roleId,
        Optional<String> phoneNumber,
        Optional<String> identification,
        Optional<LocalDate> birthDate,
        Optional<String> profilePicture
) {
    public User toDomain(Role role, String  password) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(role)
                .phoneNumber(phoneNumber.orElse(null))
                .identification(identification.orElse(null))
                .birthDate(birthDate.orElse(null))
                .profilePicture(profilePicture.orElse(null))
                .build();
    }
}
