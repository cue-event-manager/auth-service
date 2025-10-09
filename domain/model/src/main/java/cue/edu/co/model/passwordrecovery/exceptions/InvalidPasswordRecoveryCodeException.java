package cue.edu.co.model.passwordrecovery.exceptions;

import cue.edu.co.model.common.exceptions.BusinessException;

public class InvalidPasswordRecoveryCodeException extends BusinessException {
    public InvalidPasswordRecoveryCodeException() {
        super("Código inválido", "INVALID_PASSWORD_RECOVERY_CODE");
    }
}
