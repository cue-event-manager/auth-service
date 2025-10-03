package cue.edu.co.model.user;

import cue.edu.co.model.role.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String phoneNumber;
    private String identification;
    private LocalDate birthDate;
    private String profilePicture;
    private LocalDateTime createdAt;
}
