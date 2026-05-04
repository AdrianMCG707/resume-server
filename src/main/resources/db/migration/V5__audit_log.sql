CREATE TABLE audit_log (
                           id          BIGSERIAL PRIMARY KEY,
                           user_email  VARCHAR(255) NOT NULL,
                           action      VARCHAR(50)  NOT NULL,
                           entity_type VARCHAR(100) NOT NULL,
                           entity_id   BIGINT,
                           created_at  TIMESTAMP    NOT NULL DEFAULT NOW()
);