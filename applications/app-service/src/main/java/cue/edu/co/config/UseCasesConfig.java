package cue.edu.co.config;

import cue.edu.co.model.auth.gateways.AuthContext;
import cue.edu.co.model.auth.gateways.TokenProvider;
import cue.edu.co.model.refreshtoken.gateways.RefreshTokenRepository;
import cue.edu.co.model.role.gateways.RoleRepository;
import cue.edu.co.model.security.gateways.PasswordEncoder;
import cue.edu.co.model.user.gateways.UserRepository;
import cue.edu.co.model.userconsent.gateways.UserConsentRepository;
import cue.edu.co.usecase.auth.GetCurrentUserUseCase;
import cue.edu.co.usecase.auth.LoginUseCase;
import cue.edu.co.usecase.refreshtoken.CreateRefreshTokenUseCase;
import cue.edu.co.usecase.refreshtoken.RevokeRefreshTokenUseCase;
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
    public RevokeRefreshTokenUseCase revokeRefreshTokenUseCase(RefreshTokenRepository refreshTokenRepository){
        return new RevokeRefreshTokenUseCase(refreshTokenRepository);
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
    public GetCurrentUserUseCase getCurrentUserUseCase(AuthContext authContext,
                                                       UserRepository userRepository
                                                       ){
        return new GetCurrentUserUseCase(authContext,userRepository);
    }
}
