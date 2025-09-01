CREATE TABLE analyses.analyses
(
    id                            BIGSERIAL PRIMARY KEY,
    transformer_id                BIGINT       NOT NULL,
    analysis_date                 DATE         NOT NULL,
    sample_date                   DATE         NOT NULL,
    laboratory                    VARCHAR(100) NOT NULL,
    method                        VARCHAR(50)  NOT NULL DEFAULT 'ASTM_D3612',
    sample_temperature_celsius    DECIMAL(5, 2),

    hydrogen_h2_ppm               DECIMAL(10, 3),
    methane_ch4_ppm               DECIMAL(10, 3),
    acetylene_c2h2_ppm            DECIMAL(10, 3),
    ethylene_c2h4_ppm             DECIMAL(10, 3),
    ethane_c2h6_ppm               DECIMAL(10, 3),
    carbon_monoxide_co_ppm        DECIMAL(10, 3),
    carbon_dioxide_co2_ppm        DECIMAL(10, 3),
    oxygen_o2_ppm                 DECIMAL(10, 3),
    nitrogen_n2_ppm               DECIMAL(10, 3),

    total_dissolved_gas_tdg_ppm   DECIMAL(10, 3),
    total_combustible_gas_tcg_ppm DECIMAL(10, 3),

    observations                  TEXT,
    sample_condition              VARCHAR(50)           DEFAULT 'NORMAL',

    status                        VARCHAR(20)  NOT NULL DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'COMPLETED', 'CANCELLED')),
    created_at                    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at                    TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    created_by                    BIGINT       NOT NULL,
    updated_by                    BIGINT,

    CONSTRAINT fk_analyses_transformer FOREIGN KEY (transformer_id) REFERENCES transformers.transformers (id) ON DELETE CASCADE,
    CONSTRAINT fk_analyses_created_by FOREIGN KEY (created_by) REFERENCES users.users_data (id),
    CONSTRAINT fk_analyses_updated_by FOREIGN KEY (updated_by) REFERENCES users.users_data (id),
    CONSTRAINT analyses_dates_check CHECK (analysis_date >= sample_date),
    CONSTRAINT analyses_gas_values_check CHECK (
        hydrogen_h2_ppm >= 0 AND methane_ch4_ppm >= 0 AND acetylene_c2h2_ppm >= 0 AND
        ethylene_c2h4_ppm >= 0 AND ethane_c2h6_ppm >= 0 AND carbon_monoxide_co_ppm >= 0 AND
        carbon_dioxide_co2_ppm >= 0 AND oxygen_o2_ppm >= 0 AND nitrogen_n2_ppm >= 0
        )
);

CREATE INDEX idx_analyses_transformer_id ON analyses.analyses (transformer_id);
CREATE INDEX idx_analyses_analysis_date ON analyses.analyses (analysis_date);
CREATE INDEX idx_analyses_sample_date ON analyses.analyses (sample_date);
CREATE INDEX idx_analyses_status ON analyses.analyses (status);
CREATE INDEX idx_analyses_laboratory ON analyses.analyses (laboratory);