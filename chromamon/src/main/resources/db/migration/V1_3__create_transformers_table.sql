-- V3__Create_transformers_table.sql
-- Migration para criar a tabela de transformadores
CREATE TABLE transformers.transformers
(
    id                   BIGSERIAL PRIMARY KEY,
    tag                  VARCHAR(50)    NOT NULL UNIQUE,
    serial_number        VARCHAR(100)   NOT NULL UNIQUE,
    manufacturer         VARCHAR(100)   NOT NULL,
    model                VARCHAR(100),
    year_manufacture     INTEGER,
    nominal_power_kva    DECIMAL(10, 2) NOT NULL,
    primary_voltage_kv   DECIMAL(8, 2)  NOT NULL,
    secondary_voltage_kv DECIMAL(8, 2)  NOT NULL,
    connection_type      VARCHAR(20)    NOT NULL,
    cooling_type         VARCHAR(20)    NOT NULL,
    installation_date    DATE,
    location             VARCHAR(255)   NOT NULL,
    substation           VARCHAR(100)   NOT NULL,
    bay                  VARCHAR(50),
    status               VARCHAR(20)    NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'INACTIVE', 'MAINTENANCE', 'RETIRED')),
    oil_volume_liters    DECIMAL(8, 2),
    weight_kg            DECIMAL(10, 2),
    description          TEXT,
    created_at           TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at           TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    created_by           BIGINT         NOT NULL,
    updated_by           BIGINT,

    CONSTRAINT fk_transformers_created_by FOREIGN KEY (created_by) REFERENCES users.users_data (id),
    CONSTRAINT fk_transformers_updated_by FOREIGN KEY (updated_by) REFERENCES users.users_data (id),
    CONSTRAINT transformers_tag_check CHECK (LENGTH(tag) >= 3),
    CONSTRAINT transformers_power_check CHECK (nominal_power_kva > 0),
    CONSTRAINT transformers_voltage_check CHECK (primary_voltage_kv > 0 AND secondary_voltage_kv > 0)
);

-- Índices
CREATE INDEX idx_transformers_tag ON transformers.transformers (tag);
CREATE INDEX idx_transformers_serial_number ON transformers.transformers (serial_number);
CREATE INDEX idx_transformers_substation ON transformers.transformers (substation);
CREATE INDEX idx_transformers_status ON transformers.transformers (status);
CREATE INDEX idx_transformers_location ON transformers.transformers (location);