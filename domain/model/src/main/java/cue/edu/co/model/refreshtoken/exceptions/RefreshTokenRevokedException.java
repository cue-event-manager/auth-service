package cue.edu.co.model.refreshtoken.exceptions;

import cue.edu.co.model.common.exceptions.BusinessException;

public class RefreshTokenRevokedException extends BusinessException {
    public RefreshTokenRevokedException() {
        super("El token ya ha sido usado.", "REFRESH_TOKEN_REVOKED");
    }
}
