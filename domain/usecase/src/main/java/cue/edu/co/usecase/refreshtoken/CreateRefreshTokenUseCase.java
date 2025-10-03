package cue.edu.co.usecase.refreshtoken;

import cue.edu.co.model.refreshtoken.RefreshToken;
import cue.edu.co.model.refreshtoken.commands.CreateRefreshTokenCommand;
import cue.edu.co.model.refreshtoken.gateways.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateRefreshTokenUseCase {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken execute(CreateRefreshTokenCommand createRefreshTokenCommand){
        RefreshToken refreshToken = createRefreshTokenCommand.toDomain();

        return refreshTokenRepository.save(refreshToken);
    }

}
