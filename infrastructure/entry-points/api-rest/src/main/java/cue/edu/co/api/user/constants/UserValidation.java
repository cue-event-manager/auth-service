package cue.edu.co.api.user.constants;

public class UserValidation {
    private UserValidation() {}

    public static final String FIRST_NAME_REQUIRED = "First name is required";
    public static final String LAST_NAME_REQUIRED = "Last name is required";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String EMAIL_INVALID = "Email format is invalid";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String PASSWORD_MIN_LENGTH = "Password must have at least 8 characters";
    public static final String ROLE_ID_REQUIRED = "Role ID is required";
    public static final String PHONE_INVALID = "Phone number must contain only digits";
    public static final String IDENTIFICATION_INVALID = "Identification must not be blank";
}
