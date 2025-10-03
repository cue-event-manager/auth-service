package cue.edu.co.model.refreshtoken.commands;

import cue.edu.co.model.refreshtoken.RefreshToken;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

import static cue.edu.co.model.refreshtoken.constants.RefreshTokenConstant.REFRESH_TOKEN_VALIDITY;

@Builder
public record CreateRefreshTokenCommand(
        Long userId,
        String deviceInfo,
        String ipAddress
) {

    public RefreshToken toDomain(){
        return RefreshToken.builder()
                .userId(userId)
                .token(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusDays(REFRESH_TOKEN_VALIDITY))
                .revoked(false)
                .deviceInfo(deviceInfo)
                .ipAddress(ipAddress)
                .build();
    }
}
