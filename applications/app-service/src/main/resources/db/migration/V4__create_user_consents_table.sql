CREATE TABLE user_consents (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               version VARCHAR(50) NOT NULL,
                               accepted_at TIMESTAMP NOT NULL,
                               ip_address VARCHAR(100),
                               user_agent VARCHAR(255)
);

CREATE INDEX idx_user_consents_user_id ON user_consents(user_id);
