package cue.edu.co.usecase;

import cue.edu.co.model.role.Role;
import cue.edu.co.model.role.exceptions.RoleNotFoundException;
import cue.edu.co.model.role.gateways.RoleRepository;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.commands.UpdateUserCommand;
import cue.edu.co.model.user.exceptions.EmailAlreadyInUseException;
import cue.edu.co.model.user.exceptions.IdentificationAlreadyInUseException;
import cue.edu.co.model.user.exceptions.UserNotFoundException;
import cue.edu.co.model.user.gateways.UserRepository;
import cue.edu.co.usecase.user.UpdateUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateUserUseCaseTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UpdateUserUseCase useCase;

    private User existingUser;
    private Role existingRole;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        useCase = new UpdateUserUseCase(userRepository, roleRepository);

        existingRole = Role.builder().id(1L).name("USER").build();

        existingUser = User.builder()
                .id(100L)
                .firstName("Juan")
                .lastName("Mendez")
                .email("juan@old.com")
                .phoneNumber("1111")
                .identification("ABC123")
                .birthDate(LocalDate.of(2000, 1, 1))
                .role(existingRole)
                .build();
    }



    @Test
    @DisplayName("Should update user successfully when all optional fields are provided")
    void shouldUpdateUserSuccessfully_whenAllFieldsPresent() {
        // Arrange
        Role newRole = Role.builder().id(2L).name("ADMIN").build();
        UpdateUserCommand command = UpdateUserCommand.builder()
                .id(100L)
                .firstName(Optional.of("Gerardo"))
                .lastName(Optional.of("López"))
                .email(Optional.of("juan@new.com"))
                .phoneNumber(Optional.of("9999"))
                .identification(Optional.of("XYZ789"))
                .birthDate(Optional.of(LocalDate.of(1999, 12, 31)))
                .roleId(Optional.of(2L))
                .build();

        when(userRepository.findById(100L)).thenReturn(Optional.of(existingUser));
        when(roleRepository.findById(2L)).thenReturn(Optional.of(newRole));
        when(userRepository.findByEmail("juan@new.com")).thenReturn(Optional.empty());
        when(userRepository.findByIdentification("XYZ789")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        User result = useCase.execute(command);

        // Assert
        assertThat(result.getFirstName()).isEqualTo("Gerardo");
        assertThat(result.getLastName()).isEqualTo("López");
        assertThat(result.getEmail()).isEqualTo("juan@new.com");
        assertThat(result.getPhoneNumber()).isEqualTo("9999");
        assertThat(result.getIdentification()).isEqualTo("XYZ789");
        assertThat(result.getBirthDate()).isEqualTo(LocalDate.of(1999, 12, 31));
        assertThat(result.getRole()).isEqualTo(newRole);

        verify(userRepository).findById(100L);
        verify(roleRepository).findById(2L);
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Should update only provided fields and keep others unchanged")
    void shouldUpdateOnlyProvidedFields_whenSomeOptionalsEmpty() {
        // Arrange
        UpdateUserCommand command = UpdateUserCommand.builder()
                .id(100L)
                .firstName(Optional.of("Gerardo"))
                .lastName(Optional.empty())
                .email(Optional.empty())
                .phoneNumber(Optional.empty())
                .identification(Optional.empty())
                .birthDate(Optional.empty())
                .roleId(Optional.empty())
                .build();

        when(userRepository.findById(100L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        User result = useCase.execute(command);

        // Assert
        assertThat(result.getFirstName()).isEqualTo("Gerardo");
        assertThat(result.getLastName()).isEqualTo("Mendez");
        assertThat(result.getRole()).isEqualTo(existingRole);

        verify(userRepository).save(any(User.class));
    }


    @Test
    @DisplayName("Should throw UserNotFoundException when user does not exist")
    void shouldThrowUserNotFoundException_whenUserDoesNotExist() {
        // Arrange
        UpdateUserCommand command = UpdateUserCommand.builder()
                .id(999L)
                .firstName(Optional.of("Test"))
                .build();

        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(UserNotFoundException.class);

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw RoleNotFoundException when roleId provided does not exist")
    void shouldThrowRoleNotFoundException_whenRoleDoesNotExist() {
        // Arrange
        UpdateUserCommand command = UpdateUserCommand.builder()
                .id(100L)
                .roleId(Optional.of(99L))
                .build();

        when(userRepository.findById(100L)).thenReturn(Optional.of(existingUser));
        when(roleRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(RoleNotFoundException.class);
    }

    @Test
    @DisplayName("Should throw EmailAlreadyInUseException when email is already used by another user")
    void shouldThrowEmailAlreadyInUseException_whenEmailAlreadyUsed() {
        // Arrange
        User otherUser = User.builder().id(200L).email("new@domain.com").build();
        UpdateUserCommand command = UpdateUserCommand.builder()
                .id(100L)
                .email(Optional.of("new@domain.com"))
                .build();

        when(userRepository.findById(100L)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail("new@domain.com")).thenReturn(Optional.of(otherUser));

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(EmailAlreadyInUseException.class);

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw IdentificationAlreadyInUseException when identification is already used by another user")
    void shouldThrowIdentificationAlreadyInUseException_whenIdentificationAlreadyUsed() {
        // Arrange
        User otherUser = User.builder().id(300L).identification("XYZ123").build();
        UpdateUserCommand command = UpdateUserCommand.builder()
                .id(100L)
                .identification(Optional.of("XYZ123"))
                .build();

        when(userRepository.findById(100L)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByIdentification("XYZ123")).thenReturn(Optional.of(otherUser));

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(IdentificationAlreadyInUseException.class);

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should keep same role when roleId is empty")
    void shouldKeepSameRole_whenRoleIdEmpty() {
        // Arrange
        UpdateUserCommand command = UpdateUserCommand.builder()
                .id(100L)
                .roleId(Optional.empty())
                .build();

        when(userRepository.findById(100L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        User result = useCase.execute(command);

        // Assert
        assertThat(result.getRole()).isEqualTo(existingRole);
        verify(roleRepository, never()).findById(any());
    }
}