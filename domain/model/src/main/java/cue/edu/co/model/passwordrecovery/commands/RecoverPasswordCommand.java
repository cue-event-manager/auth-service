package cue.edu.co.model.passwordrecovery.commands;

import lombok.Builder;

@Builder
public record RecoverPasswordCommand(
        String email
) {
}
