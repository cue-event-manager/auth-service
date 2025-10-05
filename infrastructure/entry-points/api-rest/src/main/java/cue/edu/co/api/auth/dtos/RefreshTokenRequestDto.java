package cue.edu.co.api.auth.dtos;

import cue.edu.co.api.auth.constants.AuthValidation;
import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequestDto(
        @NotBlank(message = AuthValidation.REFRESH_TOKEN_REQUIRED)
        String refreshToken
) {
}
