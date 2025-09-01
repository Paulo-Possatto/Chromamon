CREATE
OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at
= CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$
language 'plpgsql';

CREATE TRIGGER update_users_updated_at
    BEFORE UPDATE
    ON users.users_data
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_user_permissions_updated_at
    BEFORE UPDATE
    ON users.user_permissions
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_transformers_updated_at
    BEFORE UPDATE
    ON transformers.transformers
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_analyses_updated_at
    BEFORE UPDATE
    ON analyses.analyses
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_diagnostics_updated_at
    BEFORE UPDATE
    ON diagnostic.diagnostics
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE OR REPLACE FUNCTION users.random_letter()
RETURNS TEXT AS $$
BEGIN
RETURN CHR(65 + FLOOR(RANDOM() * 26)::INTEGER);
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION users.random_number()
RETURNS TEXT AS $$
BEGIN
RETURN FLOOR(RANDOM() * 10)::TEXT;
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION users.generate_identification_code()
RETURNS TEXT AS $$
BEGIN
RETURN
    users.random_letter() ||
    users.random_letter() ||
    users.random_number() ||
    users.random_number() ||
    users.random_letter() ||
    users.random_letter();
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION users.generate_unique_identification_code()
RETURNS TEXT AS $$
DECLARE
    new_code TEXT;
    code_exists BOOLEAN;
BEGIN
    LOOP
    new_code := users.generate_identification_code();

    SELECT EXISTS (
        SELECT 1 FROM users.users_data
        WHERE id_code = new_code
    ) INTO code_exists;

    IF NOT code_exists THEN
        RETURN new_code;
    END IF;
    END LOOP;
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION users.generate_fields_on_insert()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.uuid IS NULL THEN
        NEW.uuid = gen_random_uuid();
END IF;

    IF NEW.id_code IS NULL THEN
        NEW.id_code = users.generate_unique_identification_code();
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_generate_fields
    BEFORE INSERT ON users.users_data
    FOR EACH ROW
    EXECUTE FUNCTION users.generate_fields_on_insert();