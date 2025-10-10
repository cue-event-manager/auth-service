package cue.edu.co.api.auth.mappers;

import cue.edu.co.api.auth.dtos.LoginResponseDto;
import cue.edu.co.api.auth.dtos.RecoverPasswordRequestDto;
import cue.edu.co.api.auth.dtos.ResetPasswordRequestDto;
import cue.edu.co.api.auth.dtos.UpdateProfileRequestDto;
import cue.edu.co.api.common.mappers.OptionalMapper;
import cue.edu.co.model.auth.commands.UpdateProfileCommand;
import cue.edu.co.model.auth.results.LoginResult;
import cue.edu.co.model.passwordrecovery.commands.RecoverPasswordCommand;
import cue.edu.co.model.passwordrecovery.commands.ResetPasswordCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { OptionalMapper.class })
public interface AuthDtoMapper {
    LoginResponseDto toDto(LoginResult result);
    UpdateProfileCommand toDomain(UpdateProfileRequestDto updateProfileRequestDto);
    RecoverPasswordCommand toDomain(RecoverPasswordRequestDto recoverPasswordRequestDto);
    ResetPasswordCommand toDomain(ResetPasswordRequestDto resetPasswordRequestDto);
}