package cue.edu.co.usecase.user;

import cue.edu.co.model.role.Role;
import cue.edu.co.model.role.exceptions.RoleNotFoundException;
import cue.edu.co.model.role.gateways.RoleRepository;
import cue.edu.co.model.security.gateways.PasswordEncoder;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.commands.CreateUserCommand;
import cue.edu.co.model.user.exceptions.EmailAlreadyInUseException;
import cue.edu.co.model.user.exceptions.IdentificationAlreadyInUseException;
import cue.edu.co.model.user.exceptions.PhoneAlreadyInUseException;
import cue.edu.co.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;


@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User execute(CreateUserCommand command) {
        Role role = validateRoleExists(command.roleId());

        validateUnique(
                Optional.of(command.email()),
                userRepository::existsByEmail,
                new EmailAlreadyInUseException()
        );

        validateUnique(
                command.identification(),
                userRepository::existsByIdentification,
                new IdentificationAlreadyInUseException()
        );

        validateUnique(
                command.phoneNumber(),
                userRepository::existsByPhoneNumber,
                new PhoneAlreadyInUseException()
        );

        User user = command.toDomain(role, passwordEncoder.encode(command.password()));

        return userRepository.save(user);
    }


    private Role validateRoleExists(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(RoleNotFoundException::new);
    }

    private <T> void validateUnique(Optional<T> value, Predicate<T> existsCheck, RuntimeException ex) {
        value.ifPresent(v -> {
            if (existsCheck.test(v)) throw ex;
        });
    }
}
