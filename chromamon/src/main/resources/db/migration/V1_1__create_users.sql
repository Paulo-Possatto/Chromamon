CREATE TABLE users.users_data
(
    id            BIGSERIAL PRIMARY KEY,
    uuid          UUID NOT NULL UNIQUE,
    id_code       VARCHAR(6) NOT NULL UNIQUE,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password      VARCHAR(255) NOT NULL,
    first_name    VARCHAR(100) NOT NULL,
    last_name     VARCHAR(100) NOT NULL,
    role          VARCHAR(20)  NOT NULL CHECK (role IN ('ADMIN', 'OPERATIONS', 'MAINTENANCE', 'ENGINEERING',
                                                        'PLANNING', 'CUSTOMER_SERVICE', 'BUSINESS', 'MANAGEMENT',
                                                        'ADMINISTRATION', 'TECHNOLOGY', 'ANALYST', 'SAFETY',
                                                        'USER')),
    active        BOOLEAN      NOT NULL DEFAULT true,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    last_login_at TIMESTAMP,

    CONSTRAINT users_username_check CHECK (LENGTH(username) >= 3),
    CONSTRAINT users_email_check CHECK (email ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'
)
    );

CREATE INDEX idx_users_username ON users.users_data (username);
CREATE INDEX idx_users_email ON users.users_data (email);
CREATE INDEX idx_users_role ON users.users_data (role);
CREATE INDEX idx_users_active ON users.users_data (active);