package cue.edu.co.model.auth.exceptions;

import cue.edu.co.model.common.exceptions.BusinessException;

public class InvalidCurrentPasswordException extends BusinessException {
    public InvalidCurrentPasswordException() {
        super("Contraseña actual incorrecta", "INVALID_CURRENT_PASSWORD");
    }
}
