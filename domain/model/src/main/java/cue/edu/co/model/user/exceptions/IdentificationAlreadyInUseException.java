package cue.edu.co.model.user.exceptions;

import cue.edu.co.model.common.exceptions.BusinessException;

public class IdentificationAlreadyInUseException extends BusinessException {

    public IdentificationAlreadyInUseException() {
        super("La identificacion ya esta en uso.", "IDENTIFICATION_ALREADY_IN_USE");
    }
}
