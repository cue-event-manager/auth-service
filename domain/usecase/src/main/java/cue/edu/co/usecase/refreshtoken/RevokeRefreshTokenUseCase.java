package cue.edu.co.usecase.refreshtoken;

import cue.edu.co.model.refreshtoken.gateways.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RevokeRefreshTokenUseCase {

    private final RefreshTokenRepository refreshTokenRepository;

    public void revoke(Long tokenId) {
        refreshTokenRepository.revokeById(tokenId);
    }

    public void revokeAllForUser(Long userId) {
        refreshTokenRepository.revokeAllByUserId(userId);
    }
}
