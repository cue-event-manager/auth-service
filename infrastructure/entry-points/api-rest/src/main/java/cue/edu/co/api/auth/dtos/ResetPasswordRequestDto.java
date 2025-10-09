package cue.edu.co.api.auth.dtos;

import cue.edu.co.api.auth.constants.AuthValidation;
import cue.edu.co.api.auth.validation.PasswordSecure;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequestDto(
        @NotBlank(message = AuthValidation.EMAIL_REQUIRED)
        @Email(message = AuthValidation.EMAIL_INVALID)
        String email,

        @NotBlank(message = AuthValidation.CODE_REQUIRED)
        String code,

        @PasswordSecure
        @NotBlank(message = AuthValidation.PASSWORD_REQUIRED )
        String newPassword
) {
}
