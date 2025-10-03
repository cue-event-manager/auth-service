package cue.edu.co.usecase.refreshtoken;

import cue.edu.co.model.refreshtoken.RefreshToken;
import cue.edu.co.model.refreshtoken.exceptions.RefreshTokenExpiredException;
import cue.edu.co.model.refreshtoken.exceptions.RefreshTokenInvalidException;
import cue.edu.co.model.refreshtoken.exceptions.RefreshTokenRevokedException;
import cue.edu.co.model.refreshtoken.gateways.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ValidateRefreshTokenUseCase {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken execute(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(RefreshTokenInvalidException::new);

        if (refreshToken.isRevoked()) throw new RefreshTokenRevokedException();

        if(refreshToken.getExpiresAt().isBefore(LocalDateTime.now())) throw new RefreshTokenExpiredException();

        return refreshToken;
    }

}
