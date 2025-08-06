COMMENT ON TABLE chromatography IS 'Stores gas chromatography results with concentrations in ppm';
COMMENT ON TABLE observation IS 'Contains sample observations and extraction method details';
COMMENT ON TABLE analyses IS 'Main oil analysis table that contains all the information of the analyis';
COMMENT ON TABLE gas_extraction_methods IS 'Stores all the possibles methods used to extract the gas sample';
COMMENT ON TABLE oil_types IS 'Contains all the types of isolating oil';

COMMENT ON COLUMN gas_extraction_methods.g_ext_met_id IS 'Auto-incremented primary key for gas_extraction_methods results';
COMMENT ON COLUMN gas_extraction_methods.g_ext_met_typo IS 'Unprocesses data to verify raw information (used to search in the backend)';
COMMENT ON COLUMN gas_extraction_methods.g_ext_met_literal IS 'The processed data to be the useful information';
COMMENT ON COLUMN gas_extraction_methods.dt_created IS 'Timestamp when this record was created';
COMMENT ON COLUMN gas_extraction_methods.dt_modified IS 'Timestamp when this record was updated';
COMMENT ON COLUMN gas_extraction_methods.user_created IS 'The identification of the user who created the record';
COMMENT ON COLUMN gas_extraction_methods.user_updated IS 'The identification of the user who updated the record';

COMMENT ON COLUMN chromatography.chr_id IS 'Auto-incremented primary key for chromatography results';
COMMENT ON COLUMN chromatography.hydrogen IS 'Hydrogen gas concentration in ppm (parts per million)';
COMMENT ON COLUMN chromatography.methane IS 'Methane gas concentration in ppm (parts per million)';
COMMENT ON COLUMN chromatography.ethane IS 'Ethane gas concentration in ppm (parts per million)';
COMMENT ON COLUMN chromatography.acetylene IS 'Acetylene gas concentration in ppm (parts per million)';
COMMENT ON COLUMN chromatography.c_monoxide IS 'Carbon monoxide concentration in ppm (parts per million)';
COMMENT ON COLUMN chromatography.c_dioxide IS 'Carbon dioxide concentration in ppm (parts per million)';
COMMENT ON COLUMN chromatography.dt_created IS 'Timestamp when this record was created';
COMMENT ON COLUMN chromatography.dt_modified IS 'Timestamp when this record was updated';
COMMENT ON COLUMN chromatography.user_created IS 'The identification of the user who created the record';
COMMENT ON COLUMN chromatography.user_updated IS 'The identification of the user who updated the record';

COMMENT ON COLUMN oil_types.oil_type_id IS 'Auto-incremented primary key for oil type results';
COMMENT ON COLUMN oil_types.oil_type_typo IS 'Unprocesses data to verify raw information (used to search in the backend)';
COMMENT ON COLUMN oil_types.oil_type_literal IS 'The processed data to be the useful information';
COMMENT ON COLUMN oil_types.dt_created IS 'Timestamp when this record was created';
COMMENT ON COLUMN oil_types.dt_modified IS 'Timestamp when this record was updated';
COMMENT ON COLUMN oil_types.user_created IS 'The identification of the user who created the record';
COMMENT ON COLUMN oil_types.user_updated IS 'The identification of the user who updated the record';

COMMENT ON COLUMN observation.obs_id IS 'Auto-incremented primary key for observations';
COMMENT ON COLUMN observation.sample_cond IS 'Description of the sample condition';
COMMENT ON COLUMN observation.g_ext_method IS 'Foreign key referencing gas_extraction_methods data';
COMMENT ON COLUMN observation.mod_used IS 'Model of the equipment used for gas extraction';
COMMENT ON COLUMN observation.dt_created IS 'Timestamp when this record was created';
COMMENT ON COLUMN observation.dt_modified IS 'Timestamp when this record was updated';
COMMENT ON COLUMN observation.user_created IS 'The identification of the user who created the record';
COMMENT ON COLUMN observation.user_updated IS 'The identification of the user who updated the record';

COMMENT ON COLUMN analyses.an_id IS 'Auto-incremented primary key for analyses results';
COMMENT ON COLUMN analyses.identifier IS 'Identifier key in AN_XXXXXXXXXX format (AN_ + 10-digit sequence)';
COMMENT ON COLUMN analyses.trans_ser_num IS 'Transformer serial number (40 character maximum)';
COMMENT ON COLUMN analyses.an_dt IS 'Date and time when the analysis was performed (with timezone)';
COMMENT ON COLUMN analyses.lab_an_dt IS 'Date and time when analysis arrived at the laboratory (with timezone)';
COMMENT ON COLUMN analyses.chroma IS 'Foreign key referencing chromatography results';
COMMENT ON COLUMN analyses.oil_type IS 'Numeric code representing the oil type (application-mapped)';
COMMENT ON COLUMN analyses.obs IS 'Foreign key referencing observation data';
COMMENT ON COLUMN analyses.furfural_an IS 'Furfural analysis value in ppm (0.00 to 99999999.99)';
COMMENT ON COLUMN analyses.oil_hum IS 'Oil humidity percentage value (0.00 to 100.00)';
COMMENT ON COLUMN analyses.dt_created IS 'Timestamp when this record was created';
COMMENT ON COLUMN analyses.dt_modified IS 'Timestamp when this record was last modified';
COMMENT ON COLUMN analyses.user_created IS 'The identification of the user who created the record';
COMMENT ON COLUMN analyses.user_updated IS 'The identification of the user who updated the record';