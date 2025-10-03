package cue.edu.co.model.userconsent.gateways;

import cue.edu.co.model.userconsent.UserConsent;

import java.util.Optional;

public interface UserConsentRepository {
    UserConsent save(UserConsent userConsent);
    Optional<UserConsent> findLatestByUserId(Long userId);
}
