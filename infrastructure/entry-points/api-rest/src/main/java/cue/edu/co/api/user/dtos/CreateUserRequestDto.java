package cue.edu.co.api.user.dtos;


import cue.edu.co.api.user.constants.UserValidation;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateUserRequestDto(

        @NotBlank(message = UserValidation.FIRST_NAME_REQUIRED)
        String firstName,

        @NotBlank(message = UserValidation.LAST_NAME_REQUIRED)
        String lastName,

        @NotBlank(message = UserValidation.EMAIL_REQUIRED)
        @Email(message = UserValidation.EMAIL_INVALID)
        String email,

        @NotBlank(message = UserValidation.PASSWORD_REQUIRED)
        @Size(min = 8, message = UserValidation.PASSWORD_MIN_LENGTH)
        String password,

        @NotNull(message = UserValidation.ROLE_ID_REQUIRED)
        Long roleId,

        @Pattern(regexp = "^[0-9]*$", message = UserValidation.PHONE_INVALID)
        String phoneNumber,

        String identification,
        LocalDate birthDate,
        String profilePicture
) {}