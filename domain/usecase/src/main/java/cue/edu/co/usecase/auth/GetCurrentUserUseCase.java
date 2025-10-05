package cue.edu.co.usecase.auth;

import cue.edu.co.model.auth.gateways.AuthContext;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.exceptions.UserNotFoundException;
import cue.edu.co.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetCurrentUserUseCase {

    private final AuthContext authContext;
    private final UserRepository userRepository;

    public User execute(){
        return  userRepository
                .findById(authContext.getCurrentUserId())
                .orElseThrow(UserNotFoundException::new);
    }


}
