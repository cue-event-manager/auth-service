package cue.edu.co.api.user.mappers;

import cue.edu.co.api.user.dtos.CreateUserRequestDto;
import cue.edu.co.api.user.dtos.UserResponseDto;
import cue.edu.co.api.utils.OptionalMapper;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.comnands.CreateUserCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { OptionalMapper.class })
public interface UserDtoMapper {

    CreateUserCommand toCommand(CreateUserRequestDto dto);

    UserResponseDto toDto(User user);
}