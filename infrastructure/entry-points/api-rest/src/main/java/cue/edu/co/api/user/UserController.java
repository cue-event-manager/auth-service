package cue.edu.co.api.user;

import cue.edu.co.api.common.dtos.PaginationRequestDto;
import cue.edu.co.api.common.dtos.PaginationResponseDto;
import cue.edu.co.api.user.constants.UserEndpoint;
import cue.edu.co.api.user.dtos.CreateUserRequestDto;
import cue.edu.co.api.user.dtos.UserPaginationRequestDto;
import cue.edu.co.api.user.dtos.UserResponseDto;
import cue.edu.co.api.user.mappers.UserDtoMapper;
import cue.edu.co.model.common.results.PageResult;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.comnands.CreateUserCommand;
import cue.edu.co.model.user.queries.UserPaginationQuery;
import cue.edu.co.usecase.user.CreateUserUseCase;
import cue.edu.co.usecase.user.GetUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;

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
}
