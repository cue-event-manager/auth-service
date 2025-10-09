CREATE TABLE password_recoveries (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id BIGINT NOT NULL,
                                     email VARCHAR(255) NOT NULL,
                                     code VARCHAR(20) NOT NULL,
                                     expires_at DATETIME NOT NULL,
                                     used BOOLEAN NOT NULL DEFAULT FALSE,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                     CONSTRAINT fk_password_recovery_user
                                         FOREIGN KEY (user_id)
                                             REFERENCES users(id)
                                             ON DELETE CASCADE
);

CREATE INDEX idx_password_recovery_email ON password_recoveries(email);
CREATE INDEX idx_password_recovery_user_id ON password_recoveries(user_id);
CREATE INDEX idx_password_recovery_code ON password_recoveries(code);