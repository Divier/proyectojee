-- 
--  infraccion
-- 
INSERT INTO infraccion (ID_INFRACCION, ID_TIPO_INFRACCION, NUMERAL_INFRACCION, CODIGO_INFRACCION, id_tipo_comparendo) VALUES ('-1', '1', '1', 'A_1', '1');
-- 
--  configuracion_infraccion
-- 
INSERT INTO configuracion_infraccion(id_configuracion_infraccion, id_infraccion, descripcion, id_normatividad, articulo, fecha_articulo, requiere_placa, infractor_obligatorio, dias_genera_cartera, dia_habil_genera_cartera, id_entidad_agente, id_causal_infraccion, id_ordenamiento_pais, fecha_inicio_vigencia, fecha_fin_vigencia, genera_cartera, aplica_embriaguez, aplica_descuento, genera_resolucion_automatica, condiciones_especiales, aplica_impugnacion) VALUES (-12, -1, 'aaaaaaaaaaa', 3, 'lEY 2', '2011-11-10', 1, 1, 0, 0, 1, 1, 1, '2009-11-19', NULL, 0, 1, 0, 0, 0, 1);
INSERT INTO configuracion_infraccion(id_configuracion_infraccion, id_infraccion, descripcion, id_normatividad, articulo, fecha_articulo, requiere_placa, infractor_obligatorio, dias_genera_cartera, dia_habil_genera_cartera, id_entidad_agente, id_causal_infraccion, id_ordenamiento_pais, fecha_inicio_vigencia, fecha_fin_vigencia, genera_cartera, aplica_embriaguez, aplica_descuento, genera_resolucion_automatica, condiciones_especiales, aplica_impugnacion) VALUES (-13, 2, 'NO DETENERSE ANTE UNA LUZ ROJA O AMARILLA DE SEMÁFORO, UNA SEÑAL DE PARE O UN SEMÁFORO INTERMITENT', 2, 'LEY 1383 DE 2010', '2010-03-16', 0, 1, 0, 0, 1, 1, 1, '2008-11-06', NULL, 0, 1, 0, 0, 0, 1);
-- 
--  direccion
-- 
INSERT INTO direccion (ID_DIRECCION, CODIGO_TIPO_VIA_PRINCIPAL, NUMERO_VIA_PRINCIPAL, CODIGO_NOMBRE_VIA_PRINCIPAL, LETRA_VIA_PRINCIPAL, BIS_VIA_PRINCIPAL, LETRA_BIS_VIA_PRINCIPAL, CODIGO_CARDINALIDAD_VIA_PRINC, CODIGO_TIPO_VIA_SECUNDARIA, NUMERO_VIA_SECUNDARIA, CODIGO_NOMBRE_VIA_SECUNDARIA, LETRA_VIA_SECUNDARIA, BIS_VIA_SECUNDARIA, LETRA_BIS_VIA_SECUNDARIA, NUMERO_PLACA_DOMICILIARIA, CODIGO_CARDINALIDAD_VIA_SECUN, COMPLEMENTO, ID_MUNICIPIO, ID_LOCALIDAD, CODIGO_TIPO_UBICABILIDAD, BARRIO, CODIGO_TIPO_COORDENADA, LATITUD, LONGITUD) VALUES ('-8', '3', NULL, '17', NULL, NULL, NULL, NULL, '1', '31', NULL, NULL, NULL, NULL, '20', NULL, NULL, '149', NULL, '1', NULL, NULL, NULL, NULL);
-- 
--  persona
-- 
INSERT INTO persona(id_persona, id_tipo_identificacion, numero_identificacion, codigo_fuente_informacion, codigo_organismo, fecha_ultima_actualizacion) VALUES (266, 1, '80831378', 12, 11001, '2015-08-13');
INSERT INTO persona(id_persona, id_tipo_identificacion, numero_identificacion, codigo_fuente_informacion, codigo_organismo, fecha_ultima_actualizacion) VALUES (280, 1, '1012654987', 5, 11001, '2015-11-06');
INSERT INTO persona(id_persona, id_tipo_identificacion, numero_identificacion, codigo_fuente_informacion, codigo_organismo, fecha_ultima_actualizacion) VALUES (-102, 1, '6000002', 12, 11001, '2015-01-05');
-- 
--  persona_juridica
-- 
INSERT INTO persona_juridica (ID_PERSONA_JURIDICA, DIGITO_VERIFICACION, NOMBRE_COMERCIAL, SIGLA, ID_TIPO_SOCIEDAD, ID_CLASE_ACTIVIDAD_ECONOMICA, ID_MUNICIPIO) VALUES ('266', '7', 'CIA S.A.', 'HUM', NULL, '100', '149');
-- 
-- Dumping data for table responsable_formulario
--
INSERT INTO responsable_formulario(id_responsable_formulario, codigo_organismo, id_tipo_responsable, fecha_inicio_vincula, fecha_fin_vincula, correo_responsable_formulario) VALUES (1, 11001, 2, '2015-10-01', '2015-12-31', 'giovanni.velandia@datatools.com.co');
-- 
--  unificacion responsable
-- 
INSERT INTO unificacion_responsable(id_unificacion_responsable, id_persona, codigo_organismo, id_responsable_formulario) VALUES (1, -1, NULL, 1);
-- 
--  agente
-- 
INSERT INTO agente(id_agente_transito, codigo_organismo, placa_agente_transito, fecha_inicio_vigencia, fecha_fin_vigencia, id_persona, id_entidad_agente_transito) VALUES (8, 11001, '0009', '2015-01-01', NULL, 266, 7);
-- 
--  orden_comparendo_nacional
-- 
INSERT INTO orden_comparendo_nacional(cicomparendo, numero_comparendo, codigo_organismo_transito) VALUES (123, '11001000000000000018', 11001);
INSERT INTO orden_comparendo_nacional(cicomparendo, numero_comparendo, codigo_organismo_transito) VALUES (122, '11001000000000000025', 11001);
-- 
--  comparendo
-- 
INSERT INTO comparendo(cicomparendo, fecha_registro, fecha_modificacion, id_estado_comparendo, id_tipo_comparendo, codigo_origen, codigo_medio_imposicion, codigo_tipo_infractor, id_tipo_notificacion_compa, fecha_infraccion, hora_infraccion, id_direccion_comparendo, id_infraccion, retiene_licencia, existe_fuga_infractor, niega_prueba_alcoholemia, numero_prueba_alcoholemia, id_grado_alcoholemia, valor_grado_alcoholemia, fecha_prueba_alcoholemia, numero_reincidencia, fecha_notificacion, observaciones_infractor, id_usuario, id_ruta, fecha_genera_cartera, cartera_generada, es_polca, id_estado_comparendo_simit) VALUES (122, '2015-11-25 13:56:13', '2015-11-25 13:56:13', 2, 1, NULL, 1, NULL, NULL, '2015-11-24', '10:00:00', -8, 2, 0, 0, 0, NULL, 2, 40, NULL, NULL, NULL, NULL, -1, NULL, NULL, 0, 0, NULL);
INSERT INTO comparendo(cicomparendo, fecha_registro, fecha_modificacion, id_estado_comparendo, id_tipo_comparendo, codigo_origen, codigo_medio_imposicion, codigo_tipo_infractor, id_tipo_notificacion_compa, fecha_infraccion, hora_infraccion, id_direccion_comparendo, id_infraccion, retiene_licencia, existe_fuga_infractor, niega_prueba_alcoholemia, numero_prueba_alcoholemia, id_grado_alcoholemia, valor_grado_alcoholemia, fecha_prueba_alcoholemia, numero_reincidencia, fecha_notificacion, observaciones_infractor, id_usuario, id_ruta, fecha_genera_cartera, cartera_generada, es_polca, id_estado_comparendo_simit) VALUES (123, '2015-11-26 10:31:35', '2015-11-26 10:31:35', 1, 1, NULL, 1, NULL, NULL, '2015-11-26', '00:00:00', -8, 2, 0, 0, 0, NULL, 1, 20, NULL, NULL, NULL, NULL, -1, NULL, NULL, 0, 0, NULL);
-- 
--  comparendo_vehiculo
-- 
INSERT INTO comparendo_vehiculo(id_comparendo_vehiculo, id_tipo_servicio, id_radio_accion, id_modalidad, placa_vehiculo, identificacion_vehiculo, numero_tarjeta_operacion, id_clase_vehiculo, id_nivel_servicio, codigo_organismo_matri_vehic, numero_licencia_transito, id_marca_vehiculo, id_linea_vehiculo, id_color, numero_motor, modelo, peso_seco, peso_neto, codigo_tipo_transporte_pasaj, codigo_organismo_licen_trans) VALUES (123, 3, 3, 2, 'ven285', NULL, '938271', 6, NULL, 11001, '123456', NULL, 8735, 231, '147852', '2012', NULL, NULL, NULL, NULL);
-- 
--  comparendo_agente
-- 
INSERT INTO comparendo_agente(id_comparendo_agente, id_tipo_identificacion, numero_identificacion, placa, apellido1, apellido2, nombre1, nombre2, id_unificacion_responsable, nombre_responsable, id_agente_transito, observaciones_agente) VALUES (122, 1, '80831378', NULL, 'Casas', NULL, 'Divier', NULL, 1, 'DATA TOOLS', 8, NULL);
INSERT INTO comparendo_agente(id_comparendo_agente, id_tipo_identificacion, numero_identificacion, placa, apellido1, apellido2, nombre1, nombre2, id_unificacion_responsable, nombre_responsable, id_agente_transito, observaciones_agente) VALUES (123, 1, '80831378', '0009', 'Casas', NULL, 'Divier', NULL, 1, 'DATA TOOLS', 8, NULL);
-- 
--  comparendo_patio
-- 
INSERT INTO comparendo_patio(id_comparendo_patio, id_patio, numero_patio, nombre, numero_grua, placa_grua, consecutivo_asignado_grua, numero_informe, id_direccion) VALUES (122, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO comparendo_patio(id_comparendo_patio, id_patio, numero_patio, nombre, numero_grua, placa_grua, consecutivo_asignado_grua, numero_informe, id_direccion) VALUES (123, NULL, 165879, NULL, NULL, NULL, NULL, NULL, NULL);
-- 
--  comparendo_persona
-- 
INSERT INTO comparendo_persona(id_comparendo_persona, cicomparendo, codigo_tipo_persona_comparendo, id_persona, id_tipo_identificacion, numero_identificacion, digito_verificacion_nit, id_licencia, codigo_organismo_licencia, numero_licencia, id_categoria_licencia_condu, fecha_expedicion_licen_condu, fecha_vencimiento_licen_condu, apellido1, apellido2, nombre1, nombre2, razon_social, edad, id_direccion, email, telefono_fijo, telefono_movil, fecha_incio, fecha_fin) VALUES (94, 122, 2, 280, 1, '1012654987', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CALDERON', NULL, 'LEONARDO', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-11-24', NULL);
INSERT INTO comparendo_persona(id_comparendo_persona, cicomparendo, codigo_tipo_persona_comparendo, id_persona, id_tipo_identificacion, numero_identificacion, digito_verificacion_nit, id_licencia, codigo_organismo_licencia, numero_licencia, id_categoria_licencia_condu, fecha_expedicion_licen_condu, fecha_vencimiento_licen_condu, apellido1, apellido2, nombre1, nombre2, razon_social, edad, id_direccion, email, telefono_fijo, telefono_movil, fecha_incio, fecha_fin) VALUES (95, 122, 8, 280, 1, '1012654987', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CALDERON', NULL, 'LEONARDO', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-11-24', NULL);
INSERT INTO comparendo_persona(id_comparendo_persona, cicomparendo, codigo_tipo_persona_comparendo, id_persona, id_tipo_identificacion, numero_identificacion, digito_verificacion_nit, id_licencia, codigo_organismo_licencia, numero_licencia, id_categoria_licencia_condu, fecha_expedicion_licen_condu, fecha_vencimiento_licen_condu, apellido1, apellido2, nombre1, nombre2, razon_social, edad, id_direccion, email, telefono_fijo, telefono_movil, fecha_incio, fecha_fin) VALUES (96, 123, 2, 280, 1, '1012654987', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CALDERON', NULL, 'LEONARDO', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-11-26', NULL);
-- 
--  cartera
-- 
INSERT INTO cartera(id_cartera, origen, nombre, fecha_creacion, fecha_inicio, fecha_registro, fecha_actualizacion, id_deudor, codigo_tipo_obligacion, codigo_estado_obligacion) VALUES (31, 11001, '11001000000000000025', '2015-11-01', '2015-11-01', '2015-11-13', '2015-11-13', -102, 1, 1);
-- 
--  comparendo_cartera
-- 
INSERT INTO comparendo_cartera(id_comparendo_cartera, cicomparendo, id_cartera) VALUES (19, 122, 31);