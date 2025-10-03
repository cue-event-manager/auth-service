package cue.edu.co.jpa.mappers;

import cue.edu.co.jpa.entities.RefreshTokenEntity;
import cue.edu.co.model.refreshtoken.RefreshToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenEntityMapper {
    RefreshToken toDomain(RefreshTokenEntity refreshToken);

    RefreshTokenEntity toEntity(RefreshToken refreshToken);
}
