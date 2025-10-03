package cue.edu.co.api.auth.mappers;

import cue.edu.co.api.auth.dtos.LoginResponseDto;
import cue.edu.co.api.utils.OptionalMapper;
import cue.edu.co.model.auth.results.LoginResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { OptionalMapper.class })
public interface AuthDtoMapper {
    LoginResponseDto toDto(LoginResult result);
}