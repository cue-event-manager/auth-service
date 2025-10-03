package cue.edu.co.model.auth.exceptions;

import cue.edu.co.model.common.exceptions.BusinessException;

public class UserConsentRequiredException extends BusinessException {
    public UserConsentRequiredException(String termsVersion) {
        super("Consentimiento requerido para los terminos y condiciones: " + termsVersion, "USER_CONSENT_REQUIRED");
    }
}
