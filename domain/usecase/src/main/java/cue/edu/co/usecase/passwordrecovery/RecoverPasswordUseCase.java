package cue.edu.co.usecase.passwordrecovery;

import cue.edu.co.model.common.constants.EventType;
import cue.edu.co.model.common.gateways.EventPublisher;
import cue.edu.co.model.common.models.Event;
import cue.edu.co.model.common.utils.EventBuilder;
import cue.edu.co.model.passwordrecovery.PasswordRecovery;
import cue.edu.co.model.passwordrecovery.commands.RecoverPasswordCommand;
import cue.edu.co.model.passwordrecovery.event.RecoverPasswordEvent;
import cue.edu.co.model.passwordrecovery.gateways.PasswordRecoveryRepository;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.exceptions.UserNotFoundException;
import cue.edu.co.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static cue.edu.co.model.passwordrecovery.constants.PasswordRecoveryConstant.CODE_LENGTH;
import static cue.edu.co.model.passwordrecovery.constants.PasswordRecoveryConstant.EXPIRATION_MINUTES;

@RequiredArgsConstructor
public class RecoverPasswordUseCase {

    private final PasswordRecoveryRepository recoveryRepository;
    private final EventPublisher eventPublisher;
    private final UserRepository userRepository;

    public void execute(RecoverPasswordCommand command) {
        User user = findUserByEmail(command.email());

        invalidatePreviousPasswordRecoveries(command.email());

        String recoveryCode = generateNumericCode();
        LocalDateTime expiration = calculateExpiration();

        PasswordRecovery recovery = buildPasswordRecovery(user.getId(), user.getEmail(), recoveryCode, expiration);
        recoveryRepository.save(recovery);

        RecoverPasswordEvent payload = buildRecoverPasswordPayload(user, recoveryCode, expiration);
        Event event = EventBuilder.build(EventType.RECOVER_PASSWORD, payload);

        eventPublisher.publish(event);
    }


    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    private void invalidatePreviousPasswordRecoveries(String email) {
        recoveryRepository.invalidateAllByEmail(email);
    }

    private LocalDateTime calculateExpiration() {
        return LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);
    }

    private String generateNumericCode() {
        Random random = new Random();
        String digits = "0123456789";
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(digits.charAt(random.nextInt(digits.length())));
        }
        return code.toString();
    }

    private PasswordRecovery buildPasswordRecovery(Long userId, String email, String recoveryCode, LocalDateTime expiration) {
        return PasswordRecovery.builder()
                .userId(userId)
                .email(email)
                .code(recoveryCode)
                .expiresAt(expiration)
                .used(false)
                .build();
    }

    private RecoverPasswordEvent buildRecoverPasswordPayload(User user, String recoveryCode, LocalDateTime expiration) {
        return RecoverPasswordEvent.builder()
                .email(user.getEmail())
                .name(user.getFirstName())
                .recoveryCode(recoveryCode)
                .expirationTime(expiration.toString())
                .build();
    }
}