-- V7__Insert_default_admin_user.sql
-- Migration para inserir usuário administrador padrão
-- Senha padrão: admin123 (deve ser alterada no primeiro login)
INSERT INTO users.users_data (username, email, password, first_name, last_name, role, active, created_at)
VALUES ('admin',
        'admin@chromamon.com',
        '$2a$10$H76BCwUcv8Kvl26IObpAeOFAkCVbtUbI9o74Gok2aJIhndfzUJdwe',
        'System',
        'Admin',
        'ADMIN',
        true,
        CURRENT_TIMESTAMP);