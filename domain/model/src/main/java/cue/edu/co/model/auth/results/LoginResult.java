package cue.edu.co.model.auth.results;


public record LoginResult(
        String email,
        String firstName,
        String lastName,
        String accessToken,
        String refreshToken
) {}