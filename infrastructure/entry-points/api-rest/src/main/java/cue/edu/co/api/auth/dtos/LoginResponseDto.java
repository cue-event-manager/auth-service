package cue.edu.co.api.auth.dtos;

public record LoginResponseDto(
        String email,
        String firstName,
        String lastName,
        String accessToken,
        String refreshToken
) {
}
