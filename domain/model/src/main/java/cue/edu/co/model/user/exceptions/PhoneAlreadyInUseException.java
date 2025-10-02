package cue.edu.co.model.user.exceptions;

import cue.edu.co.model.common.exceptions.BusinessException;

public class PhoneAlreadyInUseException extends BusinessException {

    public PhoneAlreadyInUseException() {
        super("El celular ya esta en uso.", "PHONE_ALREADY_IN_USE");
    }
}
