package cue.edu.co.model.passwordrecovery.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecoverPasswordEvent {
    private String email;
    private String name;
    private String recoveryCode;
    private String expirationTime;
}