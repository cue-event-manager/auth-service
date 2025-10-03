package cue.edu.co.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static cue.edu.co.jpa.constants.TableConstant.USERS_TABLE;

@Data
@Entity
@SoftDelete
@Table(name = USERS_TABLE)
@EntityListeners(AuditingEntityListener.class)
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_user_role"))
    private RoleEntity role;

    @Column(nullable = true, unique = true)
    private String phoneNumber;

    @Column(nullable = true, unique = true)
    private String identification;

    @Column(nullable = true)
    private LocalDate birthDate;

    @Column(nullable = true)
    private String profilePicture;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

}
