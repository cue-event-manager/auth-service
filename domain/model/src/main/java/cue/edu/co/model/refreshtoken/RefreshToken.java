package cue.edu.co.model.refreshtoken;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RefreshToken {
    private Long id;
    private Long userId;
    private String token;
    private boolean revoked;
    private String deviceInfo;
    private String ipAddress;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}