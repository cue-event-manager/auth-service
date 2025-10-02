package cue.edu.co.config;

import cue.edu.co.model.role.gateways.RoleRepository;
import cue.edu.co.model.security.gateways.PasswordEncoder;
import cue.edu.co.model.user.gateways.UserRepository;
import cue.edu.co.usecase.user.CreateUserUseCase;
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

    public CreateUserUseCase createUserUseCase(UserRepository userRepository,
                                               RoleRepository roleRepository,
                                               PasswordEncoder passwordEncoder){
        return new CreateUserUseCase(userRepository,roleRepository, passwordEncoder);
    }
}
