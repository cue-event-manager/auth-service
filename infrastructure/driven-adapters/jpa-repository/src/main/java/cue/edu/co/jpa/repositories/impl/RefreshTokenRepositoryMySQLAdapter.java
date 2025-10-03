package cue.edu.co.jpa.repositories.impl;

import cue.edu.co.jpa.entities.RefreshTokenEntity;
import cue.edu.co.jpa.mappers.RefreshTokenEntityMapper;
import cue.edu.co.jpa.repositories.RefreshTokenJpaRepository;
import cue.edu.co.model.refreshtoken.RefreshToken;
import cue.edu.co.model.refreshtoken.gateways.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryMySQLAdapter implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;
    private final RefreshTokenEntityMapper refreshTokenEntityMapper;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenEntityMapper.toEntity(refreshToken);
        return refreshTokenEntityMapper.toDomain(refreshTokenJpaRepository.save(refreshTokenEntity));
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenJpaRepository
                .findByToken(token)
                .map(refreshTokenEntityMapper::toDomain);
    }

    @Override
    public void revokeById(Long id) {
        refreshTokenJpaRepository.revokeById(id);
    }

    @Transactional
    @Override
    public void revokeAllByUserId(Long userId) {
        refreshTokenJpaRepository.revokeAllByUserId(userId);

    }
}
