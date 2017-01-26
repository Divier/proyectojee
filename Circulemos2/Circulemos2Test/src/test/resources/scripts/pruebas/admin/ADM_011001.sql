-- 
--  configuracion_descuento
-- 
INSERT INTO configuracion_descuento (ID_DESCUENTO, CODIGO_TIPO_DESCUENTO, DESCRIPCION, DIA_HABIL, INTERES_COMPARENDO, DIAS_HABIL_INICIO_INTERESES, FECHA_VIGENCIA_INICIO, FECHA_VIGENCIA_FIN, CODIGO_FECHA_ORIGEN, DIAS_DESDE_FECHA_IMPOSICION, LIMITE, CODIGO_ORGANISMO) VALUES ('-1', '1', 'UNIT_TEST_CAR_004_V1', '1', '1', '4', '2014-12-01', '2015-01-01', '1', '0', NULL, '11001');
INSERT INTO configuracion_descuento (ID_DESCUENTO, CODIGO_TIPO_DESCUENTO, DESCRIPCION, DIA_HABIL, INTERES_COMPARENDO, DIAS_HABIL_INICIO_INTERESES, FECHA_VIGENCIA_INICIO, FECHA_VIGENCIA_FIN, CODIGO_FECHA_ORIGEN, DIAS_DESDE_FECHA_IMPOSICION, LIMITE, CODIGO_ORGANISMO) VALUES ('-2', '1', 'UNIT_TEST_CAR_015_V1', '1', '1', '4', '2010-01-01', '2014-11-30', '1', '0', NULL, '11001');
-- 
--  descuento_notificacion
-- 
INSERT INTO descuento_notificacion (ID_DESCUENTO, ID_TIPO_NOTIFICACION) VALUES ('-1', '1');
INSERT INTO descuento_notificacion (ID_DESCUENTO, ID_TIPO_NOTIFICACION) VALUES ('-2', '1');
INSERT INTO descuento_notificacion (ID_DESCUENTO, ID_TIPO_NOTIFICACION) VALUES ('-2', '4');
INSERT INTO descuento_notificacion (ID_DESCUENTO, ID_TIPO_NOTIFICACION) VALUES ('-2', '3');
-- 
--  descuento_medio_imposicion
-- 
INSERT INTO descuento_medio_imposicion (ID_DESCUENTO, CODIGO_MEDIO_IMPOSICION) VALUES ('-1', '1');
INSERT INTO descuento_medio_imposicion (ID_DESCUENTO, CODIGO_MEDIO_IMPOSICION) VALUES ('-2', '1');
INSERT INTO descuento_medio_imposicion (ID_DESCUENTO, CODIGO_MEDIO_IMPOSICION) VALUES ('-2', '3');
-- 
--  detalle_descuento
-- 
INSERT INTO detalle_descuento (ID_DESCUENTO_DETALLE, ID_DESCUENTO, PORCENTAJE_DESCUENTO, DIAS_INICIO, DIAS_FIN, ID_CONCEPTO) VALUES ('-1', '-1', '50', '0', '5', '1');
INSERT INTO detalle_descuento (ID_DESCUENTO_DETALLE, ID_DESCUENTO, PORCENTAJE_DESCUENTO, DIAS_INICIO, DIAS_FIN, ID_CONCEPTO) VALUES ('-2', '-1', '25', '6', '15', '1');
INSERT INTO detalle_descuento (ID_DESCUENTO_DETALLE, ID_DESCUENTO, PORCENTAJE_DESCUENTO, DIAS_INICIO, DIAS_FIN, ID_CONCEPTO) VALUES ('-3', '-2', '50', '0', '5', '94');
INSERT INTO detalle_descuento (ID_DESCUENTO_DETALLE, ID_DESCUENTO, PORCENTAJE_DESCUENTO, DIAS_INICIO, DIAS_FIN, ID_CONCEPTO) VALUES ('-4', '-2', '25', '6', '15', '53');