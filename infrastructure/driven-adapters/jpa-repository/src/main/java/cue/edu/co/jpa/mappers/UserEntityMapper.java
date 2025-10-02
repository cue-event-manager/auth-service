package cue.edu.co.jpa.mappers;


import cue.edu.co.jpa.entities.UserEntity;
import cue.edu.co.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        uses = {RoleEntityMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserEntityMapper {
    User toDomain(UserEntity userEntity);
    UserEntity toEntity(User user);
}