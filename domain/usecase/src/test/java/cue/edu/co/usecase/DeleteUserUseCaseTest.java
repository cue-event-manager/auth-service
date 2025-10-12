package cue.edu.co.usecase;

import cue.edu.co.model.user.commands.DeleteUserCommand;
import cue.edu.co.model.user.exceptions.UserNotFoundException;
import cue.edu.co.model.user.gateways.UserRepository;
import cue.edu.co.usecase.user.DeleteUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class DeleteUserUseCaseTest {
    private UserRepository userRepository;
    private DeleteUserUseCase deleteUserUseCase;

    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);

        deleteUserUseCase = new DeleteUserUseCase(userRepository);
    }

    @Test
    @DisplayName("Should delete user successfully")
    void shouldDeleteUserSuccessfully_whenValid(){
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(1L);

        when(userRepository.existsById(deleteUserCommand.id())).thenReturn(true);

        deleteUserUseCase.execute(deleteUserCommand);
    }

    @Test
    @DisplayName("Should throw UserNotFoundException when user does not exist")
    void shouldThrowUserNotFoundException_whenUserDoesNotExist(){
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(1L);

        when(userRepository.existsById(deleteUserCommand.id())).thenReturn(false);

        assertThatThrownBy(() ->deleteUserUseCase.execute(deleteUserCommand))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository, never()).deleteById(deleteUserCommand.id());

    }
}
