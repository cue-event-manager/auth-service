package cue.edu.co.model.passwordrecovery;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PasswordRecovery {
    private Long id;
    private Long userId;
    private String email;
    private String code;
    private LocalDateTime expiresAt;
    private boolean used;
}
