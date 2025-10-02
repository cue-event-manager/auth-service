package cue.edu.co.model.role.exceptions;

import cue.edu.co.model.common.exceptions.NotFoundException;

public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException() {
        super("No se encontro el rol");
    }
}
