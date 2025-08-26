-- V2__Create_user_permissions_table.sql
-- Migration para criar a tabela de permissões personalizadas dos usuários
CREATE TABLE users.user_permissions
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT      NOT NULL,
    permission VARCHAR(50) NOT NULL,
    granted    BOOLEAN     NOT NULL DEFAULT true,
    created_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_permissions_user FOREIGN KEY (user_id) REFERENCES users.users_data (id) ON DELETE CASCADE,
    CONSTRAINT uk_user_permission UNIQUE (user_id, permission),
    CONSTRAINT user_permissions_permission_check CHECK (permission IN (
                                                                       'analysis:read', 'analysis:create',
                                                                       'analysis:update', 'analysis:delete',
                                                                       'diagnostic:read', 'diagnostic:create',
                                                                       'diagnostic:update', 'diagnostic:delete',
                                                                       'transformer:read', 'transformer:create',
                                                                       'transformer:update', 'transformer:delete',
                                                                       'report:read', 'report:create', 'report:update',
                                                                       'report:delete',
                                                                       'user:read', 'user:create', 'user:update',
                                                                       'user:delete',
                                                                       'audit:read', 'audit:delete'
        ))
);

-- Índices
CREATE INDEX idx_user_permissions_user_id ON users.user_permissions (user_id);
CREATE INDEX idx_user_permissions_permission ON users.user_permissions (permission);
CREATE INDEX idx_user_permissions_granted ON users.user_permissions (granted);