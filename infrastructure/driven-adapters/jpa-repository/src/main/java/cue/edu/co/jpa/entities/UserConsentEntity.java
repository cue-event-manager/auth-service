package cue.edu.co.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import static cue.edu.co.jpa.constants.TableConstant.USER_CONSENTS_TABLE;

@Entity
@Table(name = USER_CONSENTS_TABLE)
@Data
public class UserConsentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 50)
    private String version;

    @Column(nullable = false)
    private LocalDateTime acceptedAt;

    @Column(length = 100)
    private String ipAddress;

    @Column(length = 255)
    private String userAgent;
}
