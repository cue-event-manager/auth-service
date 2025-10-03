package cue.edu.co.model.refreshtoken.gateways;

import cue.edu.co.model.refreshtoken.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByToken(String token);

    void revokeById(Long id);

    void revokeAllByUserId(Long userId);
}