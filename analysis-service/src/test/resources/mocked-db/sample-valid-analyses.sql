INSERT INTO observation(sample_cond, g_ext_method, mod_used)
VALUES ('Normal appearance', 3, 'GC PerkinElmer Clarus 680');

insert into chromatography(hydrogen, methane, ethane, acetylene, c_monoxide, c_dioxide)
values (0.18, 0.28, 0.11, 0.15, 5.20, 980.75);

insert into analyses(trans_ser_num, an_dt, lab_an_dt, chroma, oil_type, obs, furfural_an, oil_hum)
values ('TRF-66kV-2024-DEF456',
'2025-06-27 14:30:10 +2:00',
'2025-06-28 08:45:33 +2:00',
1, 3, 1, 0.7, 18.72);