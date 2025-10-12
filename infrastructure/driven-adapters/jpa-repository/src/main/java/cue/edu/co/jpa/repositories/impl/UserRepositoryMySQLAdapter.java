package cue.edu.co.jpa.repositories.impl;

import cue.edu.co.jpa.entities.UserEntity;
import cue.edu.co.jpa.mappers.PaginationMapper;
import cue.edu.co.jpa.mappers.UserEntityMapper;
import cue.edu.co.jpa.repositories.UserJpaRepository;
import cue.edu.co.jpa.specifications.UserSpecificationBuilder;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.gateways.UserRepository;
import cue.edu.co.model.user.queries.UserPaginationQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class UserRepositoryMySQLAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;
    private final PaginationMapper paginationMapper;

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
    public Optional<User> findByIdentification(String identification) {
        return userJpaRepository
                .findByIdentification(identification)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userJpaRepository
                .findByPhoneNumber(phoneNumber)
                .map(userEntityMapper::toDomain);    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(userEntityMapper::toDomain);
    }

    @Override
    public PageResult<User> findAllByFilters(UserPaginationQuery query) {
        Pageable pageable = paginationMapper.toPageable(query.pagination());

        Specification<UserEntity> specification = UserSpecificationBuilder.build(query);

        Page<UserEntity> page = userJpaRepository.findAll(specification, pageable);

        return paginationMapper.toPageResult(page, userEntityMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userJpaRepository.existsById(id);
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
