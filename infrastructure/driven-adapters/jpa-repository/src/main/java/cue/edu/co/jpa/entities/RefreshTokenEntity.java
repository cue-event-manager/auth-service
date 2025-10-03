package cue.edu.co.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static cue.edu.co.jpa.constants.TableConstant.REFRESH_TOKENS_TABLE;

@Data
@Entity
@Table(name = REFRESH_TOKENS_TABLE)
@EntityListeners(AuditingEntityListener.class)
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private boolean revoked;

    @Column(nullable = false)
    private String deviceInfo;

    @Column(nullable = false)
    private String ipAddress;

}
