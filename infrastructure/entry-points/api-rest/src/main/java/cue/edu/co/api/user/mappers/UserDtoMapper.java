package cue.edu.co.api.user.mappers;

import cue.edu.co.api.common.dtos.PaginationRequestDto;
import cue.edu.co.api.common.dtos.PaginationResponseDto;
import cue.edu.co.api.common.mappers.PaginationDtoMapper;
import cue.edu.co.api.user.dtos.CreateUserRequestDto;
import cue.edu.co.api.user.dtos.UserPaginationRequestDto;
import cue.edu.co.api.user.dtos.UserResponseDto;
import cue.edu.co.api.common.mappers.OptionalMapper;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.comnands.CreateUserCommand;
import cue.edu.co.model.user.queries.UserPaginationQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { OptionalMapper.class, PaginationDtoMapper.class })
public interface UserDtoMapper {

    CreateUserCommand toCommand(CreateUserRequestDto dto);

    UserResponseDto toDto(User user);

    @Mapping(target = "pagination", source = "paginationRequestDto")
    UserPaginationQuery toQuery(UserPaginationRequestDto userPaginationRequestDto, PaginationRequestDto paginationRequestDto);

    PaginationResponseDto<UserResponseDto> toDto(PageResult<User> user);
}