package cue.edu.co.api.auth.dtos;

import cue.edu.co.api.user.constants.UserValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static cue.edu.co.api.user.constants.UserValidation.*;

public record UpdateProfileRequestDto(

        @NotBlank(message = FIRST_NAME_REQUIRED)
        String firstName,
        @NotBlank(message = LAST_NAME_REQUIRED)
        String lastName,
        @NotBlank(message = EMAIL_REQUIRED)
        @Email(message = EMAIL_INVALID)
        String email,
        String currentPassword,
        @Pattern(regexp = "^[0-9]*$", message = UserValidation.PHONE_INVALID)
        String phoneNumber,
        String newPassword
) {
}
