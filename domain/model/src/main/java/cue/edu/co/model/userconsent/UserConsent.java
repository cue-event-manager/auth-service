package cue.edu.co.model.userconsent;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserConsent {
    private Long id;
    private Long userId;
    private String version;
    private LocalDateTime acceptedAt;
    private String ipAddress;
    private String userAgent;
}