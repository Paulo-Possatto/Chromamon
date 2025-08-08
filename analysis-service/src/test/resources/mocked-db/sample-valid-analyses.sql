insert into oil_types(oil_type_typo, oil_type_literal, user_created, user_updated)
values ('MINERALOIL', 'Mineral Oil', 'AA00AA', 'AA00AA');
insert into oil_types(oil_type_typo, oil_type_literal, user_created, user_updated)
values ('VEGETALOIL', 'Vegetal Oil', 'AA00AA', 'AA00AA');
insert into oil_types(oil_type_typo, oil_type_literal, user_created, user_updated)
values ('SILICONOIL', 'Silicon Oil', 'AA00AA', 'AA00AA');

insert into gas_extraction_methods(g_ext_met_typo, g_ext_met_literal, user_created, user_updated)
values ('DIRECT', 'Direct', 'AA00AA', 'AA00AA');
insert into gas_extraction_methods(g_ext_met_typo, g_ext_met_literal, user_created, user_updated)
values ('MANUALPUMP', 'Manual Pump', 'AA00AA', 'AA00AA');
insert into gas_extraction_methods(g_ext_met_typo, g_ext_met_literal, user_created, user_updated)
values ('ELECTRICPUMP', 'Electric Pump', 'AA00AA', 'AA00AA');

insert into chromatography(hydrogen, methane, ethane, acetylene, c_monoxide, c_dioxide, user_created, user_updated)
values (0.15, 0.21, 0.09, 0.11, 4.45, 915.41, 'AA00AA', 'AA00AA');
insert into chromatography(hydrogen, methane, ethane, acetylene, c_monoxide, c_dioxide, user_created, user_updated)
values (0.22, 0.35, 0.12, 0.08, 3.75, 1020.10, 'AA00AA', 'AA00AA');
insert into chromatography(hydrogen, methane, ethane, acetylene, c_monoxide, c_dioxide, user_created, user_updated)
values (0.18, 0.28, 0.11, 0.15, 5.20, 980.75, 'AA00AA', 'AA00AA');

insert into observation(sample_cond, g_ext_method, mod_used, user_created, user_updated)
values ('Presence of particles', 1, 'GC Agilent 7890B', 'AA00AA', 'AA00AA');
insert into observation(sample_cond, g_ext_method, mod_used, user_created, user_updated)
values ('Clear with slight discoloration', 2, 'GC Shimadzu 2030', 'AA00AA', 'AA00AA');
insert into observation(sample_cond, g_ext_method, mod_used, user_created, user_updated)
values ('Normal appearance', 3, 'GC PerkinElmer Clarus 680', 'AA00AA', 'AA00AA');

insert into analyses(identifier, trans_ser_num, an_dt, lab_an_dt, chroma, oil_type, obs, furfural_an, oil_hum, user_created, user_updated)
values ('AN_0000000001', 'TRF-132kV-2023-ABC123', '2025-06-25 17:19:38+02', '2025-06-26 09:15:11+02', 1, 1, 1, 0.50, 15.00, 'AA00AA', 'AA00AA');
insert into analyses(identifier, trans_ser_num, an_dt, lab_an_dt, chroma, oil_type, obs, furfural_an, oil_hum, user_created, user_updated)
values ('AN_0000000002', 'TRF-220kV-2023-XYZ789', '2025-06-26 08:45:22+02', '2025-06-27 10:30:05+02', 2, 2, 2, 0.30, 12.30, 'AA00AA', 'AA00AA');
insert into analyses(identifier, trans_ser_num, an_dt, lab_an_dt, chroma, oil_type, obs, furfural_an, oil_hum, user_created, user_updated)
values ('AN_0000000003', 'TRF-66kV-2024-DEF456', '2025-06-27 14:30:10+02', '2025-06-28 08:45:33+02', 3, 3, 3, 0.70, 18.72, 'AA00AA', 'AA00AA');
