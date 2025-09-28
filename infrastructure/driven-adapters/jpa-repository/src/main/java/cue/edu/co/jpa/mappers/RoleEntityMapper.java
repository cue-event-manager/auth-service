package cue.edu.co.jpa.mappers;

import cue.edu.co.jpa.entities.RoleEntity;
import cue.edu.co.model.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper {
    Role toDomain(RoleEntity role);
    RoleEntity toEntity(Role role);
}
