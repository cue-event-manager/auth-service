package cue.edu.co.config;

import cue.edu.co.model.auth.gateways.AuthContext;
import cue.edu.co.model.auth.gateways.TokenProvider;
import cue.edu.co.model.common.gateways.EventPublisher;
import cue.edu.co.model.passwordrecovery.gateways.PasswordRecoveryRepository;
import cue.edu.co.model.refreshtoken.gateways.RefreshTokenRepository;
import cue.edu.co.model.role.gateways.RoleRepository;
import cue.edu.co.model.security.gateways.PasswordEncoder;
import cue.edu.co.model.user.gateways.UserRepository;
import cue.edu.co.model.userconsent.gateways.UserConsentRepository;
import cue.edu.co.usecase.auth.*;
import cue.edu.co.usecase.passwordrecovery.RecoverPasswordUseCase;
import cue.edu.co.usecase.passwordrecovery.ResetPasswordUseCase;
import cue.edu.co.usecase.refreshtoken.CreateRefreshTokenUseCase;
import cue.edu.co.usecase.user.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "cue.edu.co.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {
    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository,
                                               RoleRepository roleRepository,
                                               PasswordEncoder passwordEncoder){
        return new CreateUserUseCase(userRepository,roleRepository, passwordEncoder);
    }

    @Bean
    public  CreateRefreshTokenUseCase createRefreshToken(RefreshTokenRepository refreshTokenRepository){
        return new CreateRefreshTokenUseCase(refreshTokenRepository);
    }

    @Bean
    public LoginUseCase loginUseCase(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     UserConsentRepository userConsentRepository,
                                     TokenProvider tokenProvider,
                                     CreateRefreshTokenUseCase createRefreshTokenUseCase){
        return new LoginUseCase(userRepository,passwordEncoder,userConsentRepository,tokenProvider,createRefreshTokenUseCase);
    }

    @Bean
    public LogoutUseCase logoutUseCase(RefreshTokenRepository refreshTokenRepository){
        return new LogoutUseCase(refreshTokenRepository);
    }
    @Bean
    public GetCurrentUserUseCase getCurrentUserUseCase(AuthContext authContext,
                                                       UserRepository userRepository
                                                       ){
        return new GetCurrentUserUseCase(authContext,userRepository);
    }

    @Bean
    public RefreshTokenUseCase refreshTokenUseCase(RefreshTokenRepository refreshTokenRepository,
                                                   UserRepository userRepository,
                                                   TokenProvider tokenProvider){
        return new RefreshTokenUseCase(refreshTokenRepository,userRepository,tokenProvider);
    }

    @Bean
    public UpdateUserProfileUseCase updateUserProfileUseCase(GetCurrentUserUseCase getCurrentUserUseCase,
                                                             UserRepository userRepository,
                                                             PasswordEncoder passwordEncoder,
                                                             RefreshTokenRepository refreshTokenRepository
    ){
        return new UpdateUserProfileUseCase(getCurrentUserUseCase, userRepository, passwordEncoder, refreshTokenRepository);
    }

    @Bean
    public RecoverPasswordUseCase recoverPasswordUseCase(PasswordRecoveryRepository passwordRecoveryRepository,
                                                         EventPublisher eventPublisher,
                                                         UserRepository userRepository){
        return new RecoverPasswordUseCase(passwordRecoveryRepository, eventPublisher, userRepository);
    }

    @Bean
    public ResetPasswordUseCase resetPasswordUseCase(PasswordRecoveryRepository passwordRecoveryRepository,
                                                     PasswordEncoder passwordEncoder,
                                                     UserRepository userRepository,
                                                     RefreshTokenRepository refreshTokenRepository,
                                                     EventPublisher eventPublisher){
        return new ResetPasswordUseCase(passwordRecoveryRepository, passwordEncoder, userRepository, refreshTokenRepository, eventPublisher);
    }
}

