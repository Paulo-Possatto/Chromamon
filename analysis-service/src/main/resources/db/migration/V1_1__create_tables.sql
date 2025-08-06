CREATE TABLE chromatography (
    chr_id SERIAL PRIMARY KEY,
    hydrogen NUMERIC(10, 2) NOT NULL CHECK (hydrogen >= 0),
    methane NUMERIC(10, 2) NOT NULL CHECK (methane >= 0),
    ethane NUMERIC(10, 2) NOT NULL CHECK (ethane >= 0),
    acetylene NUMERIC(10, 2) NOT NULL CHECK (acetylene >= 0),
    c_monoxide NUMERIC(10, 2) NOT NULL CHECK (c_monoxide >= 0),
    c_dioxide NUMERIC(10, 2) NOT NULL CHECK (c_dioxide >= 0),
    dt_created TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    dt_modified TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    user_created VARCHAR(6) not null,
    user_updated VARCHAR(6) not null
);

CREATE TABLE gas_extraction_methods(
    g_ext_met_id SERIAL PRIMARY KEY,
    g_ext_met_typo VARCHAR(40) NOT NULL,
    g_ext_met_literal VARCHAR(40) NOT NULL,
    dt_created TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    dt_modified TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    user_created VARCHAR(6) not null,
    user_updated VARCHAR(6) not null
);

CREATE TABLE observation (
    obs_id SERIAL PRIMARY KEY,
    sample_cond TEXT NOT NULL,
    g_ext_method INT REFERENCES gas_extraction_methods(g_ext_met_id),
    mod_used VARCHAR(40) NOT NULL,
    dt_created TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    dt_modified TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    user_created VARCHAR(6) not null,
    user_updated VARCHAR(6) not null
);

CREATE TABLE oil_types(
    oil_type_id SERIAL PRIMARY KEY,
    oil_type_typo VARCHAR(40) NOT NULL,
    oil_type_literal VARCHAR(40) NOT NULL,
    dt_created TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    dt_modified TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    user_created VARCHAR(6) not null,
    user_updated VARCHAR(6) not null
);

CREATE TABLE analyses (
    an_id SERIAL PRIMARY KEY,
    identifier VARCHAR(14) UNIQUE NOT NULL DEFAULT generate_unique_identifier(),
    trans_ser_num VARCHAR(40) NOT NULL,
    an_dt TIMESTAMPTZ NOT NULL,
    lab_an_dt TIMESTAMPTZ,
    chroma INT REFERENCES chromatography(chr_id),
    oil_type INT REFERENCES oil_types(oil_type_id),
    obs INT REFERENCES observation(obs_id),
    furfural_an NUMERIC(10, 2) NOT NULL CHECK (furfural_an >= 0),
    oil_hum NUMERIC(10, 2) NOT NULL CHECK (oil_hum >= 0 AND oil_hum <= 100),
    dt_created TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    dt_modified TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    user_created VARCHAR(6) not null,
    user_updated VARCHAR(6) not null
);