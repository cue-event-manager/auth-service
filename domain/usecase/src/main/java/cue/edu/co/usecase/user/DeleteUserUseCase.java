package cue.edu.co.usecase.user;

import cue.edu.co.model.user.commands.DeleteUserCommand;
import cue.edu.co.model.user.exceptions.UserNotFoundException;
import cue.edu.co.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public void execute(DeleteUserCommand command){
        if(!userRepository.existsById(command.id())) throw new UserNotFoundException();

        userRepository.deleteById(command.id());
    }
}
