CREATE TABLE diagnostic.diagnostics
(
    id                    BIGSERIAL PRIMARY KEY,
    analysis_id           BIGINT      NOT NULL,
    method                VARCHAR(50) NOT NULL,

    duval_triangle_result VARCHAR(50),
    duval_pentagon_result VARCHAR(50),
    ieee_c57104_result    VARCHAR(100),
    rogers_ratio_result   VARCHAR(100),
    iec_60599_result      VARCHAR(100),

    ratio_c2h2_c2h4       DECIMAL(10, 6),
    ratio_ch4_h2          DECIMAL(10, 6),
    ratio_c2h4_c2h6       DECIMAL(10, 6),
    ratio_c2h6_c2h2       DECIMAL(10, 6),
    ratio_co2_co          DECIMAL(10, 6),

    fault_type            VARCHAR(100),
    severity_level        VARCHAR(20) CHECK (severity_level IN ('LOW', 'MEDIUM', 'HIGH', 'CRITICAL')),
    confidence_level      DECIMAL(5, 2) CHECK (confidence_level BETWEEN 0 AND 100),
    recommendation        TEXT,

    diagnosis_date        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at            TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at            TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
    created_by            BIGINT      NOT NULL,

    CONSTRAINT fk_diagnostics_analysis FOREIGN KEY (analysis_id) REFERENCES analyses.analyses (id) ON DELETE CASCADE,
    CONSTRAINT fk_diagnostics_created_by FOREIGN KEY (created_by) REFERENCES users.users_data (id),
    CONSTRAINT uk_diagnostics_analysis UNIQUE (analysis_id)
);

CREATE INDEX idx_diagnostics_analysis_id ON diagnostic.diagnostics (analysis_id);
CREATE INDEX idx_diagnostics_method ON diagnostic.diagnostics (method);
CREATE INDEX idx_diagnostics_fault_type ON diagnostic.diagnostics (fault_type);
CREATE INDEX idx_diagnostics_severity_level ON diagnostic.diagnostics (severity_level);
CREATE INDEX idx_diagnostics_diagnosis_date ON diagnostic.diagnostics (diagnosis_date);