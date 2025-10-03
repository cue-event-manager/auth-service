package cue.edu.co.model.refreshtoken.exceptions;

import cue.edu.co.model.common.exceptions.BusinessException;

public class RefreshTokenExpiredException extends BusinessException {
    public RefreshTokenExpiredException() {
        super("Token expirado.", "REFRESH_TOKEN_EXPIRED");
    }
}
