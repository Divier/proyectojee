-- 
--  infraccion
-- 
INSERT INTO infraccion (ID_INFRACCION, ID_TIPO_INFRACCION, NUMERAL_INFRACCION, CODIGO_INFRACCION) VALUES ('-1', '1', '1', 'A1');
-- 
--  configuracion_infraccion
-- 
INSERT INTO configuracion_infraccion(id_configuracion_infraccion, id_infraccion, descripcion, id_normatividad, articulo, fecha_articulo, requiere_placa, infractor_obligatorio, dias_genera_cartera, dia_habil_genera_cartera, id_entidad_agente, id_causal_infraccion, id_ordenamiento_pais, fecha_inicio_vigencia, fecha_fin_vigencia, genera_cartera, aplica_embriaguez, aplica_descuento) 
VALUES (-13, -1, 'NO DETENERSE ANTE UNA LUZ ROJA O AMARILLA DE SEMÁFORO, UNA SEÑAL DE PARE O UN SEMÁFORO INTERMITENT', 2, 'LEY 1383 DE 2010', '2010-03-16', 0, 0, 0, 0, 1, 1, 1, '2008-11-06', NULL, 0, 0, 0);
-- 
--  tarifa_infraccion
-- 
INSERT INTO tarifa_infraccion (id_tarifa_infraccion, id_infraccion, porcentaje_descuento, valor_infraccion,
valor_descuento,valor_comparendo,valor_cia,fecha_inicio_vigencia,fecha_final_vigencia,tarifa_confirmada,
fecha_creacion,fecha_confirmacion) 
VALUES ('-1','-1','0.00','85900.00','85900.00','85900.00','0.00','2015-01-01','2015-12-31','1','2014-12-19','2014-12-23'); 	
-- 
--  responsable_formulario
-- 
INSERT INTO responsable_formulario (ID_RESPONSABLE_FORMULARIO, CODIGO_ORGANISMO, ID_TIPO_RESPONSABLE, FECHA_INICIO_VINCULA,FECHA_FIN_VINCULA, CORREO_RESPONSABLE_FORMULARIO)
 VALUES ('-1', '11001', '1', '2015-10-01',null,'giovanni.velandia@datatools.com.co');
-- 
--  numeracion_formulario
-- 
INSERT INTO numeracion_formulario (ID_NUMERACION, ID_TIPO_FORMULARIO, FECHA_VIGENCIA_INICIAL, FECHA_VIGENCIA_FINAL, DIGITOS, ACTIVO, TRAMA) 
 VALUES ('-1', '1', '2015-10-01', '2015-12-31', '20', '1', '00000000000000000000'); 
-- 
--  detalle_numeracion
--  
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-1, -1, 1, '1', '1', 0, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-2, -1, 2, '1', '1', 0, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-3, -1, 3, '0', '0', 0, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-4, -1, 4, '0', '0', 0, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-5, -1, 5, '1', '1', 0, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-6, -1, 6, '0', '0', 0, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-7, -1, 7, '0', '0', 0, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-8, -1, 8, '0', '0', 0, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-9, -1, 9, '0', '9', 1, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-10, -1, 10, '0', '9', 1, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-11, -1, 11, '0', '9', 1, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-12, -1, 12, '0', '9', 1, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-13, -1, 13, '0', '9', 1, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-14, -1, 14, '0', '9', 1, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-15, -1, 15, '0', '9', 1, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-16, -1, 16, '0', '9', 1, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-17, -1, 17, '0', '9', 1, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-18, -1, 18, '0', '9', 1, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-19, -1, 19, '0', '9', 1, 1);
INSERT INTO detalle_numeracion(id_detalle_numeracion, id_numeracion, digito, caracter_inicio, caracter_fin, incremental, numerico) 
VALUES (-20, -1, 20, '0', '9', 1, 1);  
-- 
--  rango_formulario
-- 
INSERT INTO rango_formulario (ID_RANGO_FORMULARIO, CODIGO_ORGANISMO, ID_TIPO_FORMULARIO, ID_NUMERACION, ID_DOCUMENTO_FORMULARIO, NUMERO_INICIAL,
 NUMERO_FINAL, FECHA_AUTORIZACION, CANTIDAD_TOTAL, CANTIDAD_DISPONIBLE, ALERTA_STOCK) 
VALUES ('-1', '11001', '1', '-1', null,'11001000000000000000', '11001000000000001000', '2015-10-05', '1001', '1001', '0');
-- 
--  detalle_cambio_estado
-- 
INSERT INTO detalle_cambio_estado (ID_DETALLE_CAMBIO_ESTADO, ID_ESTADO_FORMULARIO, ID_CAUSAL_CAMBIO_ESTADO, ID_DOCUMENTO_FORMULARIO, ID_RESPONSABLE_FORMULARIO, ID_RANGO_FORMULARIO, NUMERO_INICIAL_RANGO, NUMERO_FINAL_RANGO, FECHA_MOVIMIENTO, FECHA_APLICACION_ESTADO, CANTIDAD_FORMULARIOS, FOLIO, TRAMA, OBSERVACIONES, ES_EVENTO) 
 VALUES ('-8', '2','1',null,'-1','-1','11001000000000000000','11001000000000000099','2015-10-29 10:22:05','2015-10-06 00:00:00','100',null, '00000000000000000000',null,true);
-- 
--  formulario
-- 
INSERT INTO formulario (ID_FORMULARIO, CODIGO_ORGANISMO, ID_RANGO_FORMULARIO, ID_TIPO_FORMULARIO,ID_ESTADO_FORMULARIO, ID_RESPONSABLE_FORMULARIO, ID_DETALLE_CAMBIO_ESTADO, NUMERO_FORMULARIO) 
VALUES ('-701', '11001', '-1', '1', '2', '-1', '-8', '11001000000000000000');
INSERT INTO formulario (ID_FORMULARIO, CODIGO_ORGANISMO, ID_RANGO_FORMULARIO, ID_TIPO_FORMULARIO,ID_ESTADO_FORMULARIO, ID_RESPONSABLE_FORMULARIO, ID_DETALLE_CAMBIO_ESTADO, NUMERO_FORMULARIO) 
VALUES ('-702', '11001', '-1', '1', '2', '-1', '-8', '11001000000000000001');
INSERT INTO formulario (ID_FORMULARIO, CODIGO_ORGANISMO, ID_RANGO_FORMULARIO, ID_TIPO_FORMULARIO,ID_ESTADO_FORMULARIO, ID_RESPONSABLE_FORMULARIO, ID_DETALLE_CAMBIO_ESTADO, NUMERO_FORMULARIO) 
VALUES ('-703', '11001', '-1', '1', '2', '-1', '-8', '11001000000000000002');
-- 
--  direccion
-- 
--INSERT INTO direccion (ID_DIRECCION, CODIGO_TIPO_VIA_PRINCIPAL, NUMERO_VIA_PRINCIPAL, CODIGO_NOMBRE_VIA_PRINCIPAL, LETRA_VIA_PRINCIPAL, BIS_VIA_PRINCIPAL, LETRA_BIS_VIA_PRINCIPAL, CODIGO_CARDINALIDAD_VIA_PRINC, CODIGO_TIPO_VIA_SECUNDARIA, NUMERO_VIA_SECUNDARIA, CODIGO_NOMBRE_VIA_SECUNDARIA, LETRA_VIA_SECUNDARIA, BIS_VIA_SECUNDARIA, LETRA_BIS_VIA_SECUNDARIA, NUMERO_PLACA_DOMICILIARIA, CODIGO_CARDINALIDAD_VIA_SECUN, COMPLEMENTO, ID_MUNICIPIO, ID_LOCALIDAD, CODIGO_TIPO_UBICABILIDAD, BARRIO, CODIGO_TIPO_COORDENADA, LATITUD, LONGITUD) VALUES ('-8', '3', NULL, '17', NULL, NULL, NULL, NULL, '1', '31', NULL, NULL, NULL, NULL, '20', NULL, NULL, '149', NULL, '1', NULL, NULL, NULL, NULL);
--INSERT INTO direccion (ID_DIRECCION, CODIGO_TIPO_VIA_PRINCIPAL, NUMERO_VIA_PRINCIPAL, CODIGO_NOMBRE_VIA_PRINCIPAL, LETRA_VIA_PRINCIPAL, BIS_VIA_PRINCIPAL, LETRA_BIS_VIA_PRINCIPAL, CODIGO_CARDINALIDAD_VIA_PRINC, CODIGO_TIPO_VIA_SECUNDARIA, NUMERO_VIA_SECUNDARIA, CODIGO_NOMBRE_VIA_SECUNDARIA, LETRA_VIA_SECUNDARIA, BIS_VIA_SECUNDARIA, LETRA_BIS_VIA_SECUNDARIA, NUMERO_PLACA_DOMICILIARIA, CODIGO_CARDINALIDAD_VIA_SECUN, COMPLEMENTO, ID_MUNICIPIO, ID_LOCALIDAD, CODIGO_TIPO_UBICABILIDAD, BARRIO, CODIGO_TIPO_COORDENADA, LATITUD, LONGITUD) VALUES ('-9', '3', NULL, '17', NULL, NULL, NULL, NULL, '1', '32', NULL, NULL, NULL, NULL, '21', NULL, NULL, '149', NULL, '1', NULL, NULL, NULL, NULL);
-- 
--  persona
-- 
INSERT INTO persona(id_persona, id_tipo_identificacion, numero_identificacion, codigo_fuente_informacion, codigo_organismo, fecha_ultima_actualizacion, id_municipio_expedicion_docum, fecha_expedicion_documento, fecha_nacimiento, fecha_fallecimiento, numero_telefonico, id_estado_civil, genero, apellido1, apellido2, nombre1, nombre2, correo_electronico, numero_celular, nombre_empresa_labora, notif_direc_empre_labor, cargo_empresa_labora, telefono_empresa_labora, fax_empresa_labora, huella_digital, ruta_foto, fecha_foto, codigo_tipo_vivienda, codigo_nivel_educativo) VALUES (266, 1, '80831378', 12, 11001, '2015-08-13', NULL, NULL, NULL, NULL, '6999999', NULL, NULL, 'Casas', NULL, 'Divier', NULL, 'divier.casas@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
-- 
--  agente
-- 
INSERT INTO agente(id_agente_transito, codigo_organismo, placa_agente_transito, fecha_inicio_vigencia, fecha_fin_vigencia, id_persona, id_entidad_agente_transito) VALUES (8, 11001, '0009', '2015-01-01', NULL, 266, 7);