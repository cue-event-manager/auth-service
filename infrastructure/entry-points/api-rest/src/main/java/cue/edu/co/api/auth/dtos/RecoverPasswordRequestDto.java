package cue.edu.co.api.auth.dtos;

import cue.edu.co.api.auth.constants.AuthValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RecoverPasswordRequestDto(
        @NotBlank(message = AuthValidation.EMAIL_REQUIRED)
        @Email(message = AuthValidation.EMAIL_INVALID)
        String email
) {
}
