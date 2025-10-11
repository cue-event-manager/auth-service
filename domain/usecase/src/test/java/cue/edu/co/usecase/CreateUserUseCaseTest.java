package cue.edu.co.usecase;


import cue.edu.co.model.role.Role;
import cue.edu.co.model.role.exceptions.RoleNotFoundException;
import cue.edu.co.model.role.gateways.RoleRepository;
import cue.edu.co.model.security.gateways.PasswordEncoder;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.commands.CreateUserCommand;
import cue.edu.co.model.user.exceptions.EmailAlreadyInUseException;
import cue.edu.co.model.user.exceptions.IdentificationAlreadyInUseException;
import cue.edu.co.model.user.exceptions.PhoneAlreadyInUseException;
import cue.edu.co.model.user.gateways.UserRepository;
import cue.edu.co.usecase.user.CreateUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private CreateUserUseCase useCase;

    private Role role;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);

        useCase = new CreateUserUseCase(userRepository, roleRepository, passwordEncoder);

        role = Role.builder().id(1L).name("USER").build();
    }

    @Test
    @DisplayName("Should create user successfully when all validations pass")
    void shouldCreateUserSuccessfully_whenValid() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand(
                "Juan",
                "Mendez",
                "juan@example.com",
                "plaintext123",
                1L,
                Optional.of("3001112233"),
                Optional.of("CC1234"),
                Optional.of(LocalDate.of(2000, 1, 1)),
                Optional.of("profile.png")
        );

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(userRepository.existsByEmail("juan@example.com")).thenReturn(false);
        when(userRepository.existsByPhoneNumber("3001112233")).thenReturn(false);
        when(userRepository.existsByIdentification("CC1234")).thenReturn(false);
        when(passwordEncoder.encode("plaintext123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        User result = useCase.execute(command);

        // Assert
        assertThat(result.getFirstName()).isEqualTo("Juan");
        assertThat(result.getLastName()).isEqualTo("Mendez");
        assertThat(result.getEmail()).isEqualTo("juan@example.com");
        assertThat(result.getPassword()).isEqualTo("encodedPassword");
        assertThat(result.getRole()).isEqualTo(role);
        assertThat(result.getPhoneNumber()).isEqualTo("3001112233");
        assertThat(result.getIdentification()).isEqualTo("CC1234");
        assertThat(result.getBirthDate()).isEqualTo(LocalDate.of(2000, 1, 1));
        assertThat(result.getProfilePicture()).isEqualTo("profile.png");

        verify(roleRepository).findById(1L);
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Should throw RoleNotFoundException when role does not exist")
    void shouldThrowRoleNotFoundException_whenRoleDoesNotExist() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand(
                "Juan",
                "Mendez",
                "juan@example.com",
                "plaintext123",
                99L,
                Optional.of("3001112233"),
                Optional.of("CC1234"),
                Optional.empty(),
                Optional.empty()
        );

        when(roleRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(RoleNotFoundException.class);

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw EmailAlreadyInUseException when email already exists")
    void shouldThrowEmailAlreadyInUseException_whenEmailAlreadyExists() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand(
                "Juan",
                "Mendez",
                "juan@example.com",
                "plaintext123",
                1L,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(userRepository.existsByEmail("juan@example.com")).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(EmailAlreadyInUseException.class);

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw IdentificationAlreadyInUseException when identification already exists")
    void shouldThrowIdentificationAlreadyInUseException_whenIdentificationAlreadyExists() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand(
                "Juan",
                "Mendez",
                "juan@example.com",
                "plaintext123",
                1L,
                Optional.empty(),
                Optional.of("CC9999"),
                Optional.empty(),
                Optional.empty()
        );

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(userRepository.existsByEmail("juan@example.com")).thenReturn(false);
        when(userRepository.existsByIdentification("CC9999")).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(IdentificationAlreadyInUseException.class);

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw PhoneAlreadyInUseException when phone number already exists")
    void shouldThrowPhoneAlreadyInUseException_whenPhoneAlreadyExists() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand(
                "Juan",
                "Mendez",
                "juan@example.com",
                "plaintext123",
                1L,
                Optional.of("3005555555"),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(userRepository.existsByEmail("juan@example.com")).thenReturn(false);
        when(userRepository.existsByPhoneNumber("3005555555")).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(PhoneAlreadyInUseException.class);

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should allow optional fields to be empty without errors")
    void shouldAllowEmptyOptionals_whenNoPhoneOrIdentificationProvided() {
        // Arrange
        CreateUserCommand command = new CreateUserCommand(
                "Juan",
                "Mendez",
                "juan@example.com",
                "plaintext123",
                1L,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(userRepository.existsByEmail("juan@example.com")).thenReturn(false);
        when(userRepository.existsByPhoneNumber(any())).thenReturn(false);
        when(userRepository.existsByIdentification(any())).thenReturn(false);
        when(passwordEncoder.encode("plaintext123")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        User result = useCase.execute(command);

        // Assert
        assertThat(result.getPhoneNumber()).isNull();
        assertThat(result.getIdentification()).isNull();
        assertThat(result.getProfilePicture()).isNull();
        assertThat(result.getBirthDate()).isNull();
    }
}