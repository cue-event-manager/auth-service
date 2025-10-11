package cue.edu.co.api.user.dtos;


import cue.edu.co.api.user.constants.UserValidation;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateUserRequestDto(
        Long id,

        String firstName,

        String lastName,

        @Email(message = UserValidation.EMAIL_INVALID)
        String email,

        Long roleId,

        @Pattern(regexp = "^[0-9]*$", message = UserValidation.PHONE_INVALID)
        String phoneNumber,

        String identification,

        LocalDate birthDate
) {
    public UpdateUserRequestDto withId(Long id){
        return new UpdateUserRequestDto(
                id,
                firstName,
                lastName,
                email,
                roleId,
                phoneNumber,
                identification,
                birthDate
        );
    }
}