package cue.edu.co.usecase.auth;

import cue.edu.co.model.auth.gateways.TokenProvider;
import cue.edu.co.model.refreshtoken.RefreshToken;
import cue.edu.co.model.refreshtoken.exceptions.RefreshTokenExpiredException;
import cue.edu.co.model.refreshtoken.exceptions.RefreshTokenInvalidException;
import cue.edu.co.model.refreshtoken.exceptions.RefreshTokenRevokedException;
import cue.edu.co.model.refreshtoken.gateways.RefreshTokenRepository;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.exceptions.UserNotFoundException;
import cue.edu.co.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class RefreshTokenUseCase {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public String execute(String token){
        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(RefreshTokenInvalidException::new);

        if(refreshToken.isRevoked()) throw new RefreshTokenRevokedException();
        if(refreshToken.getExpiresAt().isBefore(LocalDateTime.now())) throw new RefreshTokenExpiredException();

        User user = userRepository
                .findById(refreshToken.getUserId())
                .orElseThrow(UserNotFoundException::new);

        return tokenProvider.generate(user);
    }
}
