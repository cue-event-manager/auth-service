package cue.edu.co.model.common.constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventType {
    USER_CREATED("UserCreated"),
    RECOVER_PASSWORD("RecoverPasswordEvent"),
    PASSWORD_RESET("PasswordResetEvent");

    private final String type;
}