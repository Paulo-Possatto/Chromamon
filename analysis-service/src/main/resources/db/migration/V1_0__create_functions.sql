CREATE OR REPLACE FUNCTION update_modified_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.dt_modified = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION generate_unique_identifier()
RETURNS TEXT AS $$
DECLARE
    new_id TEXT;
    counter INT := 0;
BEGIN
    LOOP
        new_id := 'AN_' || LPAD(FLOOR(RANDOM() * 10000000000)::TEXT, 10, '0');

        IF NOT EXISTS (SELECT 1 FROM analyses WHERE identifier = new_id) THEN
            RETURN new_id;
        END IF;

        counter := counter + 1;

        IF counter > 1000 THEN
            RAISE EXCEPTION 'Was not possible to create an new identifier after 1000 attempts';
        END IF;
    END LOOP;
END;
$$ LANGUAGE plpgsql;