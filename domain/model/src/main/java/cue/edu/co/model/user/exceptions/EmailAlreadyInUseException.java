package cue.edu.co.model.user.exceptions;

import cue.edu.co.model.common.exceptions.BusinessException;

public class EmailAlreadyInUseException extends BusinessException {

    public EmailAlreadyInUseException() {
        super("El correo electronico ya esta en uso.", "EMAIL_ALREADY_IN_USE");
    }
}
