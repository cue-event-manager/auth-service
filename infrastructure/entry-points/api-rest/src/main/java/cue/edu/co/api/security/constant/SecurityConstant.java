package cue.edu.co.api.security.constant;

public final class SecurityConstant {

    private SecurityConstant() {}

    public static final String GATEWAY_INTERNAL_HEADER = "X-Gateway-Secret";
    public static final String USER_ID_HEADER = "X-User-Id";
    public static final String USER_ROLE_HEADER = "X-User-Role";

    public static final String ERROR_UNAUTHORIZED_GATEWAY = "Unauthorized - Request not from Gateway";
}