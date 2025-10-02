package cue.edu.co.model.role.gateways;

import cue.edu.co.model.role.Role;

import java.util.Optional;

public interface RoleRepository {
    Role save(Role role);
    Optional<Role> findByName(String name);
    Optional<Role> findById(Long id);
}
