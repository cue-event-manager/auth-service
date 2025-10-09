package cue.edu.co.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import static cue.edu.co.jpa.constants.TableConstant.PASSWORD_RECOVERIES_TABLE;

@Data
@Entity
@Table(name = PASSWORD_RECOVERIES_TABLE)
public class PasswordRecoveryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private boolean used;
}
