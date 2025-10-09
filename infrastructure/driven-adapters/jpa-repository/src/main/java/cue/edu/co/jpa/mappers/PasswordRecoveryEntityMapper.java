package cue.edu.co.jpa.mappers;

import cue.edu.co.jpa.entities.PasswordRecoveryEntity;
import cue.edu.co.model.passwordrecovery.PasswordRecovery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PasswordRecoveryEntityMapper {
    PasswordRecovery toDomain(PasswordRecoveryEntity passwordRecoveryEntity);
    PasswordRecoveryEntity toEntity(PasswordRecovery passwordRecovery);
}
