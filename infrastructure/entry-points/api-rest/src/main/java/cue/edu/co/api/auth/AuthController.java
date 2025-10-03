package cue.edu.co.api.auth;

import cue.edu.co.api.auth.constants.AuthEndpoint;
import cue.edu.co.api.auth.dtos.LoginRequestDto;
import cue.edu.co.api.auth.dtos.LoginResponseDto;
import cue.edu.co.api.auth.mappers.AuthDtoMapper;
import cue.edu.co.model.auth.commands.LoginCommand;
import cue.edu.co.model.auth.results.LoginResult;
import cue.edu.co.usecase.auth.LoginUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final LoginUseCase loginUseCase;

    private final AuthDtoMapper authDtoMapper;

    @PostMapping(AuthEndpoint.LOGIN_ENDPOINT)
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto requestDto,
            HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);

        LoginCommand command = new LoginCommand(
                requestDto.email(),
                requestDto.password(),
                ipAddress,
                userAgent,
                Optional.ofNullable(requestDto.acceptTerms())
                );

        LoginResult result = loginUseCase.execute(command);

        LoginResponseDto response = authDtoMapper.toDto(result);

        return ResponseEntity.ok(response);
    }
}
