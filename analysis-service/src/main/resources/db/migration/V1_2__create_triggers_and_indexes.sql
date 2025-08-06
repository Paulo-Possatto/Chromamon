CREATE TRIGGER update_analyses_modtime
BEFORE UPDATE ON analyses
FOR EACH ROW
EXECUTE FUNCTION update_modified_column();

CREATE TRIGGER update_chromatography_modtime
BEFORE UPDATE ON chromatography
FOR EACH ROW
EXECUTE FUNCTION update_modified_column();

CREATE TRIGGER update_observation_modtime
BEFORE UPDATE ON observation
FOR EACH ROW
EXECUTE FUNCTION update_modified_column();

CREATE TRIGGER update_gas_extraction_methods_modtime
BEFORE UPDATE ON gas_extraction_methods
FOR EACH ROW
EXECUTE FUNCTION update_modified_column();

CREATE TRIGGER update_oil_types_modtime
BEFORE UPDATE ON oil_types
FOR EACH ROW
EXECUTE FUNCTION update_modified_column();

CREATE INDEX idx_analyses_trans_ser_num ON analyses(trans_ser_num);
CREATE INDEX idx_analyses_an_dt ON analyses(an_dt);
CREATE INDEX idx_analyses_identifier ON analyses(identifier);