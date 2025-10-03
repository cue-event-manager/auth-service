package cue.edu.co.jpa.repositories.impl;

import cue.edu.co.jpa.entities.UserEntity;
import cue.edu.co.jpa.mappers.UserEntityMapper;
import cue.edu.co.jpa.repositories.UserJpaRepository;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class UserRepositoryMySQLAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User save(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);

        UserEntity userSaved = userJpaRepository.save(userEntity);

        return userEntityMapper.toDomain(userSaved);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository
                .findByEmail(email)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public boolean existsByIdentification(String identification) {
        return userJpaRepository.existsByIdentification(identification);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return userJpaRepository.existsByPhoneNumber(phoneNumber);
    }

}
