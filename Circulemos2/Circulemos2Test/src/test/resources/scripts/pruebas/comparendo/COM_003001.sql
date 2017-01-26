-- 
--  responsable_formulario
-- 
INSERT INTO responsable_formulario (ID_RESPONSABLE_FORMULARIO, CODIGO_ORGANISMO, ID_TIPO_RESPONSABLE, FECHA_INICIO_VINCULA,FECHA_FIN_VINCULA, CORREO_RESPONSABLE_FORMULARIO)
 VALUES ('-1', '11001', '1', '2015-10-01',null,'giovanni.velandia@datatools.com.co');
-- 
--  numeracion_formulario
-- 
INSERT INTO numeracion_formulario (ID_NUMERACION, ID_TIPO_FORMULARIO, FECHA_VIGENCIA_INICIAL, FECHA_VIGENCIA_FINAL, DIGITOS, ACTIVO, TRAMA) 
 VALUES ('-1', '1', '2015-10-01', '2016-12-31', '20', '1', '00000000000000000000'); 
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
VALUES ('-701', '11001', '-1', '1', '2', '-1', '-8', '11001000000000000085');
-- 
--  procesa_direccion
-- 
INSERT INTO `circulemos2_ut`.`procesa_direccion` (`id_procesa_direccion`, `codigo_tipo_via_principal`,
`numero_via_principal`, `codigo_nombre_via_principal`, `letra_via_principal`, `bis_via_principal`,
`letra_bis_via_principal`, `codigo_cardinalidad_via_princ`, `codigo_tipo_via_secundaria`, `numero_via_secundaria`,
`codigo_nombre_via_secundaria`, `letra_via_secundaria`, `bis_via_secundaria`, `letra_bis_via_secundaria`,
`numero_placa_domiciliaria`, `codigo_cardinalidad_via_secun`, `complemento`, `id_pais`, 
`id_departamento`, `id_municipio`, `id_localidad`, `codigo_tipo_ubicabilidad`,
`barrio`, `codigo_tipo_coordenada`, `latitud`, `longitud`)
VALUES
(39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '6-99',
47, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
-- 
--  Procesa comparendo
-- 
INSERT INTO `procesa_comparendo` (`id_procesamiento`,`cicomparendo`,`numero_comparendo`,`codigo_organismo_transito`,`id_tipo_comparendo`,`fecha_operacion`,
`codigo_origen`,`id_medio_imposicion`,`codigo_tipo_infractor`,`id_tipo_notificacion_compa`,`fecha_infraccion`,`hora_infraccion`,`id_procesa_direccion_compa`,
`codigo_infraccion`,`retiene_licencia`,`existe_fuga_infractor`,`niega_prueba_alcoholemia`,`numero_prueba_alcoholemia`,`grado_alcoholemia`,`valor_grado_alcoholemia`,
`fecha_prueba_alcoholemia`,`numero_reincidencia`,`fecha_notificacion`,`observaciones_infractor`,`inmovilizado`,`id_usuario`,`id_ruta`,`id_tipo_servicio`,
`id_radio_accion`,`id_modalidad`,`placa_vehiculo`,`identificacion_vehiculo`,`numero_tarjeta_operacion`,`id_clase_vehiculo`,`id_nivel_servicio`,
`codigo_organismo_matri_vehic`,`numero_licencia_transito`,`id_marca_vehiculo`,`id_linea_vehiculo`,`id_color`,`numero_motor`,`modelo`,`placa_agente`,
`peso_seco`,`peso_neto`,`codigo_tipo_trans_pasajero`,`id_agente_transito`,`observaciones_agente`,`id_tipo_identificacion_agente`,`numero_identificacion_agente`,
`id_unificacion_responsable`,`nombre_responsable`,`apellido1_agente`,`apellido2_agente`,`nombre1_agente`,`nombre2_agente`,`id_patio`,`numero_grua`,`placa_grua`,
`consecutivo_inmovilizacion`,`numero_patio`,`nombre_patio`,`numero_informe`,`id_procesa_direccion_patio`,`fecha_recepcion`,`fecha_correccion`,`es_polca`,
`codigo_organimo_licen_trans`) 
VALUES (36,NULL,'11001000000000000085',11001,1,'2015-12-03 15:56:14',NULL,1,NULL,NULL,'2015-12-03','07:00:00',39,'F',0,0,0,NULL,1,89,NULL,NULL,NULL,NULL,0,NULL,NULL,
4,NULL,NULL,'dds456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0009',NULL,NULL,NULL,8,'pruebas',1,'80831378',1,'DATA TOOLS','Casas',NULL,'Divier',NULL,
NULL,NULL,NULL,NULL,78,NULL,NULL,NULL,'2015-12-03 15:54:21',NULL,0,NULL);