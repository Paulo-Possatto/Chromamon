CREATE TABLE reports.reports
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    type            VARCHAR(50)  NOT NULL CHECK (type IN
                                                 ('ANALYSIS', 'DIAGNOSTIC', 'TRANSFORMER', 'SUMMARY', 'HISTORICAL')),
    description     TEXT,

    filters         JSONB,

    template_name   VARCHAR(100) NOT NULL,
    format          VARCHAR(10)  NOT NULL DEFAULT 'PDF' CHECK (format IN ('PDF', 'XLSX', 'CSV')),

    file_name       VARCHAR(255),
    file_path       VARCHAR(500),
    file_size_bytes BIGINT,

    status          VARCHAR(20)  NOT NULL DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'PROCESSING', 'COMPLETED', 'FAILED')),
    generated_at    TIMESTAMP,
    expires_at      TIMESTAMP,
    created_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by      BIGINT       NOT NULL,

    CONSTRAINT fk_reports_created_by FOREIGN KEY (created_by) REFERENCES users.users_data (id)
);

CREATE INDEX idx_reports_type ON reports.reports (type);
CREATE INDEX idx_reports_status ON reports.reports (status);
CREATE INDEX idx_reports_created_by ON reports.reports (created_by);
CREATE INDEX idx_reports_created_at ON reports.reports (created_at);
CREATE INDEX idx_reports_expires_at ON reports.reports (expires_at);