package cue.edu.co.jpa.repositories.impl;

import cue.edu.co.jpa.entities.RoleEntity;
import cue.edu.co.jpa.mappers.RoleEntityMapper;
import cue.edu.co.jpa.repositories.RoleJpaRepository;
import cue.edu.co.model.role.Role;
import cue.edu.co.model.role.gateways.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryMySQLAdapter implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Role save(Role role) {
        RoleEntity roleSaved = roleJpaRepository.save(roleEntityMapper.toEntity(role));
        return roleEntityMapper.toDomain(roleSaved);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleJpaRepository
                .findByName(name)
                .map(roleEntityMapper::toDomain);
    }
}
