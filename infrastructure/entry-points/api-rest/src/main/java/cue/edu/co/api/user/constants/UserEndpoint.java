package cue.edu.co.api.user.constants;

public class UserEndpoint {
    private UserEndpoint(){}
    public static final String USER_BASE = "/api/users";
    public static final String CREATE_USER_ENDPOINT = USER_BASE + "/create";
    public static final String UPDATE_USER_ENDPOINT = USER_BASE + "/{id}/update";
    public static final String DELETE_USER_ENDPOINT = USER_BASE + "/{id}/delete";
}
