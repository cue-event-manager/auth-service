package cue.edu.co.model.passwordrecovery.gateways;

import cue.edu.co.model.passwordrecovery.PasswordRecovery;

import java.util.Optional;

public interface PasswordRecoveryRepository {
    void save(PasswordRecovery recovery);
    Optional<PasswordRecovery> findByEmail(String email);
    void invalidateAllByEmail(String email);
    Optional<PasswordRecovery> findByEmailAndCode(String email, String code);
}
