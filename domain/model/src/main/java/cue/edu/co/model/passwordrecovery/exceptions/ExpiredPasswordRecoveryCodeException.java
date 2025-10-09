package cue.edu.co.model.passwordrecovery.exceptions;

import cue.edu.co.model.common.exceptions.BusinessException;

public class ExpiredPasswordRecoveryCodeException extends BusinessException {
    public ExpiredPasswordRecoveryCodeException() {
        super("El código ya ha sido expirdado o usado", "EXPIRED_PASSWORD_RECOVERY_CODE");
    }
}
