package cue.edu.co.model.refreshtoken.exceptions;

import cue.edu.co.model.common.exceptions.BusinessException;

public class RefreshTokenInvalidException extends BusinessException {
    public RefreshTokenInvalidException() {
        super("Token no valido.", "REFRESH_TOKEN_INVALID");
    }
}
