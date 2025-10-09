package cue.edu.co.api.auth.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordSecureValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordSecure {

    String message() default "Password must be at least 8 characters long and contain uppercase, lowercase, number, and special character.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}