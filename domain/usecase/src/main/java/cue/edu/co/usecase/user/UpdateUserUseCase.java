package cue.edu.co.usecase.user;

import cue.edu.co.model.role.Role;
import cue.edu.co.model.role.exceptions.RoleNotFoundException;
import cue.edu.co.model.role.gateways.RoleRepository;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.commands.UpdateUserCommand;
import cue.edu.co.model.user.exceptions.EmailAlreadyInUseException;
import cue.edu.co.model.user.exceptions.IdentificationAlreadyInUseException;
import cue.edu.co.model.user.exceptions.UserNotFoundException;
import cue.edu.co.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User execute(UpdateUserCommand command) {
        User existingUser = findExistingUser(command.id());

        Role role = command.roleId()
                .map(this::findRole)
                .orElse(existingUser.getRole());

        validateUniqueFields(command, existingUser);

        User updatedUser = applyUpdates(existingUser, command, role);

        updatedUser = userRepository.save(updatedUser);

        return updatedUser;
    }


    private User findExistingUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    private Role findRole(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(RoleNotFoundException::new);
    }

    private void validateUniqueFields(UpdateUserCommand command, User existingUser) {
        validateUniqueEmail(command, existingUser);
        validateUniqueIdentification(command, existingUser);
    }

    private void validateUniqueEmail(UpdateUserCommand command, User existingUser) {
        command.email()
                .filter(email -> !email.equals(existingUser.getEmail())).
                flatMap(email -> userRepository.findByEmail(email)
                        .filter(user -> !user.getId().equals(existingUser.getId()))).ifPresent(u -> {
                    throw new EmailAlreadyInUseException();
                });
    }

    private void validateUniqueIdentification(UpdateUserCommand command, User existingUser) {
        command.identification()
                .filter(id -> !id.equals(existingUser.getIdentification()))
                .flatMap(id -> userRepository.findByIdentification(id)
                        .filter(user -> !user.getId().equals(existingUser.getId()))).ifPresent(u -> {
                    throw new IdentificationAlreadyInUseException();
                });
    }

    private User applyUpdates(User existingUser, UpdateUserCommand command, Role role) {
        return User.builder()
                .id(existingUser.getId())
                .firstName(command.firstName().orElse(existingUser.getFirstName()))
                .lastName(command.lastName().orElse(existingUser.getLastName()))
                .email(command.email().orElse(existingUser.getEmail()))
                .password(existingUser.getPassword())
                .phoneNumber(command.phoneNumber().orElse(existingUser.getPhoneNumber()))
                .identification(command.identification().orElse(existingUser.getIdentification()))
                .birthDate(command.birthDate().orElse(existingUser.getBirthDate()))
                .role(role)
                .build();
    }
}
