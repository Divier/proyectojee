-- 
--  tipo comparendo
-- 
INSERT INTO tipo_comparendo(id_tipo_comparendo, codigo, nombre, sigla, estado, descripcion) VALUES (3, '3', 'Papeleta', NULL, 1, NULL);
-- 
--  infraccion
-- 
INSERT INTO infraccion (ID_INFRACCION, ID_TIPO_INFRACCION, NUMERAL_INFRACCION, CODIGO_INFRACCION, CODIGO_ORGANISMO) VALUES ('-1', '1', '1', 'A1', '11001');
INSERT INTO infraccion (ID_INFRACCION, ID_TIPO_INFRACCION, NUMERAL_INFRACCION, CODIGO_INFRACCION, CODIGO_ORGANISMO) VALUES ('-2', '2', '1', 'B1', '11001');
INSERT INTO infraccion (ID_INFRACCION, ID_TIPO_INFRACCION, NUMERAL_INFRACCION, CODIGO_INFRACCION, CODIGO_ORGANISMO) VALUES ('-3', '3', '1', 'C1', '11001');
INSERT INTO infraccion (ID_INFRACCION, ID_TIPO_INFRACCION, NUMERAL_INFRACCION, CODIGO_INFRACCION, CODIGO_ORGANISMO) VALUES ('-4', '4', '1', 'D1', '11001');
-- 
--  configuracion_infraccion
-- 
INSERT INTO configuracion_infraccion(id_configuracion_infraccion, id_infraccion, descripcion, id_normatividad, articulo, fecha_articulo, requiere_placa, infractor_obligatorio, dias_genera_cartera, dia_habil_genera_cartera, id_entidad_agente, id_causal_infraccion, id_ordenamiento_pais, fecha_inicio_vigencia, fecha_fin_vigencia, genera_cartera, aplica_embriaguez, aplica_descuento) VALUES (-13, -1, 'NO DETENERSE ANTE UNA LUZ ROJA O AMARILLA DE SEMÁFORO, UNA SEÑAL DE PARE O UN SEMÁFORO INTERMITENT', 2, 'LEY 1383 DE 2010', '2010-03-16', 0, 0, 0, 0, 1, 1, 1, '2008-11-06', NULL, 0, 0, 0);
-- 
--  direccion
-- 
INSERT INTO direccion (ID_DIRECCION, CODIGO_TIPO_VIA_PRINCIPAL, NUMERO_VIA_PRINCIPAL, CODIGO_NOMBRE_VIA_PRINCIPAL, LETRA_VIA_PRINCIPAL, BIS_VIA_PRINCIPAL, LETRA_BIS_VIA_PRINCIPAL, CODIGO_CARDINALIDAD_VIA_PRINC, CODIGO_TIPO_VIA_SECUNDARIA, NUMERO_VIA_SECUNDARIA, CODIGO_NOMBRE_VIA_SECUNDARIA, LETRA_VIA_SECUNDARIA, BIS_VIA_SECUNDARIA, LETRA_BIS_VIA_SECUNDARIA, NUMERO_PLACA_DOMICILIARIA, CODIGO_CARDINALIDAD_VIA_SECUN, COMPLEMENTO, ID_MUNICIPIO, ID_LOCALIDAD, CODIGO_TIPO_UBICABILIDAD, BARRIO, CODIGO_TIPO_COORDENADA, LATITUD, LONGITUD) VALUES ('-8', '3', NULL, '17', NULL, NULL, NULL, NULL, '1', '31', NULL, NULL, NULL, NULL, '20', NULL, NULL, '149', NULL, '1', NULL, NULL, NULL, NULL);
INSERT INTO direccion (ID_DIRECCION, CODIGO_TIPO_VIA_PRINCIPAL, NUMERO_VIA_PRINCIPAL, CODIGO_NOMBRE_VIA_PRINCIPAL, LETRA_VIA_PRINCIPAL, BIS_VIA_PRINCIPAL, LETRA_BIS_VIA_PRINCIPAL, CODIGO_CARDINALIDAD_VIA_PRINC, CODIGO_TIPO_VIA_SECUNDARIA, NUMERO_VIA_SECUNDARIA, CODIGO_NOMBRE_VIA_SECUNDARIA, LETRA_VIA_SECUNDARIA, BIS_VIA_SECUNDARIA, LETRA_BIS_VIA_SECUNDARIA, NUMERO_PLACA_DOMICILIARIA, CODIGO_CARDINALIDAD_VIA_SECUN, COMPLEMENTO, ID_MUNICIPIO, ID_LOCALIDAD, CODIGO_TIPO_UBICABILIDAD, BARRIO, CODIGO_TIPO_COORDENADA, LATITUD, LONGITUD) VALUES ('-9', '3', NULL, '17', NULL, NULL, NULL, NULL, '1', '32', NULL, NULL, NULL, NULL, '21', NULL, NULL, '149', NULL, '1', NULL, NULL, NULL, NULL);
-- 
--  persona
-- 
INSERT INTO persona (ID_PERSONA, ID_TIPO_IDENTIFICACION, NUMERO_IDENTIFICACION, CODIGO_FUENTE_INFORMACION, CODIGO_ORGANISMO, FECHA_ULTIMA_ACTUALIZACION, ID_MUNICIPIO_EXPEDICION_DOCUM, FECHA_EXPEDICION_DOCUMENTO, FECHA_NACIMIENTO, FECHA_FALLECIMIENTO, NUMERO_TELEFONICO, ID_ESTADO_CIVIL, GENERO, APELLIDO1, APELLIDO2, NOMBRE1, NOMBRE2, CORREO_ELECTRONICO, NUMERO_CELULAR, NOMBRE_EMPRESA_LABORA, NOTIF_DIREC_EMPRE_LABOR, CARGO_EMPRESA_LABORA, TELEFONO_EMPRESA_LABORA, FAX_EMPRESA_LABORA, HUELLA_DIGITAL, RUTA_FOTO, FECHA_FOTO, CODIGO_TIPO_VIVIENDA, CODIGO_NIVEL_EDUCATIVO) VALUES ('-19', '1', '10000000018', '7', '11001', '2014-06-04', '150', '2014-06-04', '2014-06-04', NULL, '1000018', '3', 'M', 'apellido1_18', 'apellido2_18', 'nombre1_18', 'nombre2_18', 'nombre1_18@datatools.com.co', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', '2');
INSERT INTO persona (ID_PERSONA, ID_TIPO_IDENTIFICACION, NUMERO_IDENTIFICACION, CODIGO_FUENTE_INFORMACION, CODIGO_ORGANISMO, FECHA_ULTIMA_ACTUALIZACION, ID_MUNICIPIO_EXPEDICION_DOCUM, FECHA_EXPEDICION_DOCUMENTO, FECHA_NACIMIENTO, FECHA_FALLECIMIENTO, NUMERO_TELEFONICO, ID_ESTADO_CIVIL, GENERO, APELLIDO1, APELLIDO2, NOMBRE1, NOMBRE2, CORREO_ELECTRONICO, NUMERO_CELULAR, NOMBRE_EMPRESA_LABORA, NOTIF_DIREC_EMPRE_LABOR, CARGO_EMPRESA_LABORA, TELEFONO_EMPRESA_LABORA, FAX_EMPRESA_LABORA, HUELLA_DIGITAL, RUTA_FOTO, FECHA_FOTO, CODIGO_TIPO_VIVIENDA, CODIGO_NIVEL_EDUCATIVO) VALUES ('-21', '3', '10000000020', '9', '11001', '2014-06-04', '149', '2014-06-04', '2014-06-04', NULL, '1000020', '1', 'M', 'apellido1_20', 'apellido2_20', 'nombre1_20', 'nombre2_20', 'nombre1_20@datatools.com.co', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO persona(id_persona, id_tipo_identificacion, numero_identificacion, codigo_fuente_informacion, codigo_organismo, fecha_ultima_actualizacion, id_municipio_expedicion_docum, fecha_expedicion_documento, fecha_nacimiento, fecha_fallecimiento, numero_telefonico, id_estado_civil, genero, apellido1, apellido2, nombre1, nombre2, correo_electronico, numero_celular, nombre_empresa_labora, notif_direc_empre_labor, cargo_empresa_labora, telefono_empresa_labora, fax_empresa_labora, huella_digital, ruta_foto, fecha_foto, codigo_tipo_vivienda, codigo_nivel_educativo) VALUES (266, 1, '80831378', 12, 11001, '2015-08-13', NULL, NULL, NULL, NULL, '6999999', NULL, NULL, 'Casas', NULL, 'Divier', NULL, 'divier.casas@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
-- 
--  persona_juridica
-- 
INSERT INTO persona_juridica (ID_PERSONA_JURIDICA, DIGITO_VERIFICACION, NOMBRE_COMERCIAL, SIGLA, ID_TIPO_SOCIEDAD, ID_CLASE_ACTIVIDAD_ECONOMICA, ID_MUNICIPIO) VALUES ('-21', '7', 'CIA S.A.', 'HUM', NULL, '100', '149');
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
INSERT INTO orden_comparendo_nacional (CICOMPARENDO, NUMERO_COMPARENDO, CODIGO_ORGANISMO_TRANSITO) VALUES ('68', '11001000000000000054', '11001');
-- 
--  comparendo
-- 
INSERT INTO comparendo(cicomparendo, fecha_registro, fecha_modificacion, id_estado_comparendo, id_tipo_comparendo, codigo_origen, codigo_medio_imposicion, codigo_tipo_infractor, id_tipo_notificacion_compa, fecha_infraccion, hora_infraccion, id_direccion_comparendo, id_infraccion, retiene_licencia, existe_fuga_infractor, niega_prueba_alcoholemia, numero_prueba_alcoholemia, id_grado_alcoholemia, valor_grado_alcoholemia, fecha_prueba_alcoholemia, numero_reincidencia, fecha_notificacion, observaciones_infractor, id_usuario, id_ruta, fecha_genera_cartera, cartera_generada, es_polca) VALUES (68, '2015-11-06 18:10:38', '2015-11-06 18:10:38', 1, 3, NULL, 2, NULL, NULL, '2015-11-05', '00:00:00', -8, -1, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -1, NULL, NULL, 0, 0);
-- 
--  comparendo_vehiculo
-- 
INSERT INTO comparendo_vehiculo(id_comparendo_vehiculo, id_tipo_servicio, id_radio_accion, id_modalidad, placa_vehiculo, identificacion_vehiculo, numero_tarjeta_operacion, id_clase_vehiculo, id_nivel_servicio, codigo_organ_matri_vehic, numero_licencia_transito, id_marca_vehiculo, id_linea_vehiculo, id_color, numero_motor, modelo, peso_seco, peso_neto, codigo_tipo_trans_pasajero, codigo_organ_licen_trans) VALUES (68, 3, 3, 2, 'VEN285', NULL, '938271', 6, NULL, 11001, '123456', NULL, 8735, 231, '147852', '2012', NULL, NULL, NULL, NULL);
-- 
--  comparendo_agente
-- 
INSERT INTO comparendo_agente(id_comparendo_agente, id_tipo_identificacion, numero_identificacion, placa, apellido1, apellido2, nombre1, nombre2, id_unificacion_responsable, nombre_responsable, id_agente_transito, observaciones_agente) VALUES (68, 1, '80831378', '0009', 'Casas', NULL, 'Divier', NULL, 1, 'DATA TOOLS', 8, NULL);
-- 
--  comparendo_patio
-- 
INSERT INTO comparendo_patio(id_comparendo_patio, id_patio, numero_patio, nombre, numero_grua, placa_grua, consecutivo_asignado_grua, numero_informe, id_direccion) VALUES (68, NULL, 2, 'HJK', 21, 'BBG560', '0417', NULL, -9);
-- 
--  comparendo_persona
-- 
INSERT INTO comparendo_persona(id_comparendo_persona, cicomparendo, codigo_tipo_persona_comparendo, id_persona, id_tipo_identificacion, numero_identificacion, digito_verificacion_nit, id_licencia, codigo_organismo_licencia, numero_licencia, id_categoria_licencia_condu, fecha_expedicion_licen_condu, fecha_vencimiento_licen_condu, apellido1, apellido2, nombre1, nombre2, razon_social, edad, id_direccion, email, telefono_fijo, telefono_movil, fecha_incio, fecha_fin) VALUES (66, 68, 2, -19, 1, '1014060420', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ALVAREZ', NULL, 'luis', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-11-05', NULL);
INSERT INTO comparendo_persona(id_comparendo_persona, cicomparendo, codigo_tipo_persona_comparendo, id_persona, id_tipo_identificacion, numero_identificacion, digito_verificacion_nit, id_licencia, codigo_organismo_licencia, numero_licencia, id_categoria_licencia_condu, fecha_expedicion_licen_condu, fecha_vencimiento_licen_condu, apellido1, apellido2, nombre1, nombre2, razon_social, edad, id_direccion, email, telefono_fijo, telefono_movil, fecha_incio, fecha_fin) VALUES (67, 68, 8, -21, 1, '1014060420', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ALVAREZ', NULL, 'luis', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-11-05', NULL); 
