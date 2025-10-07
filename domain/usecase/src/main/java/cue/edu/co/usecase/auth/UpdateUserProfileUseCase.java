package cue.edu.co.usecase.auth;

import cue.edu.co.model.auth.commands.UpdateProfileCommand;
import cue.edu.co.model.auth.exceptions.InvalidCurrentPasswordException;
import cue.edu.co.model.refreshtoken.gateways.RefreshTokenRepository;
import cue.edu.co.model.security.gateways.PasswordEncoder;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.exceptions.EmailAlreadyInUseException;
import cue.edu.co.model.user.exceptions.PhoneAlreadyInUseException;
import cue.edu.co.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserProfileUseCase {

    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public User execute(UpdateProfileCommand command) {
        User user = getCurrentUserUseCase.execute();

        updateBasicInfo(user, command);
        updateEmailIfChanged(user, command);
        updatePhoneIfPresent(user, command);
        updatePasswordIfValid(user, command);

        return userRepository.save(user);
    }

    private void updateBasicInfo(User user, UpdateProfileCommand command) {
        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
    }

    private void updateEmailIfChanged(User user, UpdateProfileCommand command) {
        String newEmail = command.email();
        if (!user.getEmail().equals(newEmail)) {
            if (userRepository.existsByEmail(newEmail)) {
                throw new EmailAlreadyInUseException();
            }
            user.setEmail(newEmail);
        }
    }

    private void updatePhoneIfPresent(User user, UpdateProfileCommand command) {
        command.phoneNumber().ifPresent(newPhone -> {
            if (!newPhone.equals(user.getPhoneNumber())) {
                if (userRepository.existsByPhoneNumber(newPhone)) {
                    throw new PhoneAlreadyInUseException();
                }
                user.setPhoneNumber(newPhone);
            }
        });
    }

    private void updatePasswordIfValid(User user, UpdateProfileCommand command) {
        if (command.currentPassword().isEmpty() || command.newPassword().isEmpty()) return;

        String currentPassword = command.currentPassword().get();
        String newPassword = command.newPassword().get();

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new InvalidCurrentPasswordException();
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        revokeAllUserTokens(user);
    }

    private void revokeAllUserTokens(User user){
        refreshTokenRepository.revokeAllByUserId(user.getId());
    }
}