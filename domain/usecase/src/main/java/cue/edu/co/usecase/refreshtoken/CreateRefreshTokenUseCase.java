package cue.edu.co.usecase.refreshtoken;

import cue.edu.co.model.refreshtoken.RefreshToken;
import cue.edu.co.model.refreshtoken.commands.CreateRefreshTokenCommand;
import cue.edu.co.model.refreshtoken.gateways.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CreateRefreshTokenUseCase {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken execute(CreateRefreshTokenCommand command) {
        Optional<RefreshToken> existingTokenOpt =
                refreshTokenRepository.findByUserIdAndDeviceInfo(command.userId(), command.deviceInfo(), false);

        existingTokenOpt.ifPresent(existingToken -> {
            existingToken.setRevoked(true);
            refreshTokenRepository.save(existingToken);
        });

        RefreshToken newToken = command.toDomain();

        return refreshTokenRepository.save(newToken);
    }

}
