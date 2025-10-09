package cue.edu.co.model.passwordrecovery.commands;

public record ResetPasswordCommand(
        String code,
        String email,
        String newPassword
) {
}
