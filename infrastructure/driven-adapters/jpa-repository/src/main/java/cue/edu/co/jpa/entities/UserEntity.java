package cue.edu.co.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@SoftDelete
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne
    private RoleEntity role;

    @Column(nullable = true, unique = true)
    private String phoneNumber;

    @Column(nullable = true, unique = true)
    private String identification;

    @Column(nullable = true)
    private LocalDate birthDate;

    @Column(nullable = true)
    private String profilePicture;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
