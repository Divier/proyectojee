--
--  clase_interes
-- 
INSERT INTO clase_interes (id_clase_interes, codigo, nombre, estado, descripcion, sigla)
VALUES (1, '1', 'Mora de Comparendos', 1, 'Intereses causados a comparendos en mora', null);
INSERT INTO clase_interes (id_clase_interes, codigo, nombre, estado, descripcion, sigla)
VALUES (2, '2', 'Mora de Financiaciones', 1, 'Intereses causados a una financiación en mora', null);
INSERT INTO clase_interes (id_clase_interes, codigo, nombre, estado, descripcion, sigla)
VALUES (3, '3', 'Financiaciones', 1, 'Interés por realización de la financiación', null);
-- 
--  periodo_interes
-- 
INSERT INTO periodo_interes (id_periodo_interes, codigo, nombre, estado, descripcion, sigla)
VALUES (1, '1', 'Diario', 1, null, null);
INSERT INTO periodo_interes (id_periodo_interes, codigo, nombre, estado, descripcion, sigla)
VALUES (2, '2', 'Mensual', 1, null, null);
INSERT INTO periodo_interes (id_periodo_interes, codigo, nombre, estado, descripcion, sigla)
VALUES (3, '3', 'Trimestral', 1, null, null);
INSERT INTO periodo_interes (id_periodo_interes, codigo, nombre, estado, descripcion, sigla)
VALUES (4, '4', 'Anual', 1, null, null);
-- 
--  interes
-- 
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (1, '2015-01-01', '2015-03-31', 28.82, 0.080055556, 1, 4);
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (2, '2015-01-01', '2015-03-31', 28.82, 0.080055556, 2, 4);
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (3, '2015-01-01', '2015-03-31', 28.82, 0.080055556, 3, 4);
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (4, '2015-04-01', '2015-06-30', 29.06, 0.080722222, 1, 4);
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (5, '2015-04-01', '2015-06-30', 29.06, 0.080722222, 2, 4);
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (6, '2015-04-01', '2015-06-30', 29.06, 0.080722222, 3, 4);
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (7, '2015-07-01', '2015-09-30', 28.89, 0.08025, 1, 4);
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (8, '2015-07-01', '2015-09-30', 28.89, 0.08025, 2, 4);
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (9, '2015-07-01', '2015-09-30', 28.89, 0.08025, 3, 4);
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (10,	'2015-10-01', '2015-12-31',	29, 0.080555556, 1, 4);
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (11, '2015-10-01', '2015-12-31',	29,	0.080555556, 2, 4);
INSERT INTO interes (id_interes, fecha_inicial, fecha_final, porcentaje_tasa_interes, porcentaje_interes_diario, id_clase_interes, id_periodo_interes)
VALUES (12, '2015-10-01', '2015-12-31',	29,	0.080555556, 3, 4);