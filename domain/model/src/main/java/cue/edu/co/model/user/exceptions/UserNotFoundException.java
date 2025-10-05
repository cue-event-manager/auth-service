package cue.edu.co.model.user.exceptions;

import cue.edu.co.model.common.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("No se encontro el usuario");
    }
}
