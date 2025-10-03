package cue.edu.co.model.auth.exceptions;

import cue.edu.co.model.common.exceptions.BusinessException;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException() {
        super("Credenciales invalidas", "INVALID_CREDENTIALS");
    }
}
