package cue.edu.co.jpa.repositories.impl;

import cue.edu.co.jpa.mappers.PasswordRecoveryEntityMapper;
import cue.edu.co.jpa.repositories.PasswordRecoveryJpaRepository;
import cue.edu.co.model.passwordrecovery.PasswordRecovery;
import cue.edu.co.model.passwordrecovery.gateways.PasswordRecoveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PasswordRecoveryRepositoryMySQLAdapter implements PasswordRecoveryRepository {
    private final PasswordRecoveryJpaRepository passwordRecoveryJpaRepository;
    private final PasswordRecoveryEntityMapper passwordRecoveryEntityMapper;

    @Override
    public void save(PasswordRecovery recovery) {
        passwordRecoveryJpaRepository.save(passwordRecoveryEntityMapper.toEntity(recovery));
    }

    @Override
    public Optional<PasswordRecovery> findByEmail(String email) {
        return passwordRecoveryJpaRepository
                .findByEmail(email)
                .map(passwordRecoveryEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void invalidateAllByEmail(String email) {
        passwordRecoveryJpaRepository.invalidateAllByEmail(email);
    }

    @Override
    public Optional<PasswordRecovery> findByEmailAndCode(String email, String code) {
        return passwordRecoveryJpaRepository
                .findByEmailAndCode(email,code)
                .map(passwordRecoveryEntityMapper::toDomain);
    }
}
