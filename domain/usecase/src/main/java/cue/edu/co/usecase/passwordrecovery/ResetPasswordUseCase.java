package cue.edu.co.usecase.passwordrecovery;

import cue.edu.co.model.common.constants.EventType;
import cue.edu.co.model.common.gateways.EventPublisher;
import cue.edu.co.model.common.models.Event;
import cue.edu.co.model.common.utils.EventBuilder;
import cue.edu.co.model.passwordrecovery.PasswordRecovery;
import cue.edu.co.model.passwordrecovery.commands.ResetPasswordCommand;
import cue.edu.co.model.passwordrecovery.event.PasswordResetEvent;
import cue.edu.co.model.passwordrecovery.exceptions.ExpiredPasswordRecoveryCodeException;
import cue.edu.co.model.passwordrecovery.exceptions.InvalidPasswordRecoveryCodeException;
import cue.edu.co.model.passwordrecovery.gateways.PasswordRecoveryRepository;
import cue.edu.co.model.refreshtoken.gateways.RefreshTokenRepository;
import cue.edu.co.model.security.gateways.PasswordEncoder;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.exceptions.UserNotFoundException;
import cue.edu.co.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@RequiredArgsConstructor
public class ResetPasswordUseCase {

    private final PasswordRecoveryRepository passwordRecoveryRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final EventPublisher eventPublisher;

    public void execute(ResetPasswordCommand command) {
        PasswordRecovery recovery = validateRecoveryCode(command.email(), command.code());
        User user = findUserByEmail(command.email());

        updatePassword(user, command.newPassword());
        markRecoveryAsUsed(recovery);
        revokeUserSessions(user);
        publishPasswordResetEvent(user);
    }

    private PasswordRecovery validateRecoveryCode(String email, String code) {
        PasswordRecovery recovery = passwordRecoveryRepository
                .findByEmailAndCode(email, code)
                .orElseThrow(InvalidPasswordRecoveryCodeException::new);

        if (recovery.isUsed() || LocalDateTime.now().isAfter(recovery.getExpiresAt())) {
            throw new ExpiredPasswordRecoveryCodeException();
        }

        return recovery;
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    private void updatePassword(User user, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    private void markRecoveryAsUsed(PasswordRecovery recovery) {
        recovery.setUsed(true);
        passwordRecoveryRepository.save(recovery);
    }

    private void revokeUserSessions(User user) {
        refreshTokenRepository.revokeAllByUserId(user.getId());
    }

    private void publishPasswordResetEvent(User user) {
        PasswordResetEvent payload = PasswordResetEvent.builder()
                .email(user.getEmail())
                .name(user.getFirstName())
                .occurredAt(LocalDateTime.now().toString())
                .build();

        Event event = EventBuilder.build(EventType.PASSWORD_RESET, payload);

        eventPublisher.publish(event);
    }
}
