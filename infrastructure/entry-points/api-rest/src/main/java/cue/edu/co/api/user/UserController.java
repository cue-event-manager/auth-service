package cue.edu.co.api.user;

import cue.edu.co.api.common.dtos.PaginationRequestDto;
import cue.edu.co.api.common.dtos.PaginationResponseDto;
import cue.edu.co.api.user.constants.UserEndpoint;
import cue.edu.co.api.user.dtos.CreateUserRequestDto;
import cue.edu.co.api.user.dtos.UpdateUserRequestDto;
import cue.edu.co.api.user.dtos.UserPaginationRequestDto;
import cue.edu.co.api.user.dtos.UserResponseDto;
import cue.edu.co.api.user.mappers.UserDtoMapper;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.commands.CreateUserCommand;
import cue.edu.co.model.user.commands.UpdateUserCommand;
import cue.edu.co.model.user.queries.UserPaginationQuery;
import cue.edu.co.usecase.user.CreateUserUseCase;
import cue.edu.co.usecase.user.GetUserUseCase;
import cue.edu.co.usecase.user.UpdateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    private final UserDtoMapper userDtoMapper;

    @PostMapping(UserEndpoint.CREATE_USER_ENDPOINT)
    public ResponseEntity<UserResponseDto> createUser(
            @Valid @RequestBody CreateUserRequestDto requestDto) {

        CreateUserCommand command = userDtoMapper.toCommand(requestDto);

        User createdUser = createUserUseCase.execute(command);

        UserResponseDto response = userDtoMapper.toDto(createdUser);

        return ResponseEntity.ok(response);
    }

    @GetMapping(UserEndpoint.USER_BASE)
    public ResponseEntity<PaginationResponseDto<UserResponseDto>> getUsers(
            @Valid UserPaginationRequestDto requestDto,
            @Valid PaginationRequestDto paginationRequestDto
            ) {

        UserPaginationQuery query = userDtoMapper.toQuery(requestDto, paginationRequestDto);

        PageResult<User> pageResult = getUserUseCase.execute(query);

        PaginationResponseDto<UserResponseDto> response = userDtoMapper.toDto(pageResult);

        return ResponseEntity.ok(response);
    }

    @PutMapping(UserEndpoint.UPDATE_USER_ENDPOINT)
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateUserRequestDto requestDto
            ){
        UpdateUserRequestDto updateUserRequestDto = requestDto.withId(id);

        UpdateUserCommand updateUserCommand = userDtoMapper.toCommand(updateUserRequestDto);

        User userUpdated = updateUserUseCase.execute(updateUserCommand);

        return ResponseEntity.ok(userDtoMapper.toDto(userUpdated));
    }
}
