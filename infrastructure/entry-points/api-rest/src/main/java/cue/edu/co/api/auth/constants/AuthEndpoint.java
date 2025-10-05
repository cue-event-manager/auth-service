package cue.edu.co.api.auth.constants;

public class AuthEndpoint {
    private AuthEndpoint(){}
    public static final String AUTH_BASE = "/api/auth";
    public static final String LOGIN_ENDPOINT =  AUTH_BASE + "/login";
    public static final String ME_ENDPOINT =  AUTH_BASE + "/me";
    public static final String REFRESH_TOKEN_ENDPOINT = AUTH_BASE + "/refresh";
    public static final String LOGOUT_ENDPOINT = AUTH_BASE + "/logout";
}
