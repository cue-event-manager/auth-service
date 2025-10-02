package cue.edu.co.api.user;

import cue.edu.co.api.user.constants.UserEndpoint;
import cue.edu.co.api.user.dtos.CreateUserRequestDto;
import cue.edu.co.api.user.dtos.UserResponseDto;
import cue.edu.co.api.user.mappers.UserDtoMapper;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.comnands.CreateUserCommand;
import cue.edu.co.usecase.user.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CreateUserUseCase createUserUseCase;

    private final UserDtoMapper userDtoMapper;

    @PostMapping(UserEndpoint.CREATE_USER_ENDPOINT)
    public ResponseEntity<UserResponseDto> createUser(
            @Valid @RequestBody CreateUserRequestDto requestDto) {

        CreateUserCommand command = userDtoMapper.toCommand(requestDto);

        User createdUser = createUserUseCase.execute(command);

        UserResponseDto response = userDtoMapper.toDto(createdUser);

        return ResponseEntity.ok(response);
    }
}
