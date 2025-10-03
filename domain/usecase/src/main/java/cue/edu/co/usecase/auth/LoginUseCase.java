package cue.edu.co.usecase.auth;

import cue.edu.co.model.auth.commands.LoginCommand;
import cue.edu.co.model.auth.exceptions.InvalidCredentialsException;
import cue.edu.co.model.auth.exceptions.UserConsentRequiredException;
import cue.edu.co.model.auth.gateways.TokenProvider;
import cue.edu.co.model.auth.results.LoginResult;
import cue.edu.co.model.refreshtoken.commands.CreateRefreshTokenCommand;
import cue.edu.co.model.security.gateways.PasswordEncoder;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.gateways.UserRepository;
import cue.edu.co.model.userconsent.UserConsent;
import cue.edu.co.model.userconsent.gateways.UserConsentRepository;
import cue.edu.co.usecase.refreshtoken.CreateRefreshTokenUseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConsentRepository userConsentRepository;
    private final TokenProvider tokenProvider;
    private final CreateRefreshTokenUseCase createRefreshToken;

    private static final String CURRENT_TERMS_VERSION = "v1.0-2025-09-30";

    public LoginResult execute(LoginCommand command) {
        User user = userRepository.findByEmail(command.email())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(command.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        boolean hasConsent = hasUserConsent(user.getId());

        if(!hasConsent && command.hasAcceptedTerms()){
            saveUserConsent(user.getId(), command);
        }

        if(!hasConsent && !command.hasAcceptedTerms()){
            throw new UserConsentRequiredException(CURRENT_TERMS_VERSION);
        }

        String accessToken = tokenProvider.generate(user);
        String refreshToken = saveRefreshToken(user.getId(),command);

        return new LoginResult(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                accessToken,
                refreshToken
        );
    }

    private boolean hasUserConsent(Long userId){
        return userConsentRepository.findLatestByUserId(userId)
                .filter(consent -> consent.getVersion().equals(CURRENT_TERMS_VERSION))
                .isPresent();
    }

    private void saveUserConsent(Long userId, LoginCommand loginCommand){
        userConsentRepository.save(
                UserConsent.builder()
                        .userId(userId)
                        .version(CURRENT_TERMS_VERSION)
                        .acceptedAt(LocalDateTime.now())
                        .ipAddress(loginCommand.ipAddress())
                        .userAgent(loginCommand.userAgent())
                        .build()
        );
    }

    private String saveRefreshToken(Long userId, LoginCommand loginCommand){

        CreateRefreshTokenCommand createRefreshTokenCommand = CreateRefreshTokenCommand.builder()
                .userId(userId)
                .deviceInfo(loginCommand.userAgent())
                .ipAddress(loginCommand.ipAddress())
                .build();

        return createRefreshToken.execute(createRefreshTokenCommand).getToken();
    }
}
