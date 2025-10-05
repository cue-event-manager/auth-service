package cue.edu.co.usecase.auth;

import cue.edu.co.model.refreshtoken.RefreshToken;
import cue.edu.co.model.refreshtoken.exceptions.RefreshTokenInvalidException;
import cue.edu.co.model.refreshtoken.gateways.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogoutUseCase {
    private final RefreshTokenRepository refreshTokenRepository;

    public void execute(String refreshToken){
        RefreshToken existingRefreshToken = refreshTokenRepository
                .findByToken(refreshToken)
                .orElseThrow(RefreshTokenInvalidException::new);

        refreshTokenRepository.revokeById(existingRefreshToken.getId());
    }
}

