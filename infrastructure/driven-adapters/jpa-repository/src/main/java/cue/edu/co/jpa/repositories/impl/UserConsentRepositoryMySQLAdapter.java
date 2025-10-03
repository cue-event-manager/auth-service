package cue.edu.co.jpa.repositories.impl;

import cue.edu.co.jpa.entities.UserConsentEntity;
import cue.edu.co.jpa.mappers.UserConsentEntityMapper;
import cue.edu.co.jpa.repositories.UserConsentJpaRepository;
import cue.edu.co.model.userconsent.UserConsent;
import cue.edu.co.model.userconsent.gateways.UserConsentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserConsentRepositoryMySQLAdapter implements UserConsentRepository {

    private final UserConsentJpaRepository refreshTokenJpaRepository;
    private final UserConsentEntityMapper userConsentEntityMapper;


    @Override
    public UserConsent save(UserConsent userConsent) {
        UserConsentEntity saved = refreshTokenJpaRepository.save(userConsentEntityMapper.toEntity(userConsent));
        return userConsentEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<UserConsent> findLatestByUserId(Long userId) {
        return refreshTokenJpaRepository
                .findTopByUserIdOrderByAcceptedAtDesc(userId)
                .map(userConsentEntityMapper::toDomain);
    }
}
