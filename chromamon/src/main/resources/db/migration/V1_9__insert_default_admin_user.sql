INSERT INTO users.users_data (username, email, password, first_name, last_name, role, active, created_at)
VALUES ('admin',
        'admin@chromamon.com',
        '$2a$10$r8/eUA3v3GqkOgqijRn.TuhcCfRsFiDM1gCyGU.pRne.HJTkQBE1a',
        'System',
        'Admin',
        'ADMIN',
        true,
        CURRENT_TIMESTAMP);