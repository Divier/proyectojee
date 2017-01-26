SET FOREIGN_KEY_CHECKS=0;
-- 08-Mar-2016
-- SPRINT 12
-- Formularios
DROP TABLE IF EXISTS causal_cambio_estado CASCADE;
DROP TABLE IF EXISTS detalle_cambio_estado CASCADE;
DROP TABLE IF EXISTS detalle_numeracion CASCADE;
DROP TABLE IF EXISTS documento_formulario CASCADE;
DROP TABLE IF EXISTS estado_formulario CASCADE;
DROP TABLE IF EXISTS formulario CASCADE;
DROP TABLE IF EXISTS numeracion_formulario CASCADE;
DROP TABLE IF EXISTS rango_formulario CASCADE;
DROP TABLE IF EXISTS relacion_estados CASCADE;
DROP TABLE IF EXISTS responsable_formulario CASCADE;
DROP TABLE IF EXISTS seguimiento_formulario CASCADE;
DROP TABLE IF EXISTS stock_tipo_formulario CASCADE;
DROP TABLE IF EXISTS stock_tipo_responsable CASCADE;
DROP TABLE IF EXISTS tipo_formulario CASCADE;
DROP TABLE IF EXISTS tipo_responsable_formulario CASCADE;
DROP TABLE IF EXISTS unificacion_responsable CASCADE;
-- Mail sender
DROP TABLE IF EXISTS correo_envio CASCADE;
DROP TABLE IF EXISTS usuario_persona CASCADE;
DROP TABLE IF EXISTS tipo_comunicacion_organismo CASCADE;
DROP TABLE IF EXISTS configuracion_email CASCADE;
DROP TABLE IF EXISTS tipo_email CASCADE;
-- Dependencias
DROP TABLE IF EXISTS departamento CASCADE;
DROP TABLE IF EXISTS localidad CASCADE;
DROP TABLE IF EXISTS municipio CASCADE;
DROP TABLE IF EXISTS pais CASCADE;
DROP TABLE IF EXISTS organismo_transito CASCADE;

DROP TABLE IF EXISTS direccion CASCADE;
DROP TABLE IF EXISTS cardinalidad_direccion CASCADE;
DROP TABLE IF EXISTS nombre_via CASCADE;
DROP TABLE IF EXISTS tipo_coordenada CASCADE;
DROP TABLE IF EXISTS tipo_ubicabilidad CASCADE;
DROP TABLE IF EXISTS tipo_via CASCADE;

DROP TABLE IF EXISTS direccion_organismo CASCADE;
DROP TABLE IF EXISTS direccion_persona CASCADE;
DROP TABLE IF EXISTS parentesco_persona CASCADE;
DROP TABLE IF EXISTS persona_historico CASCADE;
DROP TABLE IF EXISTS tipo_parentesco_persona CASCADE;

DROP TABLE IF EXISTS persona_juridica CASCADE;
DROP TABLE IF EXISTS clase_actividad_economica CASCADE;
DROP TABLE IF EXISTS grupo_actividad_economica CASCADE;
DROP TABLE IF EXISTS division_actividad_economica CASCADE;
DROP TABLE IF EXISTS seccion_actividad_economica CASCADE;
DROP TABLE IF EXISTS tipo_sociedad CASCADE;

DROP TABLE IF EXISTS persona CASCADE;
DROP TABLE IF EXISTS nivel_educativo CASCADE;
DROP TABLE IF EXISTS tipo_fuente_informacion CASCADE;
DROP TABLE IF EXISTS tipo_identificacion_persona CASCADE;
DROP TABLE IF EXISTS tipo_vivienda CASCADE;
DROP TABLE IF EXISTS estado_civil CASCADE;
-- General de sistema
DROP TABLE IF EXISTS catalogo CASCADE;
DROP TABLE IF EXISTS modulo CASCADE;
DROP TABLE IF EXISTS parametro CASCADE;
DROP TABLE IF EXISTS parametro_organismo CASCADE;
DROP TABLE IF EXISTS proceso CASCADE;
DROP TABLE IF EXISTS tipo_variable CASCADE;

-- ------------------------------------------------------------------------------------
-- comparendos
DROP TABLE IF EXISTS tipo_infractor_confi_infra CASCADE;
DROP TABLE IF EXISTS tipo_entidad_agente_transito CASCADE;
DROP TABLE IF EXISTS causal_infraccion CASCADE;
DROP TABLE IF EXISTS tipo_sancion_confi_infra CASCADE;
DROP TABLE IF EXISTS tipo_sancion CASCADE;
DROP TABLE IF EXISTS normatividad CASCADE;
DROP TABLE IF EXISTS normatividad_organismo CASCADE;
DROP TABLE IF EXISTS ordenamiento_pais CASCADE;
DROP TABLE IF EXISTS configuracion_infraccion CASCADE;
DROP TABLE IF EXISTS tipo_infraccion CASCADE;
DROP TABLE IF EXISTS infraccion_tipo_respo_solid CASCADE;
DROP TABLE IF EXISTS tipo_responsable_solidario CASCADE;
DROP TABLE IF EXISTS comparendo_cartera CASCADE;
DROP TABLE IF EXISTS estado_comparendo CASCADE;
DROP TABLE IF EXISTS tipo_transporte_pasajero CASCADE;
DROP TABLE IF EXISTS tipo_comparendo CASCADE;
DROP TABLE IF EXISTS comparendo_patio CASCADE;
DROP TABLE IF EXISTS agente CASCADE;
DROP TABLE IF EXISTS comparendo_agente CASCADE;
DROP TABLE IF EXISTS comparendo_vehiculo CASCADE;
DROP TABLE IF EXISTS empresa_transporte CASCADE;
DROP TABLE IF EXISTS tipo_categ_licen_condu CASCADE;
DROP TABLE IF EXISTS tipo_persona_comparendo CASCADE;
DROP TABLE IF EXISTS comparendo_persona CASCADE;
DROP TABLE IF EXISTS orden_comparendo_nacional CASCADE;
DROP TABLE IF EXISTS procesa_evidencia CASCADE;
DROP TABLE IF EXISTS procesa_direccion CASCADE;
DROP TABLE IF EXISTS procesa_comparendo_persona CASCADE;
DROP TABLE IF EXISTS procesa_comparendo CASCADE;
DROP TABLE IF EXISTS grado_alcoholemia CASCADE;
DROP TABLE IF EXISTS tipo_evidencia CASCADE;
DROP TABLE IF EXISTS evidencia CASCADE;
DROP TABLE IF EXISTS tipo_infractor CASCADE;
DROP TABLE IF EXISTS nivel_servicio CASCADE;
DROP TABLE IF EXISTS radio_accion CASCADE;
DROP TABLE IF EXISTS medio_imposicion_comparendo CASCADE;
DROP TABLE IF EXISTS tipo_origen_comparendo CASCADE;
DROP TABLE IF EXISTS detalle_procesamiento CASCADE;
DROP TABLE IF EXISTS ruta CASCADE;
DROP TABLE IF EXISTS modalidad CASCADE;
DROP TABLE IF EXISTS clase_vehiculo CASCADE;
DROP TABLE IF EXISTS tipo_servicio CASCADE;
DROP TABLE IF EXISTS tipo_notificacion_comparendo CASCADE;
DROP TABLE IF EXISTS trazabilidad_comparendo CASCADE;
DROP TABLE IF EXISTS infraccion CASCADE;
DROP TABLE IF EXISTS comparendo CASCADE;
DROP TABLE IF EXISTS error_procesamiento CASCADE;

-- configuraciones
DROP TABLE IF EXISTS valor_configuracion CASCADE;
DROP TABLE IF EXISTS configuracion CASCADE;

-- dependencias
-- patios
DROP TABLE IF EXISTS patio CASCADE;
-- registro vehicular
DROP TABLE IF EXISTS estado_licencia CASCADE;
DROP TABLE IF EXISTS linea_vehiculo CASCADE;
DROP TABLE IF EXISTS marca_vehiculo CASCADE;
DROP TABLE IF EXISTS color CASCADE;
DROP TABLE IF EXISTS licencia_conduccion CASCADE;

DROP TABLE IF EXISTS tipo_propietario CASCADE;
DROP TABLE IF EXISTS empresa_vehiculo CASCADE;
DROP TABLE IF EXISTS historico_vehiculo CASCADE;
DROP TABLE IF EXISTS origen_registro_vehicular CASCADE;
DROP TABLE IF EXISTS propietario_vehiculo CASCADE;
DROP TABLE IF EXISTS seguimiento_estado_licencia CASCADE;
DROP TABLE IF EXISTS vehiculo CASCADE;
-- administracion
DROP TABLE IF EXISTS entidad CASCADE;
DROP TABLE IF EXISTS campo_entidad CASCADE;
DROP TABLE IF EXISTS actividad CASCADE;
DROP TABLE IF EXISTS dia_no_habil CASCADE;

-- ------------------------------------------------------------------------------------
-- cartera
DROP TABLE IF EXISTS estado_obligacion CASCADE;
DROP TABLE IF EXISTS tipo_saldo CASCADE;
DROP TABLE IF EXISTS tipo_obligacion CASCADE;
DROP TABLE IF EXISTS actividad_cartera CASCADE;
DROP TABLE IF EXISTS tipo_concepto_cartera CASCADE;
DROP TABLE IF EXISTS concepto_cartera CASCADE;

DROP TABLE IF EXISTS log_afectar_cartera CASCADE;
DROP TABLE IF EXISTS trazabilidad_cartera CASCADE;
DROP TABLE IF EXISTS saldo_cartera CASCADE;
DROP TABLE IF EXISTS cartera CASCADE;
DROP TABLE IF EXISTS movimientos_cartera CASCADE;

-- ------------------------------------------------------------------------------------
-- SPRINT 05
DROP TABLE IF EXISTS tipo_moneda CASCADE;
DROP TABLE IF EXISTS unidad_monetaria CASCADE;
DROP TABLE IF EXISTS tarifa_infraccion CASCADE;

-- ------------------------------------------------------------------------------------
-- SPRINT 06
-- ------------------------------------------------------------------------------------
-- SPRINT 07
DROP TABLE IF EXISTS estado_comparendo_simit CASCADE;
DROP TABLE IF EXISTS archivo_notificacion_simit CASCADE;
DROP TABLE IF EXISTS notificacion_simit CASCADE;
DROP TABLE IF EXISTS tipo_resultado_envio_simit CASCADE;
DROP TABLE IF EXISTS tipo_documento_envio_simit CASCADE;
DROP TABLE IF EXISTS valor_homologacion CASCADE;
DROP TABLE IF EXISTS tipo_homologacion CASCADE;
DROP TABLE IF EXISTS servicio_homologacion CASCADE;
DROP TABLE IF EXISTS interes CASCADE;
DROP TABLE IF EXISTS clase_interes CASCADE;
DROP TABLE IF EXISTS periodo_interes CASCADE;

DROP TABLE IF EXISTS tipo_consecutivo CASCADE;
DROP TABLE IF EXISTS consecutivo CASCADE;

-- ------------------------------------------------------------------------------------
-- SPRINT 08
DROP TABLE IF EXISTS tipo_documento_generado CASCADE;
DROP TABLE IF EXISTS tipo_fecha_origen CASCADE;
DROP TABLE IF EXISTS detalle_descuento CASCADE;
DROP TABLE IF EXISTS descuento_notificacion CASCADE;
DROP TABLE IF EXISTS descuento_medio_imposicion CASCADE;
DROP TABLE IF EXISTS tipo_descuento CASCADE;
DROP TABLE IF EXISTS configuracion_descuento CASCADE;

-- ------------------------------------------------------------------------------------
-- SPRINT 09
DROP TABLE IF EXISTS campo_rectifica_comparendo CASCADE;
DROP TABLE IF EXISTS rectifica_comparendo CASCADE;
DROP TABLE IF EXISTS notificacion_correo_comparendo CASCADE;
DROP TABLE IF EXISTS notificacion_correo CASCADE;
DROP TABLE IF EXISTS pago_comparendo CASCADE;
DROP TABLE IF EXISTS comparendo_proceso_contra CASCADE;
DROP TABLE IF EXISTS proceso_contravencional CASCADE;
DROP TABLE IF EXISTS tipo_proceso CASCADE;
DROP TABLE IF EXISTS estado_proceso CASCADE;
DROP TABLE IF EXISTS curso_pedagogico CASCADE;
DROP TABLE IF EXISTS estado_resolucion CASCADE;
DROP TABLE IF EXISTS comparendo_resolucion CASCADE;
DROP TABLE IF EXISTS resolucion CASCADE;
DROP TABLE IF EXISTS tipo_resolucion CASCADE;

-- ------------------------------------------------------------------------------------
-- SPRINT 10
DROP TABLE IF EXISTS mensaje_error_cargue_acuse CASCADE;
DROP TABLE IF EXISTS campo_archivo_acuse CASCADE;
DROP TABLE IF EXISTS error_cargue_acuse CASCADE;
DROP TABLE IF EXISTS comparendo_archivo_acuse CASCADE;
DROP TABLE IF EXISTS archivo_acuse_notificacion CASCADE;
DROP TABLE IF EXISTS notificacion_aviso_comparendo CASCADE;
DROP TABLE IF EXISTS notificacion_aviso CASCADE;

-- ------------------------------------------------------------------------------------
-- SPRINT 11
DROP TABLE IF EXISTS rectifica_evidencia CASCADE;

-- ------------------------------------------------------------------------------------
-- SPRINT 12
DROP TABLE IF EXISTS representante_legal CASCADE;

-- ------------------------------------------------------------------------
-- Formularios
CREATE TABLE documento_formulario
(
	id_documento_formulario BIGINT NOT NULL AUTO_INCREMENT,
	numero_documento VARCHAR(30),
	id_documento VARCHAR(255),
	PRIMARY KEY (id_documento_formulario)
);

CREATE TABLE causal_cambio_estado
(
	id_causal_cambio_estado INTEGER NOT NULL AUTO_INCREMENT,
	id_estado_formulario INTEGER NOT NULL,
	codigo VARCHAR(5),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_causal_cambio_estado),
	KEY (id_estado_formulario),
	CONSTRAINT CK_causa_cambi_estad_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE unificacion_responsable
(
	id_unificacion_responsable BIGINT NOT NULL AUTO_INCREMENT,
	id_persona BIGINT,
	codigo_organismo INTEGER,
	id_responsable_formulario BIGINT NOT NULL,
	PRIMARY KEY (id_unificacion_responsable),
	KEY (id_persona),
	KEY (codigo_organismo),
	CONSTRAINT CK_unifi_respo CHECK (id_persona IS NOT NULL OR codigo_organismo IS NOT NULL)
);

CREATE TABLE tipo_formulario
(
	id_tipo_formulario INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_formulario),
	CONSTRAINT CK_tipo_formu_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE tipo_responsable_formulario
(
	id_tipo_responsable INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	codigo VARCHAR(10),
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_responsable),
	CONSTRAINT CK_tipo_asign_respo_01 CHECK (estado =1 OR estado =0)
);

CREATE TABLE stock_tipo_formulario
(
	id_stock_tipo_formulario INTEGER NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	id_tipo_formulario INTEGER NOT NULL,
	porcentaje_maximo_consumo INTEGER NOT NULL,
	PRIMARY KEY (id_stock_tipo_formulario),
	UNIQUE UQ_stock_tipo_formu_01(id_tipo_formulario, codigo_organismo),
	KEY (id_tipo_formulario),
	KEY (codigo_organismo),
	CONSTRAINT CK_stock_tipo_formu_01 CHECK (porcentaje_maximo_consumo >= 0 AND porcentaje_maximo_consumo <= 100)
);

CREATE TABLE stock_tipo_responsable
(
	id_stock_tipo_responsable INTEGER NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	id_tipo_formulario INTEGER NOT NULL,
	id_tipo_responsable INTEGER NOT NULL,
	stock_minimo INTEGER NOT NULL,
	stock_maximo INTEGER NOT NULL,
	estado_stock TINYINT NOT NULL,
	PRIMARY KEY (id_stock_tipo_responsable),
	UNIQUE UQ_stock_tipo_respo_01(id_tipo_formulario, codigo_organismo, id_tipo_responsable),
	KEY (id_tipo_formulario),
	KEY (id_tipo_responsable),
	KEY (codigo_organismo),
	CONSTRAINT CK_stock_tipo_respo_01 CHECK (stock_minimo > 0),
	CONSTRAINT CK_stock_tipo_respo_02 CHECK (stock_maximo < stock_minimo),
	CONSTRAINT CK_stock_tipo_respo_03 CHECK (estado_stock = 0 OR estado_stock = 1)
);

CREATE TABLE seguimiento_formulario
(
	id_seguimiento_formulario BIGINT NOT NULL AUTO_INCREMENT,
	id_formulario BIGINT NOT NULL,
	id_detalle_cambio_estado BIGINT,
	id_estado_formulario INTEGER NOT NULL,
	id_responsable_formulario BIGINT,
	fecha_movimiento DATETIME NOT NULL,
	fecha_aplicacion_estado DATETIME NOT NULL,
	usuario_registro VARCHAR(60) NOT NULL,
	PRIMARY KEY (id_seguimiento_formulario),
	KEY (id_formulario),
	KEY (id_estado_formulario),
	KEY (id_responsable_formulario),
	KEY (id_detalle_cambio_estado)
);

CREATE TABLE responsable_formulario
(
	id_responsable_formulario BIGINT NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	id_tipo_responsable INTEGER,
	fecha_inicio_vincula DATE NOT NULL,
	fecha_fin_vincula DATE,
	correo_responsable_formulario VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_responsable_formulario),
	KEY (id_tipo_responsable),
	KEY (codigo_organismo)
);

CREATE TABLE relacion_estados
(
	id_relacion_estados BIGINT NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	id_estado_formulario_actual INTEGER NOT NULL,
	id_estado_formulario_siguiente INTEGER NOT NULL,
	id_tipo_formulario INTEGER NOT NULL,
	PRIMARY KEY (id_relacion_estados),
	UNIQUE KEY UQ_relac_estad_01(id_tipo_formulario, id_estado_formulario_actual, id_estado_formulario_siguiente, codigo_organismo),
	KEY (id_estado_formulario_actual),
	KEY (id_estado_formulario_siguiente),
	KEY (id_tipo_formulario),
	KEY (codigo_organismo)
);

CREATE TABLE rango_formulario
(
	id_rango_formulario BIGINT NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	id_tipo_formulario INTEGER NOT NULL,
	id_numeracion INTEGER NOT NULL,
	id_documento_formulario BIGINT,
	numero_inicial VARCHAR(50) NOT NULL,
	numero_final VARCHAR(50) NOT NULL,
	fecha_autorizacion DATE NOT NULL,
	cantidad_total INTEGER NOT NULL,
	cantidad_disponible INTEGER NOT NULL,
	alerta_stock TINYINT NOT NULL DEFAULT 0,
	PRIMARY KEY (id_rango_formulario),
	KEY (id_numeracion),
	KEY (id_tipo_formulario),
	KEY (id_documento_formulario),
	KEY (codigo_organismo),
	CONSTRAINT CK_rango_formu CHECK (alerta_stock = 0 OR alerta_stock = 1)
);

CREATE TABLE numeracion_formulario
(
	id_numeracion INTEGER NOT NULL AUTO_INCREMENT,
	id_tipo_formulario INTEGER NOT NULL,
	fecha_vigencia_inicial DATE NOT NULL,
	fecha_vigencia_final DATE NOT NULL,
	digitos INTEGER NOT NULL,
	activo TINYINT NOT NULL,
	trama VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_numeracion),
	KEY (id_tipo_formulario),
	CONSTRAINT CK_numer_formu_01 CHECK (activo = 0 OR activo = 1),
	CONSTRAINT CK_numer_formu_02 CHECK (digitos > 0 AND digitos <= 50)
);

CREATE TABLE formulario
(
	id_formulario BIGINT NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	id_rango_formulario BIGINT DEFAULT NULL,
	id_tipo_formulario INTEGER NOT NULL,
	id_estado_formulario INTEGER NOT NULL,
	id_responsable_formulario BIGINT,
	id_detalle_cambio_estado BIGINT NOT NULL,
	numero_formulario VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_formulario),
	UNIQUE KEY UQ_formu_01(numero_formulario, id_tipo_formulario, codigo_organismo),
	KEY (id_tipo_formulario),
	KEY (id_estado_formulario),
	KEY (id_detalle_cambio_estado),
	KEY (id_responsable_formulario),
	KEY (id_rango_formulario),
	KEY (codigo_organismo)
);

CREATE TABLE estado_formulario
(
	id_estado_formulario INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(5),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_estado_formulario),
	CONSTRAINT CK_estad_formu_01 CHECK (estado = 1 OR estado = 0)
);

CREATE TABLE detalle_numeracion
(
	id_detalle_numeracion BIGINT NOT NULL AUTO_INCREMENT,
	id_numeracion INTEGER NOT NULL,
	digito BIGINT NOT NULL,
	caracter_inicio VARCHAR(1) NOT NULL,
	caracter_fin VARCHAR(1) NOT NULL,
	incremental TINYINT NOT NULL,
	numerico TINYINT NOT NULL,
	PRIMARY KEY (id_detalle_numeracion),
	KEY (id_numeracion),
	CONSTRAINT CK_detal_numer_01 CHECK (numerico = 0 OR numerico = 1),
	CONSTRAINT CK_detal_numer_02 CHECK (incremental = 0 OR incremental = 1)
);

CREATE TABLE detalle_cambio_estado
(
	id_detalle_cambio_estado BIGINT NOT NULL AUTO_INCREMENT,
	id_estado_formulario INTEGER NOT NULL,
	id_causal_cambio_estado INTEGER NOT NULL,
	id_documento_formulario BIGINT,
	id_responsable_formulario BIGINT NOT NULL,
	id_rango_formulario BIGINT NOT NULL,
	numero_inicial_rango VARCHAR(50),
	numero_final_rango VARCHAR(50),
	fecha_movimiento DATETIME NOT NULL,
	fecha_aplicacion_estado DATETIME NOT NULL,
	cantidad_formularios INTEGER,
	folio VARCHAR(20),
	trama VARCHAR(50) NOT NULL,
	observaciones VARCHAR(150),
	es_evento TINYINT NOT NULL,
	PRIMARY KEY (id_detalle_cambio_estado),
	KEY (id_estado_formulario),
	KEY (id_causal_cambio_estado),
	KEY (id_documento_formulario),
	KEY (id_responsable_formulario),
	KEY (id_rango_formulario),
	CONSTRAINT CK_detal_cambi_estad_01 CHECK (es_evento=0 OR es_evento=1)
);

-- ------------------------------------------------------------------------
-- Mail sender
-- ------------------------------------------------------------------------
CREATE TABLE correo_envio
(
	id_correo_envio INTEGER NOT NULL AUTO_INCREMENT,
	correo_envio VARCHAR(50) NOT NULL,
	id_configuracion INTEGER NOT NULL,
	codigo_organismo INTEGER NOT NULL,
	PRIMARY KEY (id_correo_envio),
	UNIQUE KEY UQ_correo_envio_01(correo_envio, id_configuracion),
	KEY (codigo_organismo),
	KEY (id_configuracion)
);

CREATE TABLE usuario_persona
(
	id_usuario INTEGER NOT NULL AUTO_INCREMENT,
	id_persona BIGINT NOT NULL,
	login VARCHAR(60) NOT NULL,
	PRIMARY KEY (id_usuario),
	KEY (id_persona)
);

CREATE TABLE tipo_comunicacion_organismo
(
	codigo_tipo_email INTEGER NOT NULL,
	codigo_organismo INTEGER NOT NULL,
	PRIMARY KEY (codigo_tipo_email, codigo_organismo),
	KEY (codigo_tipo_email),
	KEY (codigo_organismo)
);

CREATE TABLE configuracion_email
(
	id_configuracion INTEGER NOT NULL AUTO_INCREMENT,
	codigo_tipo_email INTEGER NOT NULL,
	codigo_organismo INTEGER NOT NULL,
	estado TINYINT NOT NULL DEFAULT 1,
	id_usuario INTEGER NOT NULL,
	fecha_cambio DATETIME NOT NULL,
	asunto_email VARCHAR(200) NOT NULL,
	cuerpo_email VARCHAR(1000),
	pie_email VARCHAR(1000),
	PRIMARY KEY (id_configuracion),
	UNIQUE KEY UQ_confi_email_01(codigo_tipo_email, codigo_organismo),
	KEY (id_usuario),
	KEY (codigo_tipo_email),
	KEY (codigo_organismo),
	CONSTRAINT CK_confi_email CHECK (estado = 1 or estado = 0)
);

CREATE TABLE tipo_email
(
	codigo_tipo_email INTEGER NOT NULL,
	nombre_tipo_email VARCHAR(50) NOT NULL,
	variables TEXT,
	PRIMARY KEY (codigo_tipo_email),
	UNIQUE KEY UQ_tipo_comun_email_01(nombre_tipo_email)
);

-- ------------------------------------------------------------------------
-- Dependencias
-- ------------------------------------------------------------------------
CREATE TABLE pais
(
	id_pais INTEGER NOT NULL AUTO_INCREMENT,
	codigo_pais VARCHAR(3) NOT NULL,
	nombre_pais VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_pais)
);

CREATE TABLE departamento
(
	id_departamento INTEGER NOT NULL AUTO_INCREMENT,
	codigo_departamento VARCHAR(10) NOT NULL,
	nombre_departamento VARCHAR(100) NOT NULL,
	id_pais INTEGER NOT NULL,
	PRIMARY KEY (id_departamento),
	UNIQUE KEY UQ_depar_01(codigo_departamento, id_pais),
	KEY (id_pais)
);

CREATE TABLE municipio
(
	id_municipio INTEGER NOT NULL AUTO_INCREMENT,
	codigo_municipio VARCHAR(10) NOT NULL,
	id_departamento INTEGER NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	PRIMARY KEY (id_municipio),
	UNIQUE KEY UQ_munic_01(codigo_municipio, id_departamento),
	KEY (id_departamento)
);

CREATE TABLE localidad
(
	id_localidad INTEGER NOT NULL AUTO_INCREMENT,
	codigo_localidad VARCHAR(10) NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	id_municipio INTEGER NOT NULL,
	PRIMARY KEY (id_localidad),
	KEY (id_municipio)
);

CREATE TABLE organismo_transito
(
	codigo_organismo INTEGER NOT NULL,
	id_departamento INTEGER,
	id_municipio INTEGER,
	nit VARCHAR(16),
	nombre_organismo VARCHAR(100) NOT NULL,
	correo_electronico VARCHAR(50),
	codigo_runt VARCHAR(16),
	codigo_ministerio VARCHAR(16),
	activo TINYINT NOT NULL DEFAULT 1,
	codigo_organismo_padre INTEGER,
	PRIMARY KEY (codigo_organismo),
	KEY (id_departamento),
	KEY (id_municipio),
	KEY (codigo_organismo_padre),
	CONSTRAINT CK_organ_trans_01 CHECK (activo = 0 OR activo = 1)
);

CREATE TABLE cardinalidad_direccion
(
	codigo_cardinalidad INTEGER NOT NULL,
	nombre_cardinalidad VARCHAR(10) NOT NULL,
	abreviatura_cardinalidad CHAR(2) NOT NULL,
	id_pais INTEGER NOT NULL,
	PRIMARY KEY (codigo_cardinalidad),
	UNIQUE KEY UQ_cardi_direc_01(id_pais, nombre_cardinalidad),
	KEY (id_pais)
);

CREATE TABLE nombre_via
(
	codigo_nombre_via INTEGER NOT NULL,
	codigo_tipo_via INTEGER NOT NULL,
	id_municipio INTEGER NOT NULL,
	nombre_via VARCHAR(60) NOT NULL,
	PRIMARY KEY (codigo_nombre_via),
	UNIQUE KEY UQ_nombr_via_01(nombre_via, id_municipio),
	KEY (id_municipio),
	KEY (codigo_tipo_via)
);

CREATE TABLE tipo_coordenada
(
	codigo_tipo_coordenada INTEGER NOT NULL,
	nombre_tipo_coordenada VARCHAR(50) NOT NULL,
	PRIMARY KEY (codigo_tipo_coordenada)
);

CREATE TABLE tipo_ubicabilidad
(
	codigo_tipo_ubicabilidad INTEGER NOT NULL,
	nombre VARCHAR(70) NOT NULL,
	PRIMARY KEY (codigo_tipo_ubicabilidad)
);

CREATE TABLE tipo_via
(
	codigo_tipo_via INTEGER NOT NULL,
	nombre_tipo_via VARCHAR(40) NOT NULL,
	id_pais INTEGER NOT NULL,
	PRIMARY KEY (codigo_tipo_via),
	UNIQUE KEY UQ_tipo_via_01(nombre_tipo_via, id_pais),
	KEY (id_pais)
);

CREATE TABLE direccion
(
	id_direccion BIGINT NOT NULL AUTO_INCREMENT,
	codigo_tipo_via_principal INTEGER NOT NULL,
	numero_via_principal INTEGER,
	codigo_nombre_via_principal INTEGER,
	letra_via_principal CHAR(1),
	bis_via_principal VARCHAR(3),
	letra_bis_via_principal CHAR(1),
	codigo_cardinalidad_via_princ INTEGER,
	codigo_tipo_via_secundaria INTEGER,
	numero_via_secundaria INTEGER,
	codigo_nombre_via_secundaria INTEGER,
	letra_via_secundaria CHAR(1),
	bis_via_secundaria VARCHAR(3),
	letra_bis_via_secundaria CHAR(1),
	numero_placa_domiciliaria INTEGER,
	codigo_cardinalidad_via_secun INTEGER,
	complemento VARCHAR(150),
	id_pais INTEGER,
	id_departamento INTEGER,
	id_municipio INTEGER,
	id_localidad INTEGER,
	codigo_tipo_ubicabilidad INTEGER,
	barrio VARCHAR(150),
	codigo_tipo_coordenada INTEGER,
	latitud DOUBLE,
	longitud DOUBLE,
	PRIMARY KEY (id_direccion),
	KEY (codigo_cardinalidad_via_princ),
	KEY (codigo_cardinalidad_via_secun),
	KEY (id_localidad),
	KEY (id_municipio),
	KEY (codigo_nombre_via_principal),
	KEY (codigo_nombre_via_secundaria),
	KEY (codigo_tipo_via_principal),
	KEY (codigo_tipo_via_secundaria),
	KEY (codigo_tipo_coordenada),
	KEY (codigo_tipo_ubicabilidad)
);

CREATE TABLE nivel_educativo
(
	codigo_nivel_educativo INTEGER NOT NULL,
	id_pais INTEGER,
	nombre_nivel_educativo VARCHAR(150),
	PRIMARY KEY (codigo_nivel_educativo),
	KEY (id_pais)
);

CREATE TABLE tipo_fuente_informacion
(
	codigo_fuente_informacion INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	codigo VARCHAR(10),
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (codigo_fuente_informacion),
	UNIQUE KEY UQ_tipo_fuent_infor_01(codigo),
	CONSTRAINT CK_tipo_fuent_infor_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE tipo_identificacion_persona
(
	id_tipo_identificacion INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10) NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150) NOT NULL,
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_tipo_identificacion),
	UNIQUE KEY UQ_tipo_ident_perso_01(codigo),
	CONSTRAINT CK_tipo_ident_perso_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE tipo_vivienda
(
	codigo_tipo_vivienda INTEGER NOT NULL,
	nombre_tipo_vivienda VARCHAR(150),
	id_pais INTEGER,
	PRIMARY KEY (codigo_tipo_vivienda),
	KEY (id_pais)
);

CREATE TABLE persona
(
	id_persona BIGINT NOT NULL AUTO_INCREMENT,
	id_tipo_identificacion INTEGER NOT NULL,
	numero_identificacion VARCHAR(20) NOT NULL,
	codigo_fuente_informacion INTEGER NOT NULL,
	codigo_organismo INTEGER,
	fecha_ultima_actualizacion DATE NOT NULL,
	id_municipio_expedicion_docum INTEGER,
	fecha_expedicion_documento DATE,
	fecha_nacimiento DATE,
	fecha_fallecimiento DATE,
	numero_telefonico VARCHAR(20),
	id_estado_civil INTEGER,
	genero CHAR(1),
	apellido1 VARCHAR(30),
	apellido2 VARCHAR(30),
	nombre1 VARCHAR(30),
	nombre2 VARCHAR(30),
	correo_electronico VARCHAR(255),
	numero_celular VARCHAR(20),
	nombre_empresa_labora VARCHAR(60),
	notifica_direccion_empre_labor TINYINT,
	cargo_empresa_labora VARCHAR(30),
	telefono_empresa_labora VARCHAR(30),
	fax_empresa_labora VARCHAR(20),
	huella_digital BLOB,
	ruta_foto VARCHAR(255),
	fecha_foto DATE,
	codigo_tipo_vivienda INTEGER,
	codigo_nivel_educativo INTEGER,
	PRIMARY KEY (id_persona),
	UNIQUE KEY UQ_perso_01(codigo_organismo, id_tipo_identificacion, numero_identificacion),
	KEY (codigo_organismo),
	KEY (id_estado_civil),
	KEY (codigo_nivel_educativo),
	KEY (codigo_tipo_vivienda),
	KEY (id_municipio_expedicion_docum),
	KEY (codigo_fuente_informacion),
	KEY (id_tipo_identificacion),
	CONSTRAINT CK_perso_01 CHECK (notif_direc_empre_labor = 0 OR notif_direc_empre_labor = 1)
);

CREATE TABLE clase_actividad_economica
(
	id_clase_actividad_economica INTEGER NOT NULL AUTO_INCREMENT,
	id_grupo_actividad_economica INTEGER NOT NULL,
	codigo_clase_actividad_econo VARCHAR(10) NOT NULL,
	nombre_clase_actividad_econo VARCHAR(250) NOT NULL,
	PRIMARY KEY (id_clase_actividad_economica),
	UNIQUE KEY UQ_clase_activ_econo_01(codigo_clase_actividad_econo, id_grupo_actividad_economica),
	KEY (id_grupo_actividad_economica)
);

CREATE TABLE division_actividad_economica
(
	id_division_actividad_econo INTEGER NOT NULL AUTO_INCREMENT,
	id_seccion_actividad_econo INTEGER NOT NULL,
	codigo_division_activ_econo VARCHAR(5) NOT NULL,
	nombre_division_activ_econo VARCHAR(250) NOT NULL,
	PRIMARY KEY (id_division_actividad_econo),
	UNIQUE KEY UQ_divis_activ_01(codigo_division_activ_econo, id_seccion_actividad_econo),
	KEY (id_seccion_actividad_econo)
);

CREATE TABLE grupo_actividad_economica
(
	id_grupo_actividad_economica INTEGER NOT NULL AUTO_INCREMENT,
	id_division_actividad_econo INTEGER NOT NULL,
	codigo_grupo_actividad_econo VARCHAR(7) NOT NULL,
	nombre_grupo_actividad_econo VARCHAR(250) NOT NULL,
	PRIMARY KEY (id_grupo_actividad_economica),
	UNIQUE KEY UQ_grupo_activ_01(id_division_actividad_econo, codigo_grupo_actividad_econo),
	KEY (id_division_actividad_econo)
);

CREATE TABLE persona_juridica
(
	id_persona_juridica BIGINT NOT NULL,
	codigo_empresa VARCHAR(20),
	digito_verificacion SMALLINT,
	nombre_comercial VARCHAR(70) NOT NULL,
	sigla VARCHAR(10),
	id_tipo_sociedad INTEGER,
	id_clase_actividad_economica INTEGER,
	id_municipio INTEGER,
	PRIMARY KEY (id_persona_juridica),
	UNIQUE KEY UQ_perso_jurid_01(codigo_empresa),
	KEY (id_tipo_sociedad),
	KEY (id_clase_actividad_economica),
	KEY (id_municipio)
);

CREATE TABLE seccion_actividad_economica
(
	id_seccion_actividad_econo INTEGER NOT NULL AUTO_INCREMENT,
	id_pais INTEGER NOT NULL,
	codigo_seccion_activ_econo VARCHAR(3) NOT NULL,
	nombre_seccion_activ_econo VARCHAR(250) NOT NULL,
	PRIMARY KEY (id_seccion_actividad_econo),
	UNIQUE KEY UQ_secci_activ_econo_01(id_pais, codigo_seccion_activ_econo),
	KEY (id_pais)
);

CREATE TABLE tipo_sociedad
(
	id_tipo_sociedad INTEGER NOT NULL AUTO_INCREMENT,
	id_pais INTEGER NOT NULL,
	codigo_tipo_sociedad INTEGER NOT NULL,
	nombre VARCHAR(60) NOT NULL,
	abreviatura VARCHAR(10),
	PRIMARY KEY (id_tipo_sociedad),
	UNIQUE KEY UQ_tipo_socie_01(id_pais, codigo_tipo_sociedad),
	KEY (id_pais)
);

CREATE TABLE direccion_organismo
(
	id_direccion_organismo BIGINT NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	id_direccion BIGINT NOT NULL,
	fecha_inicio DATE NOT NULL,
	fecha_fin DATE,
	PRIMARY KEY (id_direccion_organismo),
	KEY (codigo_organismo),
	KEY (id_direccion)
);

CREATE TABLE direccion_persona
(
	id_direccion_persona BIGINT NOT NULL AUTO_INCREMENT,
	id_direccion BIGINT NOT NULL,
	id_persona BIGINT NOT NULL,
	codigo_fuente_informacion INTEGER NOT NULL,
	fecha_inicio DATE,
	fecha_fin DATE,
	PRIMARY KEY (id_direccion_persona),
	KEY (codigo_fuente_informacion),
	KEY (id_persona),
	KEY (id_direccion)
);

CREATE TABLE tipo_parentesco_persona
(
	id_tipo_parentesco_persona INTEGER NOT NULL,
	nombre_parentesco_persona VARCHAR(50),
	PRIMARY KEY (id_tipo_parentesco_persona)
);

CREATE TABLE parentesco_persona
(
	id_parentesco_persona INTEGER NOT NULL AUTO_INCREMENT,
	id_persona_1 BIGINT NOT NULL,
	id_persona_2 BIGINT NOT NULL,
	id_tipo_parentesco_persona INTEGER NOT NULL,
	fecha_inicio_parentesco DATE NOT NULL,
	fecha_fin_parentesco DATE,
	PRIMARY KEY (id_parentesco_persona),
	KEY (id_tipo_parentesco_persona),
	KEY (id_persona_1),
	KEY (id_persona_2)
);

CREATE TABLE persona_historico
(
	id_persona_historico BIGINT NOT NULL AUTO_INCREMENT,
	id_persona BIGINT NOT NULL,
	id_tipo_identificacion INTEGER NOT NULL,
	numero_identificacion VARCHAR(20) NOT NULL,
	codigo_fuente_informacion INTEGER NOT NULL,
	codigo_organismo INTEGER,
	fecha_ultima_actualizacion DATETIME NOT NULL,
	id_municipio_expedicion_docum INTEGER,
	fecha_expedicion_documento DATE,
	fecha_nacimiento DATE,
	fecha_fallecimiento DATE,
	numero_telefonico VARCHAR(20),
	id_estado_civil INTEGER,
	genero CHAR(1),
	apellido1 VARCHAR(30),
	apellido2 VARCHAR(30),
	nombre1 VARCHAR(30),
	nombre2 VARCHAR(30),
	correo_electronico VARCHAR(255),
	numero_celular VARCHAR(20),
	nombre_empresa_labora VARCHAR(60),
	notif_direc_empre_labor TINYINT,
	cargo_empresa_labora VARCHAR(30),
	telefono_empresa_labora VARCHAR(30),
	fax_empresa_labora VARCHAR(20),
	huella_digital BLOB,
	ruta_foto VARCHAR(255),
	codigo_tipo_vivienda INTEGER,
	codigo_nivel_educativo INTEGER,
	digito_verificacion SMALLINT,
	nombre_comercial VARCHAR(70),
	sigla VARCHAR(10),
	id_tipo_sociedad INTEGER,
	id_clase_actividad_economica INTEGER,
	fecha_registro DATE NOT NULL,
	PRIMARY KEY (id_persona_historico),
	KEY (codigo_organismo),
	KEY (id_clase_actividad_economica),
	KEY (id_estado_civil),
	KEY (id_tipo_identificacion),
	KEY (id_tipo_sociedad),
	KEY (codigo_fuente_informacion),
	KEY (codigo_tipo_vivienda),
	KEY (codigo_nivel_educativo),
	KEY (id_municipio_expedicion_docum),
	KEY (id_persona)
);

CREATE TABLE estado_civil
(
	id_estado_civil INTEGER NOT NULL AUTO_INCREMENT,
	codigo_estado_civil INTEGER NOT NULL,
	id_pais INTEGER NOT NULL,
	nombre_estado_civil VARCHAR(50) NOT NULL,
	abreviatura_estado_civil CHAR(5),
	PRIMARY KEY (id_estado_civil),
	UNIQUE KEY UQ_estad_civil_01(id_pais, codigo_estado_civil),
	KEY (id_pais)

);

-- ------------------------------------------------------------------------
-- General de sistema
-- ------------------------------------------------------------------------

CREATE TABLE catalogo
(
	id_catalogo INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	codigo VARCHAR(10) NOT NULL,
	sigla VARCHAR(5),
	editable TINYINT NOT NULL,
	descripcion VARCHAR(150),
	nombre_entidad VARCHAR(50),
	paquete_entidad VARCHAR(255) NOT NULL,
	id_catalogo_dependencia INTEGER,
	PRIMARY KEY (id_catalogo),
	UNIQUE KEY UQ_catal_01(nombre),
	KEY (id_catalogo_dependencia),
	CONSTRAINT CK_catal_01 CHECK (editable = 1 or editable = 0)
);

CREATE TABLE modulo
(
	codigo_modulo INTEGER NOT NULL,
	nombre_modulo VARCHAR(30) NOT NULL,
	descripcion_modulo VARCHAR(60) NOT NULL,
	PRIMARY KEY (codigo_modulo)
);

CREATE TABLE parametro
(
	codigo_parametro INTEGER NOT NULL,
	codigo_modulo INTEGER NOT NULL,
	nombre_parametro VARCHAR(70) NOT NULL,
	codigo_tipo_variable INTEGER NOT NULL,
	valor_parametro_defecto VARCHAR(255),
	codigo_unidad_parametro INTEGER,
	formato VARCHAR(50),
	editable_organismo TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (codigo_parametro),
	KEY (codigo_tipo_variable),
	KEY (codigo_modulo),
	CONSTRAINT CK_param_01 CHECK (editable_organismo = 0 or editable_organismo = 1)
);

CREATE TABLE parametro_organismo
(
	id_parametro INTEGER NOT NULL AUTO_INCREMENT,
	valor_parametro VARCHAR(1024) NOT NULL,
	codigo_organismo INTEGER NOT NULL,
	codigo_parametro INTEGER NOT NULL,
	fecha_inicio DATE NOT NULL,
	fecha_fin DATE,
	PRIMARY KEY (id_parametro),
	KEY (codigo_organismo),
	KEY (codigo_parametro)
);

CREATE TABLE proceso
(
	codigo_proceso INTEGER NOT NULL AUTO_INCREMENT,
	codigo_modulo INTEGER NOT NULL,
	nombre_proceso VARCHAR(70) NOT NULL,
	descripcion_proceso VARCHAR(60) NOT NULL,
	codigo_proceso_padre INTEGER,
	PRIMARY KEY (codigo_proceso),
	KEY (codigo_modulo),
	KEY (codigo_proceso_padre),
	CONSTRAINT CK_IdProcesoPadre CHECK (codigo_proceso_padre<codigo_proceso)
);


CREATE TABLE tipo_variable
(
	codigo_tipo_variable INTEGER NOT NULL,
	nombre_tipo_variable VARCHAR(70) NOT NULL,
	descripcion_tipo_variable VARCHAR(70),
	PRIMARY KEY (codigo_tipo_variable)
);

-- ------------------------------------------------------------------------
-- comparendos
-- ------------------------------------------------------------------------
CREATE TABLE tipo_infractor_confi_infra
(
	id_configuracion_infraccion BIGINT NOT NULL,
	codigo_tipo_infractor INTEGER NOT NULL,
	PRIMARY KEY (id_configuracion_infraccion, codigo_tipo_infractor),
	KEY (id_configuracion_infraccion),
	KEY (codigo_tipo_infractor)
);

CREATE TABLE tipo_entidad_agente_transito
(
	id_entidad_agente INTEGER NOT NULL AUTO_INCREMENT,
	codigo_entidad_agente VARCHAR(10) NOT NULL,
	id_pais INTEGER NOT NULL,
	nombre_entidad_agente VARCHAR(100) NOT NULL,
	descripcion_entidad_agente VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_entidad_agente),
	UNIQUE KEY UQ_tipo_entid_agent_trans_01(codigo_entidad_agente, id_pais),
	KEY (id_pais)
);

CREATE TABLE causal_infraccion
(
	id_causal_infraccion INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_causal_infraccion),
	UNIQUE KEY UQ_causa_infra_01(nombre),
	CONSTRAINT CK_causa_infra_01 CHECK (estado = 1 OR estado = 1)
);

CREATE TABLE tipo_sancion_confi_infra
(
	id_tipo_sancion INTEGER NOT NULL,
	id_configuracion_infraccion BIGINT NOT NULL,
	PRIMARY KEY (id_tipo_sancion, id_configuracion_infraccion),
	KEY (id_configuracion_infraccion),
	KEY (id_tipo_sancion)
);

CREATE TABLE tipo_sancion
(
	id_tipo_sancion INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_tipo_sancion),
	CONSTRAINT CK_tipo_sanci_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE normatividad
(
	id_normatividad INTEGER NOT NULL AUTO_INCREMENT,
	codigo_normatividad INTEGER NOT NULL,
	nombre VARCHAR(70) NOT NULL,
	id_ordenamiento_pais INTEGER NOT NULL,
	descripcion VARCHAR(70),
	fecha_inicio DATE NOT NULL,
	fecha_fin DATE,
	PRIMARY KEY (id_normatividad),
	KEY (id_ordenamiento_pais)
);

CREATE TABLE normatividad_organismo
(
	id_normatividad INTEGER NOT NULL,
	codigo_organismo INTEGER NOT NULL,
	PRIMARY KEY (id_normatividad, codigo_organismo),
	KEY (id_normatividad),
	KEY (codigo_organismo)
);

CREATE TABLE ordenamiento_pais
(
	id_ordenamiento_pais INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_ordenamiento_pais),
	CONSTRAINT CK_orden_pais_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE configuracion_infraccion
(
	id_configuracion_infraccion BIGINT NOT NULL AUTO_INCREMENT,
	id_infraccion INTEGER NOT NULL,
	descripcion TEXT NOT NULL,
	id_normatividad INTEGER NOT NULL,
	articulo TEXT NOT NULL,
	fecha_articulo DATE NOT NULL,
	requiere_placa TINYINT NOT NULL DEFAULT 1,
	infractor_obligatorio TINYINT NOT NULL DEFAULT 1,
	dias_genera_cartera SMALLINT,
	dia_habil_genera_cartera TINYINT,
	id_entidad_agente INTEGER,
	id_causal_infraccion INTEGER,
	id_ordenamiento_pais INTEGER NOT NULL,
	fecha_inicio_vigencia DATE NOT NULL,
	fecha_fin_vigencia DATE,
	genera_cartera TINYINT NOT NULL DEFAULT 0,
	aplica_embriaguez TINYINT NOT NULL DEFAULT 0,
	aplica_descuento TINYINT NOT NULL DEFAULT 0,
	genera_resolucion_automatica TINYINT NOT NULL DEFAULT 1,
	condiciones_especiales TINYINT NOT NULL DEFAULT 0,
	aplica_impugnacion TINYINT NOT NULL,
	PRIMARY KEY (id_configuracion_infraccion),
	KEY (id_infraccion),
	KEY (id_normatividad),
	KEY (id_entidad_agente),
	KEY (id_causal_infraccion),
	KEY (id_ordenamiento_pais),
	CONSTRAINT CK_confi_infra_01 CHECK (dia_habil_genera_cartera = 0 or dia_habil_genera_cartera = 1),
	CONSTRAINT CK_confi_infra_02 CHECK (requiere_placa = 0 or requiere_placa = 1),
	CONSTRAINT CK_confi_infra_03 CHECK (infractor_obligatorio = 0 or infractor_obligatorio = 1),
	CONSTRAINT CK_confi_infra_04 CHECK (genera_resolucion_automatica = 0 OR genera_resolucion_automatica = 1),
	CONSTRAINT CK_confi_infra_05 CHECK (condiciones_especiales = 0 OR condiciones_especiales = 1)
);

CREATE TABLE tipo_infraccion
(
	id_tipo_infraccion INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_tipo_infraccion),
	UNIQUE UQ_tipo_infra_01(nombre),
	CONSTRAINT CK_tipo_infra_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE infraccion_tipo_respo_solid
(
	codigo_responsable INTEGER NOT NULL,
	id_configuracion_infraccion BIGINT NOT NULL,
	PRIMARY KEY (codigo_responsable, id_configuracion_infraccion),
	KEY (id_configuracion_infraccion),
	KEY (codigo_responsable)
);

CREATE TABLE tipo_responsable_solidario
(
	codigo_responsable INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	codigo VARCHAR(10),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	sigla VARCHAR(5),
	PRIMARY KEY (codigo_responsable),
	CONSTRAINT CK_tipo_respo_solid_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE comparendo_cartera
(
	id_comparendo_cartera BIGINT NOT NULL AUTO_INCREMENT,
	cicomparendo BIGINT NOT NULL,
	id_cartera BIGINT NOT NULL,
	PRIMARY KEY (id_comparendo_cartera),
	KEY (cicomparendo),
	KEY (id_cartera)
);

CREATE TABLE estado_comparendo
(
	id_estado_comparendo INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL DEFAULT 1,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_estado_comparendo),
	CONSTRAINT CK_estad_compa_01 CHECK (estado=0 OR estado=1)
);

CREATE TABLE tipo_transporte_pasajero
(
	codigo_tipo_transporte_pasaj INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (codigo_tipo_transporte_pasaj),
	CONSTRAINT CK_tipo_trans_pasaj_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE tipo_comparendo
(
	id_tipo_comparendo INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL DEFAULT 1,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_comparendo),
	CONSTRAINT CK_tipo_compa_01 CHECK (estado = 1 OR estado = 0)
);

CREATE TABLE comparendo_patio
(
	id_comparendo_patio BIGINT NOT NULL,
	id_patio INTEGER,
	numero_patio INTEGER,
	nombre VARCHAR(150),
	numero_grua INTEGER,
	placa_grua VARCHAR(10),
	consecutivo_asignado_grua VARCHAR(16),
	numero_informe VARCHAR(20),
	id_direccion BIGINT,
	PRIMARY KEY (id_comparendo_patio),
	KEY (id_patio),
	KEY (id_direccion)
);

CREATE TABLE agente
(
	id_agente_transito INTEGER NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	placa_agente_transito VARCHAR(20),
	fecha_inicio_vigencia DATE NOT NULL,
	fecha_fin_vigencia DATE,
	id_persona BIGINT NOT NULL,
	id_entidad_agente_transito INTEGER NOT NULL,
	PRIMARY KEY (id_agente_transito),
	KEY (codigo_organismo),
	KEY (id_persona),
	KEY (id_entidad_agente_transito)
);

CREATE TABLE comparendo_agente
(
	id_comparendo_agente BIGINT NOT NULL,
	id_tipo_identificacion INTEGER,
	numero_identificacion VARCHAR(20),
	placa VARCHAR(10),
	apellido1 VARCHAR(30) NOT NULL,
	apellido2 VARCHAR(30),
	nombre1 VARCHAR(30) NOT NULL,
	nombre2 VARCHAR(30),
	id_unificacion_responsable BIGINT,
	nombre_responsable VARCHAR(30),
	id_agente_transito INTEGER,
	observaciones_agente VARCHAR(512),
	PRIMARY KEY (id_comparendo_agente),
	KEY (id_tipo_identificacion),
	KEY (id_unificacion_responsable),
	KEY (id_agente_transito),
	INDEX IND_compa_agent_01 (placa ASC)
);

CREATE TABLE comparendo_vehiculo
(
	id_comparendo_vehiculo BIGINT NOT NULL,
	id_tipo_servicio INTEGER,
	id_radio_accion INTEGER,
	id_modalidad INTEGER,
	placa_vehiculo VARCHAR(10),
	identificacion_vehiculo VARCHAR(70),
	numero_tarjeta_operacion VARCHAR(16),
	id_clase_vehiculo INTEGER,
	id_nivel_servicio INTEGER,
	codigo_organismo_matri_vehic INTEGER,
	codigo_organismo_licen_trans INTEGER,
	numero_licencia_transito VARCHAR(20),
	id_marca_vehiculo INTEGER,
	id_linea_vehiculo INTEGER,
	id_color INTEGER,
	numero_motor VARCHAR(25),
	modelo VARCHAR(4),
	peso_seco INTEGER,
	peso_neto INTEGER,
	codigo_tipo_transporte_pasaj INTEGER,
	PRIMARY KEY (id_comparendo_vehiculo),
	KEY (id_tipo_servicio),
	KEY (id_radio_accion),
	KEY (id_modalidad),
	KEY (id_clase_vehiculo),
	KEY (id_nivel_servicio),
	KEY (id_marca_vehiculo),
	KEY (id_linea_vehiculo),
	KEY (id_color),
	KEY (codigo_tipo_transporte_pasaj),
	KEY (codigo_organismo_licen_trans),
	KEY (codigo_organismo_matri_vehic),
	INDEX IND_compa_vehic_01 (placa_vehiculo ASC)
);

CREATE TABLE empresa_transporte
(
	id_empresa_transporte BIGINT NOT NULL,
	PRIMARY KEY (id_empresa_transporte)
);

CREATE TABLE tipo_categ_licen_condu
(
	id_categoria_licencia_conduc INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	sigla VARCHAR(5),
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_categoria_licencia_conduc),
	UNIQUE KEY UQ_tipo_categ_licen_condu_01(codigo),
	CONSTRAINT CK_tipo_categ_licen_condu_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE tipo_persona_comparendo
(
	codigo_tipo_persona_comparendo INTEGER NOT NULL,
	nombre VARCHAR(200) NOT NULL,
	PRIMARY KEY (codigo_tipo_persona_comparendo)
);

CREATE TABLE comparendo_persona
(
	id_comparendo_persona BIGINT NOT NULL AUTO_INCREMENT,
	cicomparendo BIGINT NOT NULL,
	codigo_tipo_persona_comparendo INTEGER NOT NULL,
	id_persona BIGINT,
	id_tipo_identificacion INTEGER,
	numero_identificacion VARCHAR(20),
	digito_verificacion_nit SMALLINT,
	id_licencia BIGINT,
	codigo_organismo_licencia INTEGER,
	numero_licencia VARCHAR(50),
	id_categoria_licencia_condu INTEGER,
	fecha_expedicion_licen_condu DATE,
	fecha_vencimiento_licen_condu DATE,
	apellido1 VARCHAR(30),
	apellido2 VARCHAR(30),
	nombre1 VARCHAR(30),
	nombre2 VARCHAR(30),
	razon_social VARCHAR(200),
	edad SMALLINT,
	id_direccion BIGINT,
	email VARCHAR(255),
	telefono_fijo VARCHAR(20),
	telefono_movil VARCHAR(20),
	fecha_incio DATE NOT NULL,
	fecha_fin DATE,
	PRIMARY KEY (id_comparendo_persona),
	UNIQUE KEY UQ_compa_perso_01(cicomparendo, codigo_tipo_persona_comparendo, id_persona),
	KEY (id_licencia),
	KEY (codigo_tipo_persona_comparendo),
	KEY (cicomparendo),
	KEY (id_persona),
	KEY (id_tipo_identificacion),
	KEY (id_direccion),
	KEY (id_categoria_licencia_condu),
	KEY (codigo_organismo_licencia)
);

CREATE TABLE orden_comparendo_nacional
(
	cicomparendo BIGINT NOT NULL AUTO_INCREMENT,
	numero_comparendo VARCHAR(30) NOT NULL,
	codigo_organismo_transito INTEGER NOT NULL,
	PRIMARY KEY (cicomparendo),
	UNIQUE KEY UQ_orden_compa_nacio_01(numero_comparendo, codigo_organismo_transito),
	KEY (codigo_organismo_transito)
);

CREATE TABLE procesa_evidencia
(
	id_procesa_evidencia INTEGER NOT NULL AUTO_INCREMENT,
	id_procesa_comparendo BIGINT NOT NULL,
	codigo_tipo_evidencia INTEGER,
	fecha_evidencia DATETIME,
	id_documento VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_procesa_evidencia),
	KEY (id_procesa_comparendo)
);

CREATE TABLE procesa_direccion
(
	id_procesa_direccion BIGINT NOT NULL AUTO_INCREMENT,
	codigo_tipo_via_principal INTEGER,
	numero_via_principal INTEGER,
	codigo_nombre_via_principal INTEGER,
	letra_via_principal CHAR(1),
	bis_via_principal VARCHAR(3),
	letra_bis_via_principal CHAR(1),
	codigo_cardinalidad_via_princ INTEGER,
	codigo_tipo_via_secundaria INTEGER,
	numero_via_secundaria INTEGER,
	codigo_nombre_via_secundaria INTEGER,
	letra_via_secundaria CHAR(1),
	bis_via_secundaria VARCHAR(3),
	letra_bis_via_secundaria CHAR(1),
	numero_placa_domiciliaria INTEGER,
	codigo_cardinalidad_via_secun INTEGER,
	complemento VARCHAR(150),
	id_pais INTEGER,
	id_departamento INTEGER,
	id_municipio INTEGER,
	id_localidad INTEGER,
	codigo_tipo_ubicabilidad INTEGER,
	barrio VARCHAR(150),
	codigo_tipo_coordenada INTEGER,
	latitud DOUBLE,
	longitud DOUBLE,
	PRIMARY KEY (id_procesa_direccion)
);

CREATE TABLE procesa_comparendo_persona
(
	id_comparendo_persona BIGINT NOT NULL AUTO_INCREMENT,
	id_procesamiento BIGINT NOT NULL,
	codigo_tipo_persona_comparendo INTEGER,
	id_tipo_identificacion INTEGER,
	numero_identificacion VARCHAR(20),
	digito_verificacion_nit SMALLINT,
	codigo_organismo_licencia INTEGER,
	numero_licencia VARCHAR(50),
	id_categoria_licencia_condu INTEGER,
	fecha_expedicion_licen_condu DATE,
	fecha_vencimiento_licen_condu DATE,
	apellido1 VARCHAR(30),
	apellido2 VARCHAR(30),
	nombre1 VARCHAR(30),
	nombre2 VARCHAR(30),
	razon_social VARCHAR(200),
	edad SMALLINT,
	id_procesa_direccion BIGINT,
	email VARCHAR(255),
	telefono_fijo VARCHAR(20),
	telefono_movil VARCHAR(20),
	PRIMARY KEY (id_comparendo_persona),
	KEY (id_procesamiento),
	KEY (id_procesa_direccion)
);

CREATE TABLE procesa_comparendo
(
	id_procesamiento BIGINT NOT NULL AUTO_INCREMENT,
	cicomparendo BIGINT,
	numero_comparendo VARCHAR(30),
	codigo_organismo_transito INTEGER NOT NULL,
	id_tipo_comparendo INTEGER,
	fecha_operacion DATETIME NOT NULL,
	codigo_origen INTEGER,
	id_medio_imposicion INTEGER,
	codigo_tipo_infractor INTEGER,
	id_tipo_notificacion_compa INTEGER,
	fecha_infraccion DATE,
	hora_infraccion TIME,
	id_procesa_direccion_compa BIGINT,
	codigo_infraccion VARCHAR(15),
	retiene_licencia TINYINT DEFAULT 0,
	existe_fuga_infractor TINYINT DEFAULT 0,
	niega_prueba_alcoholemia TINYINT,
	numero_prueba_alcoholemia VARCHAR(10),
	grado_alcoholemia INTEGER,
	valor_grado_alcoholemia INTEGER,
	fecha_prueba_alcoholemia DATE,
	numero_reincidencia INTEGER,
	fecha_notificacion DATETIME,
	observaciones_infractor VARCHAR(255),
	inmovilizado TINYINT DEFAULT 0,
	id_usuario INTEGER,
	id_ruta INTEGER,
	id_tipo_servicio INTEGER,
	id_radio_accion INTEGER,
	id_modalidad INTEGER,
	placa_vehiculo VARCHAR(10),
	identificacion_vehiculo VARCHAR(70),
	numero_tarjeta_operacion VARCHAR(16),
	id_clase_vehiculo INTEGER,
	id_nivel_servicio INTEGER,
	codigo_organismo_matri_vehic INTEGER,
	codigo_organimo_licen_trans INTEGER,
	numero_licencia_transito VARCHAR(20),
	id_marca_vehiculo INTEGER,
	id_linea_vehiculo INTEGER,
	id_color INTEGER,
	numero_motor VARCHAR(25),
	modelo VARCHAR(4),
	placa_agente VARCHAR(10),
	peso_seco INTEGER,
	peso_neto INTEGER,
	codigo_tipo_transporte_pasaj INTEGER,
	id_agente_transito INTEGER,
	observaciones_agente VARCHAR(512),
	id_tipo_identificacion_agente INTEGER,
	numero_identificacion_agente VARCHAR(20),
	id_unificacion_responsable BIGINT,
	nombre_responsable VARCHAR(30),
	apellido1_agente VARCHAR(30),
	apellido2_agente VARCHAR(30),
	nombre1_agente VARCHAR(30),
	nombre2_agente VARCHAR(30),
	id_patio INTEGER,
	numero_grua INTEGER,
	placa_grua VARCHAR(7),
	consecutivo_inmovilizacion VARCHAR(16),
	numero_patio INTEGER,
	nombre_patio VARCHAR(50),
	numero_informe VARCHAR(20),
	id_procesa_direccion_patio BIGINT,
	fecha_recepcion DATETIME,
	fecha_correccion DATETIME,
	es_polca TINYINT NOT NULL,
	PRIMARY KEY (id_procesamiento),
	KEY (codigo_organismo_transito),
	KEY (id_procesa_direccion_compa),
	KEY (cicomparendo),
	KEY (id_usuario),
	KEY (id_procesa_direccion_patio),
	CONSTRAINT CK_proce_compa_01 CHECK (retiene_licencia = 0 or retiene_licencia = 1),
	CONSTRAINT CK_proce_compa_02 CHECK (existe_fuga_infractor = 0 OR existe_fuga_infractor = 1),
	CONSTRAINT CK_proce_compa_03 CHECK (niega_prueba_alcoholemia = 0 OR niega_prueba_alcoholemia = 1),
	CONSTRAINT CK_proce_compa_04 CHECK (inmovilizado = 0 or inmovilizado = 1),
	CONSTRAINT CK_proce_compa_05 CHECK (activo = 1 OR activo = 0)
);

CREATE TABLE grado_alcoholemia
(
	id_grado_alcoholemia INTEGER NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	valor INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_grado_alcoholemia),
	UNIQUE KEY UQ_grado_alcoh_01(valor),
	KEY (codigo_organismo)
);

CREATE TABLE tipo_evidencia
(
	codigo_tipo_evidencia INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (codigo_tipo_evidencia),
	UNIQUE KEY UQ_tipo_evide_01(nombre),
	CONSTRAINT CK_tipo_evide_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE evidencia
(
	id_evidencia INTEGER NOT NULL AUTO_INCREMENT,
	cicomparendo BIGINT NOT NULL,
	codigo_tipo_evidencia INTEGER NOT NULL,
	fecha_evidencia DATE NOT NULL,
	id_documento VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_evidencia),
	KEY (cicomparendo),
	KEY (codigo_tipo_evidencia)
);

CREATE TABLE tipo_infractor
(
	codigo_tipo_infractor INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (codigo_tipo_infractor),
	CONSTRAINT CK_tipo_infra_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE nivel_servicio
(
	id_nivel_servicio INTEGER NOT NULL AUTO_INCREMENT,
	id_pais INTEGER NOT NULL,
	codigo_nivel_servicio VARCHAR(10) NOT NULL,
	nombre_nivel_servicio VARCHAR(50) NOT NULL,
	descripcion_nivel_servicio VARCHAR(100),
	PRIMARY KEY (id_nivel_servicio),
	UNIQUE KEY UQ_nivel_servi_01(codigo_nivel_servicio, id_pais),
	KEY (id_pais)
);

CREATE TABLE radio_accion
(
	id_radio_accion INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_radio_accion),
	UNIQUE KEY UQ_radio_accio_01(codigo),
	CONSTRAINT CK_radio_accio_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE medio_imposicion_comparendo
(
	codigo_medio_imposicion INTEGER NOT NULL,
	codigo VARCHAR(10) NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (codigo_medio_imposicion),
	UNIQUE KEY UQ_medio_impos_compa_01(codigo),
	CONSTRAINT CK_medio_impos_compa_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE tipo_origen_comparendo
(
	codigo_origen INTEGER NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(150) NOT NULL,
	codigo VARCHAR(10),
	sigla VARCHAR(5),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (codigo_origen),
	CONSTRAINT CK_tipo_orige_compa_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE detalle_procesamiento
(
	id_detalle_procesamiento INTEGER NOT NULL AUTO_INCREMENT,
	id_procesamiento BIGINT NOT NULL,
	id_error_procesamiento INTEGER NOT NULL,
	codigo_campo INTEGER NOT NULL,
	PRIMARY KEY (id_detalle_procesamiento),
	KEY (id_error_procesamiento),
	KEY (id_procesamiento),
	KEY (codigo_campo)
);

CREATE TABLE ruta
(
	id_ruta INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10) NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(150),
	id_empresa_transporte BIGINT NOT NULL,
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_ruta),
	UNIQUE KEY UQ_ruta_01(id_empresa_transporte, codigo),
	KEY (id_empresa_transporte),
	CONSTRAINT CK_ruta_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE modalidad
(
	id_modalidad INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_modalidad),
	CONSTRAINT CK_modal_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE clase_vehiculo
(
	id_clase_vehiculo INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	estado TINYINT NOT NULL DEFAULT 1,
	descripcion VARCHAR(150),
	sigla VARCHAR(5),
	PRIMARY KEY (id_clase_vehiculo),
	CONSTRAINT CK_clase_vehic_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE tipo_servicio
(
	id_tipo_servicio INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10) NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_servicio),
	UNIQUE KEY UQ_tipo_servi_01(codigo),
	CONSTRAINT CK_tipo_servi_01 CHECK (estado=0 OR estado=1)
);

CREATE TABLE tipo_notificacion_comparendo
(
	id_tipo_notificacion INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_tipo_notificacion),
	CONSTRAINT CK_tipo_notif_compa_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE trazabilidad_comparendo
(
	id_trazabilidad_comparendo BIGINT NOT NULL AUTO_INCREMENT,
	cicomparendo BIGINT NOT NULL,
	id_actividad INTEGER NOT NULL,
	fecha_ejecucion DATETIME NOT NULL,
	fecha_registro DATETIME NOT NULL,
	id_usuario INTEGER,
	usuario_externo VARCHAR(20),
	activo TINYINT NOT NULL DEFAULT 1,
	id_estado_comparendo INTEGER NOT NULL,
	numero_acto_administrativo VARCHAR(50),
	PRIMARY KEY (id_trazabilidad_comparendo),
	KEY (id_actividad),
	KEY (id_usuario),
	KEY (cicomparendo),
	KEY (id_estado_comparendo),
	INDEX IND_traza_compa_01 (fecha_ejecucion ASC),
	CONSTRAINT CK_traza_compa_01 CHECK (activo = 0 or activo = 1)
);

CREATE TABLE infraccion
(
	id_infraccion INTEGER NOT NULL AUTO_INCREMENT,
	id_tipo_infraccion INTEGER,
	numeral_infraccion VARCHAR(5),
	codigo_infraccion VARCHAR(15) NOT NULL,
	id_tipo_comparendo INTEGER NOT NULL,
	PRIMARY KEY (id_infraccion),
	UNIQUE KEY UQ_infra_01(codigo_infraccion),
	KEY (id_tipo_infraccion),
	KEY (id_tipo_comparendo)
);

CREATE TABLE comparendo
(
	cicomparendo BIGINT NOT NULL,
	fecha_registro DATETIME NOT NULL,
	fecha_modificacion DATETIME NOT NULL,
	id_estado_comparendo INTEGER NOT NULL,
	id_tipo_comparendo INTEGER NOT NULL,
	codigo_origen INTEGER,
	codigo_medio_imposicion INTEGER NOT NULL,
	codigo_tipo_infractor INTEGER,
	id_tipo_notificacion_compa INTEGER,
	fecha_infraccion DATE NOT NULL,
	hora_infraccion TIME NOT NULL,
	id_direccion_comparendo BIGINT NOT NULL,
	id_infraccion INTEGER NOT NULL,
	retiene_licencia TINYINT DEFAULT 0,
	existe_fuga_infractor TINYINT NOT NULL DEFAULT 0,
	niega_prueba_alcoholemia TINYINT,
	numero_prueba_alcoholemia VARCHAR(10),
	id_grado_alcoholemia INTEGER,
	valor_grado_alcoholemia INTEGER,
	fecha_prueba_alcoholemia DATE,
	numero_reincidencia INTEGER,
	fecha_notificacion DATE,
	observaciones_infractor VARCHAR(255),
	id_usuario INTEGER NOT NULL,
	id_ruta INTEGER,
	fecha_genera_cartera DATE,
	cartera_generada TINYINT NOT NULL,
	es_polca TINYINT NOT NULL DEFAULT 0,
	id_estado_comparendo_simit INTEGER,
	id_documento_notificacion BIGINT,
	PRIMARY KEY (cicomparendo),
	KEY (id_tipo_comparendo),
	KEY (id_infraccion),
	KEY (id_direccion_comparendo),
	KEY (codigo_medio_imposicion),
	KEY (codigo_origen),
	KEY (codigo_tipo_infractor),
	KEY (id_usuario),
	KEY (id_ruta),
	KEY (id_estado_comparendo),
	KEY (id_tipo_notificacion_compa),
	KEY (id_grado_alcoholemia),
	INDEX IND_compa_01 (fecha_infraccion ASC),
	CONSTRAINT CK_compa_01 CHECK (retiene_licencia = 0 or retiene_licencia = 1),
	CONSTRAINT CK_compa_02 CHECK (existe_fuga_infractor = 0 or existe_fuga_infractor = 1),
	CONSTRAINT CK_compa_03 CHECK (niega_prueba_alcoholemia = 0 OR niega_prueba_alcoholemia = 1),
	CONSTRAINT CK_compa_05 CHECK (es_polca = 1 or es_polca = 0 )
);

CREATE TABLE error_procesamiento
(
	id_error_procesamiento INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10) NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL DEFAULT 1,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_error_procesamiento),
	UNIQUE KEY UQ_error_proce_01(codigo),
	CONSTRAINT CK_error_proce_01 CHECK (estado = 0 OR estado = 1)
);
-- ------------------------------------------------------------------------
-- configuraciones
-- ------------------------------------------------------------------------
CREATE TABLE valor_configuracion
(
	id_valor_configuracion INTEGER NOT NULL AUTO_INCREMENT,
	id_configuracion INTEGER NOT NULL,
	valor TEXT NOT NULL,
	PRIMARY KEY (id_valor_configuracion),
	KEY (id_configuracion)
);

CREATE TABLE configuracion
(
	id_configuracion INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(150),
	plantilla TEXT NOT NULL,
	PRIMARY KEY (id_configuracion),
	UNIQUE KEY UQ_confi_01(codigo),
	UNIQUE KEY UQ_confi_02(nombre)
);
-- ------------------------------------------------------------------------
-- patios
-- ------------------------------------------------------------------------
CREATE TABLE patio
(
	id_patio INTEGER NOT NULL AUTO_INCREMENT,
	codigo_patio VARCHAR(10) NOT NULL,
	codigo_organismo INTEGER NOT NULL,
	nombre_patio VARCHAR(60),
	id_direccion_patio BIGINT,
	PRIMARY KEY (id_patio),
	KEY (id_direccion_patio),
	KEY (codigo_organismo),
	UNIQUE KEY UQ_patio_01 (codigo_organismo,codigo_patio)
);
-- ------------------------------------------------------------------------
-- registro vehicular
-- ------------------------------------------------------------------------
CREATE TABLE estado_licencia
(
	id_estado_licencia INTEGER NOT NULL,
	nombre VARCHAR(20) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL DEFAULT 1,
	codigo VARCHAR(10),
	descripcion VARCHAR(150),
	PRIMARY KEY (id_estado_licencia),
	CONSTRAINT CK_estad_licen_01 CHECK (estado = 1 OR estado = 0)
);

CREATE TABLE marca_vehiculo
(
	id_marca_vehiculo INTEGER NOT NULL AUTO_INCREMENT,
	sigla VARCHAR(5),
	nombre VARCHAR(50) NOT NULL,
	estado TINYINT NOT NULL DEFAULT 1,
	codigo VARCHAR(10),
	descripcion VARCHAR(150),
	PRIMARY KEY (id_marca_vehiculo),
	CONSTRAINT CK_marca_vehic_01 CHECK (estado = 0 OR estado =1)
);

CREATE TABLE linea_vehiculo
(
	id_linea_vehiculo INTEGER NOT NULL AUTO_INCREMENT,
	id_marca_vehiculo INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL DEFAULT 1,
	codigo VARCHAR(10),
	descripcion VARCHAR(150),
	PRIMARY KEY (id_linea_vehiculo),
	KEY (id_marca_vehiculo),
	CONSTRAINT CK_linea_vehic_01 CHECK (estado = 0 OR estado =1)
);

CREATE TABLE color
(
	id_color INTEGER NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(150) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL DEFAULT 1,
	codigo VARCHAR(10),
	descripcion VARCHAR(150),
	PRIMARY KEY (id_color),
	CONSTRAINT CK_color_01 CHECK (estado = 1 OR estado = 0)
);

CREATE TABLE licencia_conduccion
(
	id_licencia BIGINT NOT NULL AUTO_INCREMENT,
	licencia_conduccion VARCHAR(20) NOT NULL,
	fecha_expedicion_licencia DATE NOT NULL,
	fecha_vencimiento_licencia BIGINT,
	codigo_organismo INTEGER NOT NULL,
	id_persona BIGINT NOT NULL,
	id_categoria_licencia_conduc INTEGER NOT NULL,
	id_estado_licencia INTEGER NOT NULL,
	PRIMARY KEY (id_licencia),
	KEY (codigo_organismo),
	KEY (id_persona),
	KEY (id_estado_licencia),
	KEY (id_categoria_licencia_conduc)
);

CREATE TABLE tipo_propietario
(
	id_tipo_propietario INTEGER NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL DEFAULT 1,
	codigo VARCHAR(10),
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_propietario),
	CONSTRAINT CK_tipo_propi_01 CHECK (estado = 1 OR estado = 0)
);

CREATE TABLE empresa_vehiculo
(
	id_empresa_vehiculo BIGINT NOT NULL AUTO_INCREMENT,
	id_vehiculo BIGINT NOT NULL,
	id_persona_juridica BIGINT NOT NULL,
	fecha_inicio DATE NOT NULL,
	fecha_fin DATE,
	PRIMARY KEY (id_empresa_vehiculo),
	KEY (id_persona_juridica),
	KEY (id_vehiculo)
);

CREATE TABLE historico_vehiculo
(
	id_historico_vehiculo BIGINT NOT NULL AUTO_INCREMENT,
	id_vehiculo BIGINT NOT NULL,
	codigo_organismo INTEGER NOT NULL,
	id_clase_vehiculo INTEGER NOT NULL,
	id_tipo_servicio INTEGER NOT NULL,
	id_radio_accion INTEGER,
	id_modalidad INTEGER NOT NULL,
	numero_motor BIGINT NOT NULL,
	id_color INTEGER NOT NULL,
	fecha_expedicion_tarje_opera DATE,
	fecha_vencimiento_tarje_opera DATE,
	numero_tarjeta_operacion INTEGER,
	campo_cambio VARCHAR(100) NOT NULL,
	fecha_inicial DATE NOT NULL,
	fecha_final DATE,
	PRIMARY KEY (id_historico_vehiculo),
	KEY (id_vehiculo),
	KEY (codigo_organismo),
	KEY (id_clase_vehiculo),
	KEY (id_tipo_servicio),
	KEY (id_radio_accion),
	KEY (id_modalidad),
	KEY (id_color)
);

CREATE TABLE origen_registro_vehicular
(
	id_origen_registro_vehicular INTEGER NOT NULL,
	nombre VARCHAR(25) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL DEFAULT 1,
	codigo VARCHAR(10),
	descripcion VARCHAR(150),
	PRIMARY KEY (id_origen_registro_vehicular),
	CONSTRAINT CK_orige_regis_vehic_01 CHECK (estado = 0 OR estado =1)
);

CREATE TABLE propietario_vehiculo
(
	id_propietario BIGINT NOT NULL AUTO_INCREMENT,
	id_persona BIGINT NOT NULL,
	id_vehiculo BIGINT NOT NULL,
	id_tipo_propietario INTEGER NOT NULL,
	fecha_inicio DATE NOT NULL,
	fecha_fin DATE,
	porcentaje DECIMAL(4,1) NOT NULL,
	PRIMARY KEY (id_propietario),
	KEY (id_persona),
	KEY (id_vehiculo),
	KEY (id_tipo_propietario)
);

CREATE TABLE seguimiento_estado_licencia
(
	id_seguimiento_estado_licencia BIGINT NOT NULL AUTO_INCREMENT,
	id_licencia BIGINT NOT NULL,
	id_estado_licencia INTEGER NOT NULL,
	observacion VARCHAR(256),
	fecha_cambio_estado DATE NOT NULL,
	PRIMARY KEY (id_seguimiento_estado_licencia),
	KEY (id_licencia),
	KEY (id_estado_licencia)
);

CREATE TABLE vehiculo
(
	id_vehiculo BIGINT NOT NULL AUTO_INCREMENT,
	licencia_transito VARCHAR(16) NOT NULL,
	codigo_organismo INTEGER NOT NULL,
	id_clase_vehiculo INTEGER NOT NULL,
	id_tipo_servicio INTEGER NOT NULL,
	id_radio_accion INTEGER,
	id_modalidad INTEGER,
	id_linea_vehiculo INTEGER NOT NULL,
	numero_motor VARCHAR(25) NOT NULL,
	modelo INTEGER NOT NULL,
	id_color INTEGER NOT NULL,
	placa VARCHAR(7) NOT NULL,
	fecha_expedicion_tarje_opera DATE,
	fecha_vencimiento_tarje_opera DATE,
	numero_tarjeta_opera INTEGER,
	PRIMARY KEY (id_vehiculo),
	UNIQUE KEY UQ_vehic_01(licencia_transito),
	KEY (codigo_organismo),
	KEY (id_clase_vehiculo),
	KEY (id_tipo_servicio),
	KEY (id_radio_accion),
	KEY (id_modalidad),
	KEY (id_linea_vehiculo),
	KEY (id_color)
);
-- ------------------------------------------------------------------------
-- administracion
-- ------------------------------------------------------------------------
CREATE TABLE entidad
(
	codigo_entidad INTEGER NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	PRIMARY KEY (codigo_entidad)
);

CREATE TABLE campo_entidad
(
	codigo_campo INTEGER NOT NULL,
	nombre_campo VARCHAR(90) NOT NULL,
	codigo_entidad INTEGER NOT NULL,
	PRIMARY KEY (codigo_campo),
	KEY (codigo_entidad)
);

CREATE TABLE actividad
(
	id_actividad INTEGER NOT NULL AUTO_INCREMENT,
	codigo_proceso INTEGER NOT NULL,
	codigo_actividad INTEGER NOT NULL,
	nombre VARCHAR(80) NOT NULL,
	descripcion VARCHAR(150) NOT NULL,
	PRIMARY KEY (id_actividad),
	KEY (codigo_proceso)
);

CREATE TABLE dia_no_habil
(
	id_dia_no_habil INTEGER NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	fecha DATE NOT NULL,
	observacion VARCHAR(300),
	PRIMARY KEY (id_dia_no_habil),
	KEY (codigo_organismo)
);
-- ------------------------------------------------------------------------
-- CARTERA
-- ------------------------------------------------------------------------
CREATE TABLE estado_obligacion
(
	codigo_estado_obligacion INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (codigo_estado_obligacion),
	CONSTRAINT CK_estad_oblig CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE tipo_saldo
(
	id_tipo_saldo INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_saldo),
	UNIQUE KEY UQ_tipo_saldo(nombre)
);

CREATE TABLE tipo_obligacion
(
	codigo_tipo_obligacion INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (codigo_tipo_obligacion),
	CONSTRAINT CK_tipo_oblig_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE log_afectar_cartera
(
	id_log_afectar_cartera BIGINT NOT NULL AUTO_INCREMENT,
	id_cartera BIGINT NOT NULL,
	fecha_creacion DATETIME NOT NULL,
	id_concepto INTEGER NOT NULL,
	valor_movimiento DECIMAL(16,2) NOT NULL,
	fecha_movimiento DATETIME NOT NULL,
	id_usuario INTEGER NOT NULL,
	codigo_excepcion INTEGER,
	descripcion_excepcion VARCHAR(250),
	PRIMARY KEY (id_log_afectar_cartera),
	KEY (id_usuario),
	KEY (id_concepto),
	KEY (id_cartera)
);

CREATE TABLE actividad_cartera
(
	id_actividad_cartera INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (id_actividad_cartera),
	CONSTRAINT CK_activ_carte_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE trazabilidad_cartera
(
	id_trazabilidad_cartera BIGINT NOT NULL AUTO_INCREMENT,
	fecha_registro DATETIME NOT NULL,
	id_cartera BIGINT NOT NULL,
	id_actividad_cartera INTEGER NOT NULL,
	login_usuario VARCHAR(60) NOT NULL,
	codigo_estado_obligacion INTEGER NOT NULL,
	PRIMARY KEY (id_trazabilidad_cartera),
	KEY (codigo_estado_obligacion),
	KEY (id_cartera),
	KEY (id_actividad_cartera)
);

CREATE TABLE saldo_cartera
(
	id_saldo_cartera BIGINT NOT NULL AUTO_INCREMENT,
	id_cartera BIGINT NOT NULL,
	saldo NUMERIC(10,2) NOT NULL,
	fecha_registro DATE NOT NULL,
	fecha_calculo DATE NOT NULL,
	id_tipo_saldo INTEGER NOT NULL,
	PRIMARY KEY (id_saldo_cartera),
	KEY (id_cartera),
	KEY (id_tipo_saldo)
);

CREATE TABLE cartera
(
	id_cartera BIGINT NOT NULL AUTO_INCREMENT,
	origen INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	fecha_creacion DATE NOT NULL,
	fecha_inicio DATE,
	fecha_registro DATE NOT NULL,
	fecha_actualizacion DATE NOT NULL,
	id_deudor BIGINT NOT NULL,
	codigo_tipo_obligacion INTEGER NOT NULL,
	codigo_estado_obligacion INTEGER NOT NULL,
	PRIMARY KEY (id_cartera),
	KEY (id_deudor),
	KEY (codigo_estado_obligacion),
	KEY (codigo_tipo_obligacion)
);

CREATE TABLE movimientos_cartera
(
	id_movimiento_cartera BIGINT NOT NULL AUTO_INCREMENT,
	id_cartera BIGINT NOT NULL,
	fecha_movimiento DATETIME NOT NULL,
	id_concepto INTEGER NOT NULL,
	valor_movimiento DECIMAL(16,2) NOT NULL,
	fecha_creacion DATETIME NOT NULL,
	observaciones VARCHAR(300),
	login_usuario VARCHAR(60) NOT NULL,
	PRIMARY KEY (id_movimiento_cartera),
	KEY (id_concepto),
	KEY (id_cartera),
	CONSTRAINT CK_movim_carte CHECK (valor_movimiento>=0)
);

CREATE TABLE concepto_cartera
(
	id_concepto INTEGER NOT NULL AUTO_INCREMENT,
	codigo_tipo_concepto INTEGER NOT NULL,
	codigo_concepto INTEGER NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	id_tipo_saldo INTEGER NOT NULL,
	PRIMARY KEY (id_concepto),
	KEY (id_tipo_saldo),
	KEY (codigo_tipo_concepto)
);

CREATE TABLE tipo_concepto_cartera
(
	codigo_tipo_concepto INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL DEFAULT 1,
	PRIMARY KEY (codigo_tipo_concepto),
	CONSTRAINT CK_tipo_conce_carte_01 CHECK (estado = 0 OR estado = 1)
);

-- ------------------------------------------------------------------------
-- SPRINT 05
-- ------------------------------------------------------------------------
CREATE TABLE tipo_moneda
(
	id_tipo_moneda INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_moneda)
);

CREATE TABLE unidad_monetaria
(
	id_unidad_monetaria INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_unidad_monetaria),
	UNIQUE KEY UQ_unida_monet_01(codigo)
);

CREATE TABLE tarifa_infraccion
(
	id_tarifa_infraccion INTEGER NOT NULL AUTO_INCREMENT,
	id_infraccion INTEGER NOT NULL,
	porcentaje_descuento DECIMAL(5,2) NOT NULL DEFAULT 0,
	valor_infraccion DECIMAL(16,2) NOT NULL,
	valor_descuento DECIMAL(16,2) NOT NULL,
	valor_comparendo DECIMAL(16,2) NOT NULL,
	valor_cia DECIMAL(16,2) NOT NULL,
	fecha_inicio_vigencia DATE,
	fecha_final_vigencia DATE,
	tarifa_confirmada TINYINT,
	fecha_creacion DATE NOT NULL,
	fecha_confirmacion DATE,
	PRIMARY KEY (id_tarifa_infraccion),
	INDEX IX_tarif_infra_01 (id_infraccion ASC),
	CONSTRAINT CK_tarif_infra_01 CHECK (valor_infraccion>=0),
	CONSTRAINT CK_tarif_infra_02 CHECK (tarifa_confirmada = 0 or tarifa_confirmada =1)
);

-- ------------------------------------------------------------------------
-- SPRINT 06
-- ------------------------------------------------------------------------

-- ------------------------------------------------------------------------
-- SPRINT 07
-- ------------------------------------------------------------------------
CREATE TABLE estado_comparendo_simit
(
	id_estado_comparendo_simit INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_estado_comparendo_simit),
	CONSTRAINT CK_estad_compa_simit CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE archivo_notificacion_simit
(
	id_archivo_notificacion_simit BIGINT NOT NULL AUTO_INCREMENT,
	id_notificacion_simit BIGINT NOT NULL,
	id_documento VARCHAR(255) NOT NULL,
	ruta_documento VARCHAR(512) NOT NULL,
	cantidad_registros INTEGER NOT NULL,
	numero_oficio VARCHAR(10) NOT NULL,
	PRIMARY KEY (id_archivo_notificacion_simit),
	KEY (id_notificacion_simit)
);

CREATE TABLE tipo_resultado_envio_simit
(
	id_tipo_resultado_envio_simit INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_resultado_envio_simit),
	CONSTRAINT CK_tipo_resul_envio_simit_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE tipo_documento_envio_simit
(
	id_tipo_documento_envio_simit INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL DEFAULT 0,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_documento_envio_simit),
	CONSTRAINT CK_tipo_docum_envio_simit_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE notificacion_simit
(
	id_notificacion_simit BIGINT NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	fecha_generacion DATETIME NOT NULL,
	id_tipo_documento_envio_simit INTEGER NOT NULL,
	cantidad_registros INTEGER NOT NULL,
	id_tipo_resultado_envio_simit INTEGER NOT NULL,
	PRIMARY KEY (id_notificacion_simit),
	KEY (codigo_organismo),
	KEY (id_tipo_documento_envio_simit),
	KEY (id_tipo_resultado_envio_simit)
);

CREATE TABLE valor_homologacion
(
	id_valor_homologacion BIGINT NOT NULL,
	id_tipo_homologacion BIGINT NOT NULL,
	valor_origen VARCHAR(50) NOT NULL,
	valor_final VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_valor_homologacion),
	KEY (id_tipo_homologacion)
);

CREATE TABLE tipo_homologacion
(
	id_tipo_homologacion BIGINT NOT NULL,
	id_servicio_homologacion INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_homologacion),
	KEY (id_servicio_homologacion)
);

CREATE TABLE servicio_homologacion
(
	id_servicio_homologacion INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(150),
	estado TINYINT NOT NULL,
	PRIMARY KEY (id_servicio_homologacion),
	CONSTRAINT CK_servi_homol_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE interes
(
	id_interes INTEGER NOT NULL AUTO_INCREMENT,
	fecha_inicial DATE NOT NULL,
	fecha_final DATE NOT NULL,
	porcentaje_tasa_interes DECIMAL(13,10) NOT NULL,
	porcentaje_interes_diario DECIMAL(13,10) NOT NULL,
	id_clase_interes INTEGER NOT NULL,
	id_periodo_interes INTEGER NOT NULL,
	PRIMARY KEY (id_interes),
	KEY (id_clase_interes),
	KEY (id_periodo_interes)
);

CREATE TABLE periodo_interes
(
	id_periodo_interes INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	estado TINYINT NOT NULL DEFAULT 1,
	descripcion VARCHAR(150),
	sigla VARCHAR(5),
	PRIMARY KEY (id_periodo_interes),
	CONSTRAINT CK_perio_inter_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE clase_interes
(
	id_clase_interes INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	estado TINYINT NOT NULL DEFAULT 1,
	descripcion VARCHAR(150),
	sigla VARCHAR(5),
	PRIMARY KEY (id_clase_interes),
	CONSTRAINT CK_clase_inter_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE tipo_consecutivo
(
	id_tipo_consecutivo INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_consecutivo),
	CONSTRAINT CK_tipo_conse_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE consecutivo
(
	id_consecutivo INTEGER NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	id_tipo_consecutivo INTEGER NOT NULL,
	consecutivo VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_consecutivo),
	KEY (codigo_organismo),
	KEY (id_tipo_consecutivo)
);
-- ------------------------------------------------------------------------
-- SPRINT 08
CREATE TABLE tipo_documento_generado
(
	id_tipo_documento_generado BIGINT NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_documento_generado),
	CONSTRAINT CK_tipo_docum_gener_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE tipo_fecha_origen
(
	codigo_fecha_origen INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	codigo VARCHAR(10) NOT NULL,
	estado TINYINT NOT NULL DEFAULT 1,
	sigla VARCHAR(5) NULL,
	descripcion VARCHAR(150) NULL,
	PRIMARY KEY (codigo_fecha_origen),
	UNIQUE UQ_tipo_fecha_orige_01(codigo),
	CONSTRAINT CK_tipo_fecha_orige CHECK (estado = 0 OR estado = 1)

)  COMMENT='Es fecha notificacion o fecha de imposicion  
 Catalogo que contiene el tipo fecha de origen de comparendo: 
 1 - Fecha notificacion,  2 - Fecha imposicion, ';

CREATE TABLE detalle_descuento
(
	id_descuento_detalle INTEGER NOT NULL AUTO_INCREMENT,
	id_descuento INTEGER NOT NULL,
	porcentaje_descuento DECIMAL(5,2) NOT NULL,
	dias_inicio SMALLINT NOT NULL,
	dias_fin SMALLINT NOT NULL,
	id_concepto INTEGER NOT NULL,
	PRIMARY KEY (id_descuento_detalle),
	KEY (id_descuento),
	KEY (id_concepto)
)  COMMENT='Contiene la parametrización del descuento ';

CREATE TABLE descuento_notificacion
(
	id_descuento INTEGER NOT NULL,
	id_tipo_notificacion INTEGER NOT NULL,
	PRIMARY KEY (id_descuento, id_tipo_notificacion),
	KEY (id_descuento),
	KEY (id_tipo_notificacion)

)  COMMENT='tabla que resuelve la relación de muchos a muchos entre la configuración de descuento y tipo de notifixcación de comparendos';

CREATE TABLE descuento_medio_imposicion
(
	id_descuento INTEGER NOT NULL,
	codigo_medio_imposicion INTEGER NOT NULL,
	PRIMARY KEY (id_descuento, codigo_medio_imposicion),
	KEY (id_descuento),
	KEY (codigo_medio_imposicion)
)  COMMENT='indica los medios de imposición que aplican a la configuración de descuento';


CREATE TABLE tipo_descuento
(
	codigo_tipo_descuento INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	codigo VARCHAR(10) NOT NULL,
	sigla VARCHAR(5) NULL,
	estado TINYINT NOT NULL DEFAULT 1,
	descripcion VARCHAR(150) NULL,
	PRIMARY KEY (codigo_tipo_descuento),
	UNIQUE UQ_tipo_descu_01(codigo),
	CONSTRAINT CK_tipo_descu_01 CHECK (estado = 0 OR estado = 1)
)  COMMENT='Catalogo que contiene las Formas de parametrizar los tipos de descuentos: 
 1. Tipo Notificacion Comparendo, 2. Tipo Dias Transcurridos';


CREATE TABLE configuracion_descuento
(
	id_descuento INTEGER NOT NULL AUTO_INCREMENT,
	codigo_tipo_descuento INTEGER NOT NULL,
	descripcion VARCHAR(50) NULL,
	resolucion_especial TINYINT NOT NULL,
	interes_comparendo TINYINT NOT NULL,
	fecha_vigencia_inicio DATE NOT NULL,
	fecha_vigencia_fin DATE NULL,
	codigo_fecha_origen INTEGER NOT NULL,
	dias_desde_fecha_imposicion INTEGER NULL,
	limite CHAR(2) NULL,
	codigo_organismo INTEGER NOT NULL,
	cantidad_dias_habiles INTEGER NULL,
	cantidad_dias_calendario INTEGER NULL,
	PRIMARY KEY (id_descuento),
	KEY (codigo_organismo),
	KEY (codigo_fecha_origen),
	KEY (codigo_tipo_descuento),
	CONSTRAINT CK_confi_descu_01 CHECK (resolucion_especial=0 or resolucion_especial=1),
	CONSTRAINT CK_confi_descu_02 CHECK (limite='>=' or limite='<='  or limite='<' or limite='>'),
	CONSTRAINT CK_confi_descu_03 CHECK (cantidad_dias_habiles >= 0),
	CONSTRAINT CK_confi_descu_04 CHECK (cantidad_dias_calendario >= 0)
)  COMMENT='Tabla que almacena las configuraciones de descuento definidas por el usuario para el organismo de transito';

-- ------------------------------------------------------------------------
-- SPRINT 09
CREATE TABLE campo_rectifica_comparendo
(
	id_campo_rectifica_comparendo BIGINT NOT NULL AUTO_INCREMENT,
	valor_original VARCHAR(255),
	valor_nuevo VARCHAR(255),
	id_rectifica_comparendo BIGINT NOT NULL,
	codigo_campo INTEGER NOT NULL,
	PRIMARY KEY (id_campo_rectifica_comparendo),
	KEY (id_rectifica_comparendo),
	KEY (codigo_campo)
);

CREATE TABLE rectifica_comparendo
(
	id_rectifica_comparendo BIGINT NOT NULL AUTO_INCREMENT,
	fecha DATETIME NOT NULL,
	cicomparendo BIGINT NOT NULL,
	PRIMARY KEY (id_rectifica_comparendo),
	KEY (cicomparendo)
);

CREATE TABLE notificacion_correo_comparendo
(
	id_notificacion_correo_compa BIGINT NOT NULL AUTO_INCREMENT,
	id_notificacion_correo BIGINT NOT NULL,
	cicomparendo BIGINT NOT NULL,
	notificado TINYINT,
	observacion VARCHAR(255),
	id_documento_notificacion BIGINT,
	consecutivo VARCHAR(50),
	fecha_cargue DATETIME,
	PRIMARY KEY (id_notificacion_correo_compa),
	UNIQUE UQ_notif_corre_compa_01(cicomparendo),
	KEY (id_notificacion_correo),
	KEY (cicomparendo)
);

CREATE TABLE notificacion_correo
(
	id_notificacion_correo BIGINT NOT NULL AUTO_INCREMENT,
	fecha_generacion DATE NOT NULL,
	id_archivo_generado VARCHAR(255) NOT NULL,
	consecutivo VARCHAR(50) NOT NULL,
	notificacion_generada TINYINT NOT NULL,
	PRIMARY KEY (id_notificacion_correo),
	CONSTRAINT CK_notif_corre_01 CHECK (notificacion_generada = 0 OR notificacion_generada = 1)
);

CREATE TABLE pago_comparendo
(
	id_pago_comparendo BIGINT NOT NULL AUTO_INCREMENT,
	cicomparendo BIGINT NOT NULL,
	id_pago BIGINT NOT NULL,
	PRIMARY KEY (id_pago_comparendo),
	KEY (cicomparendo)
);

CREATE TABLE comparendo_proceso_contra
(
	cicomparendo BIGINT NOT NULL,
	id_proceso_contravencional INTEGER NOT NULL,
	KEY (cicomparendo),
	KEY (id_proceso_contravencional)
);

CREATE TABLE proceso_contravencional
(
	id_proceso_contravencional INTEGER NOT NULL AUTO_INCREMENT,
	id_tipo_proceso INTEGER NOT NULL,
	id_estado_proceso INTEGER NOT NULL,
	PRIMARY KEY (id_proceso_contravencional),
	KEY (id_tipo_proceso),
	KEY (id_estado_proceso)
);

CREATE TABLE tipo_proceso
(
	id_tipo_proceso INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_proceso),
	CONSTRAINT CK_tipo_proceso_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE estado_proceso
(
	id_estado_proceso INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_estado_proceso),
	CONSTRAINT CK_estado_proceso_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE curso_pedagogico
(
	id_curso_pedagogico INTEGER NOT NULL AUTO_INCREMENT,
	numero_comparendo VARCHAR(50) NOT NULL,
	fecha_curso DATE NOT NULL,
	id_persona BIGINT NOT NULL,
	cicomparendo BIGINT,
	PRIMARY KEY (id_curso_pedagogico),
	KEY (id_persona),
	KEY (cicomparendo)
);

CREATE TABLE estado_resolucion
(
	id_estado_resolucion INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_estado_resolucion),
	CONSTRAINT CK_estad_resol_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE comparendo_resolucion
(
	id_comparendo_resolucion BIGINT NOT NULL AUTO_INCREMENT,
	id_resolucion BIGINT NOT NULL,
	cicomparendo BIGINT NOT NULL,
	PRIMARY KEY (id_comparendo_resolucion),
	KEY (cicomparendo),
	KEY (id_resolucion)
);

CREATE TABLE resolucion
(
	id_resolucion BIGINT NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	ano_resolucion INTEGER,
	numero_resolucion VARCHAR(50),
	fecha_resolucion DATETIME,
	id_tipo_resolucion INTEGER,
	id_estado_resolucion INTEGER,
	id_documento BIGINT,
	resolucion_exitosa TINYINT NOT NULL,
	id_resolucion_anterior BIGINT,
	PRIMARY KEY (id_resolucion),
	KEY (codigo_organismo),
	KEY (id_tipo_resolucion),
	KEY (id_estado_resolucion),
	KEY (id_resolucion_anterior)
);

CREATE TABLE tipo_resolucion
(
	id_tipo_resolucion INTEGER NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_tipo_resolucion),
	CONSTRAINT CK_tipo_resol_01 CHECK (estado = 0 OR estado = 1)
);

-- ------------------------------------------------------------------------
-- SPRINT 10
CREATE TABLE mensaje_error_cargue_acuse
(
	id_mensaje_error_cargue_acuse INTEGER NOT NULL,
	descripcion VARCHAR(150) NOT NULL,
	PRIMARY KEY (id_mensaje_error_cargue_acuse)
);

CREATE TABLE campo_archivo_acuse
(
	id_campo_archivo_acuse INTEGER NOT NULL,
	codigo VARCHAR(10),
	nombre VARCHAR(50) NOT NULL,
	sigla VARCHAR(5),
	estado TINYINT NOT NULL,
	descripcion VARCHAR(150),
	PRIMARY KEY (id_campo_archivo_acuse),
	CONSTRAINT CK_campo_archi_acuse_01 CHECK (estado = 0 OR estado = 1)
);

CREATE TABLE error_cargue_acuse
(
	id_error_cargue_acuse BIGINT NOT NULL AUTO_INCREMENT,
	id_campo_archivo_acuse INTEGER NOT NULL,
	id_mensaje_error_cargue_acuse INTEGER NOT NULL,
	id_comparendo_archivo_acuse BIGINT NOT NULL,
	PRIMARY KEY (id_error_cargue_acuse),
	KEY (id_campo_archivo_acuse),
	KEY (id_mensaje_error_cargue_acuse),
	KEY (id_comparendo_archivo_acuse)
);

CREATE TABLE comparendo_archivo_acuse
(
	id_comparendo_archivo_acuse BIGINT NOT NULL AUTO_INCREMENT,
	id_archivo_acuse_correo BIGINT NOT NULL,
	cicomparendo BIGINT,
	numero_comparendo VARCHAR(20) NOT NULL,
	procesado TINYINT NOT NULL,
	PRIMARY KEY (id_comparendo_archivo_acuse),
	KEY (id_archivo_acuse_correo),
	KEY (cicomparendo),
	CONSTRAINT CK_compa_archi_acuse_01 CHECK (procesado = 0 OR procesado = 1)
);

CREATE TABLE archivo_acuse_notificacion
(
	id_archivo_acuse_correo BIGINT NOT NULL AUTO_INCREMENT,
	fecha_cargue DATETIME NOT NULL,
	PRIMARY KEY (id_archivo_acuse_correo)
);

CREATE TABLE notificacion_aviso_comparendo
(
	id_notificacion_aviso BIGINT NOT NULL,
	cicomparendo BIGINT NOT NULL,
	PRIMARY KEY (id_notificacion_aviso, cicomparendo),
	KEY (id_notificacion_aviso),
	KEY (cicomparendo)
);

CREATE TABLE notificacion_aviso
(
	id_notificacion_aviso BIGINT NOT NULL AUTO_INCREMENT,
	codigo_organismo INTEGER NOT NULL,
	fecha_generacion DATETIME NOT NULL,
	fecha_notificacion DATE NOT NULL,
	notificado TINYINT NOT NULL,
	id_archivo_generado BIGINT,
	cantidad_comparendos INTEGER NOT NULL,
	consecutivo VARCHAR(50) NOT NULL,
	fecha_ejecucion_notificacion DATETIME,
	PRIMARY KEY (id_notificacion_aviso),
	CONSTRAINT CK_notif_aviso_01 CHECK (notificado = 0 OR notificado = 1),
	CONSTRAINT CK_notif_aviso_02 CHECK (cantidad_comparendos >= 0)
);

-- ------------------------------------------------------------------------
-- SPRINT 11
CREATE TABLE rectifica_evidencia
(
	id_rectifica_evidencia BIGINT NOT NULL AUTO_INCREMENT,
	id_rectifica_comparendo BIGINT NOT NULL,
	codigo_tipo_evidencia INTEGER NOT NULL,
	id_documento VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_rectifica_evidencia),
	KEY (id_rectifica_comparendo),
	KEY (codigo_tipo_evidencia)
);

-- ------------------------------------------------------------------------
-- SPRINT 12
CREATE TABLE representante_legal
(
	id_representante_legal INTEGER NOT NULL AUTO_INCREMENT,
	id_persona BIGINT NOT NULL,
	id_persona_juridica BIGINT NOT NULL,
	correo_electronico_empresaria VARCHAR(255),
	fecha_inicio_vigencia DATE,
	fecha_fin_vigencia DATE,
	PRIMARY KEY (id_representante_legal),
	KEY (id_persona),
	KEY (id_persona_juridica)
);


SET FOREIGN_KEY_CHECKS=1;

-- ------------------------------------------------------------------------
-- Formularios
-- ------------------------------------------------------------------------
ALTER TABLE causal_cambio_estado ADD CONSTRAINT FK_causa_cambi_estad_01 
	FOREIGN KEY (id_estado_formulario) REFERENCES estado_formulario (id_estado_formulario);

ALTER TABLE unificacion_responsable ADD CONSTRAINT FK_unifi_respo_01 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona);

ALTER TABLE unificacion_responsable ADD CONSTRAINT FK_unifi_respo_02 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE stock_tipo_formulario ADD CONSTRAINT FK_stock_tipo_formu_01 
	FOREIGN KEY (id_tipo_formulario) REFERENCES tipo_formulario (id_tipo_formulario);

ALTER TABLE stock_tipo_formulario ADD CONSTRAINT FK_stock_tipo_formu_02 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE stock_tipo_responsable ADD CONSTRAINT FK_stock_tipo_respo_01 
	FOREIGN KEY (id_tipo_formulario) REFERENCES tipo_formulario (id_tipo_formulario);

ALTER TABLE stock_tipo_responsable ADD CONSTRAINT FK_stock_tipo_respo_02 
	FOREIGN KEY (id_tipo_responsable) REFERENCES tipo_responsable_formulario (id_tipo_responsable);

ALTER TABLE stock_tipo_responsable ADD CONSTRAINT FK_stock_tipo_respo_03 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE seguimiento_formulario ADD CONSTRAINT FK_segui_formu_01 
	FOREIGN KEY (id_formulario) REFERENCES formulario (id_formulario);

ALTER TABLE seguimiento_formulario ADD CONSTRAINT FK_segui_formu_02 
	FOREIGN KEY (id_estado_formulario) REFERENCES estado_formulario (id_estado_formulario);

ALTER TABLE seguimiento_formulario ADD CONSTRAINT FK_segui_formu_03 
	FOREIGN KEY (id_responsable_formulario) REFERENCES responsable_formulario (id_responsable_formulario);

ALTER TABLE seguimiento_formulario ADD CONSTRAINT FK_segui_formu_05 
	FOREIGN KEY (id_detalle_cambio_estado) REFERENCES detalle_cambio_estado (id_detalle_cambio_estado);

ALTER TABLE responsable_formulario ADD CONSTRAINT FK_respo_formu_01 
	FOREIGN KEY (id_tipo_responsable) REFERENCES tipo_responsable_formulario (id_tipo_responsable);

ALTER TABLE responsable_formulario ADD CONSTRAINT FK_respo_formu_02 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE relacion_estados ADD CONSTRAINT FK_relac_estad_01 
	FOREIGN KEY (id_estado_formulario_actual) REFERENCES estado_formulario (id_estado_formulario);

ALTER TABLE relacion_estados ADD CONSTRAINT FK_relac_estad_02 
	FOREIGN KEY (id_estado_formulario_siguiente) REFERENCES estado_formulario (id_estado_formulario);

ALTER TABLE relacion_estados ADD CONSTRAINT FK_relac_estad_03 
	FOREIGN KEY (id_tipo_formulario) REFERENCES tipo_formulario (id_tipo_formulario);

ALTER TABLE relacion_estados ADD CONSTRAINT FK_relac_estad_04 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE rango_formulario ADD CONSTRAINT FK_rango_formu_01 
	FOREIGN KEY (id_numeracion) REFERENCES numeracion_formulario (id_numeracion);

ALTER TABLE rango_formulario ADD CONSTRAINT FK_rango_formu_02 
	FOREIGN KEY (id_tipo_formulario) REFERENCES tipo_formulario (id_tipo_formulario);

ALTER TABLE rango_formulario ADD CONSTRAINT FK_rango_formu_03 
	FOREIGN KEY (id_documento_formulario) REFERENCES documento_formulario (id_documento_formulario);

ALTER TABLE rango_formulario ADD CONSTRAINT FK_rango_formu_04 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE numeracion_formulario ADD CONSTRAINT FK_numer_formu_02 
	FOREIGN KEY (id_tipo_formulario) REFERENCES tipo_formulario (id_tipo_formulario);

ALTER TABLE formulario ADD CONSTRAINT FK_formu_01 
	FOREIGN KEY (id_tipo_formulario) REFERENCES tipo_formulario (id_tipo_formulario);

ALTER TABLE formulario ADD CONSTRAINT FK_formu_02 
	FOREIGN KEY (id_estado_formulario) REFERENCES estado_formulario (id_estado_formulario);

ALTER TABLE formulario ADD CONSTRAINT FK_formu_03 
	FOREIGN KEY (id_detalle_cambio_estado) REFERENCES detalle_cambio_estado (id_detalle_cambio_estado);

ALTER TABLE formulario ADD CONSTRAINT FK_formu_04 
	FOREIGN KEY (id_responsable_formulario) REFERENCES responsable_formulario (id_responsable_formulario);

ALTER TABLE formulario ADD CONSTRAINT FK_formu_05 
	FOREIGN KEY (id_rango_formulario) REFERENCES rango_formulario (id_rango_formulario);

ALTER TABLE formulario ADD CONSTRAINT FK_formu_06 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE detalle_numeracion ADD CONSTRAINT FK_detal_numer_01 
	FOREIGN KEY (id_numeracion) REFERENCES numeracion_formulario (id_numeracion);

ALTER TABLE detalle_cambio_estado ADD CONSTRAINT FK_detal_cambi_estad_01 
	FOREIGN KEY (id_estado_formulario) REFERENCES estado_formulario (id_estado_formulario);

ALTER TABLE detalle_cambio_estado ADD CONSTRAINT FK_detal_cambi_estad_02 
	FOREIGN KEY (id_causal_cambio_estado) REFERENCES causal_cambio_estado (id_causal_cambio_estado);

ALTER TABLE detalle_cambio_estado ADD CONSTRAINT FK_detal_cambi_estad_03 
	FOREIGN KEY (id_documento_formulario) REFERENCES documento_formulario (id_documento_formulario);

ALTER TABLE detalle_cambio_estado ADD CONSTRAINT FK_detal_cambi_estad_04 
	FOREIGN KEY (id_responsable_formulario) REFERENCES responsable_formulario (id_responsable_formulario);

ALTER TABLE detalle_cambio_estado ADD CONSTRAINT FK_detal_cambi_estad_05 
	FOREIGN KEY (id_rango_formulario) REFERENCES rango_formulario (id_rango_formulario);
-- ------------------------------------------------------------------------
-- Mail sender
-- ------------------------------------------------------------------------
ALTER TABLE correo_envio ADD CONSTRAINT FK_correo_envio_01 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE correo_envio ADD CONSTRAINT FK_correo_envio_02 
	FOREIGN KEY (id_configuracion) REFERENCES configuracion_email (id_configuracion);

ALTER TABLE usuario_persona ADD CONSTRAINT FK_usuario_persona 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona);

ALTER TABLE tipo_comunicacion_organismo ADD CONSTRAINT FK_tipo_comun_organ_01 
	FOREIGN KEY (codigo_tipo_email) REFERENCES tipo_email (codigo_tipo_email);

ALTER TABLE tipo_comunicacion_organismo ADD CONSTRAINT FK_tipo_comun_organ_02 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE configuracion_email ADD CONSTRAINT FK_confi_email_01 
	FOREIGN KEY (id_usuario) REFERENCES usuario_persona (id_usuario);

ALTER TABLE configuracion_email ADD CONSTRAINT FK_confi_email_02 
	FOREIGN KEY (codigo_tipo_email) REFERENCES tipo_email (codigo_tipo_email);

ALTER TABLE configuracion_email ADD CONSTRAINT FK_confi_email_03 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);
-- ------------------------------------------------------------------------
-- Dependencias
-- ------------------------------------------------------------------------
ALTER TABLE departamento ADD CONSTRAINT FK_depar_01 
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais);

ALTER TABLE localidad ADD CONSTRAINT FK_local_01 
	FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio);

ALTER TABLE municipio ADD CONSTRAINT FK_munic_01 
	FOREIGN KEY (id_departamento) REFERENCES departamento (id_departamento);

ALTER TABLE organismo_transito ADD CONSTRAINT FK_organ_trans_01 
	FOREIGN KEY (id_departamento) REFERENCES departamento (id_departamento);

ALTER TABLE organismo_transito ADD CONSTRAINT FK_organ_trans_02 
	FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio);

ALTER TABLE organismo_transito ADD CONSTRAINT FK_organ_trans_03 
	FOREIGN KEY (codigo_organismo_padre) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE cardinalidad_direccion ADD CONSTRAINT FK_cardi_direc_01 
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_01 
	FOREIGN KEY (codigo_cardinalidad_via_princ) REFERENCES cardinalidad_direccion (codigo_cardinalidad);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_02 
	FOREIGN KEY (codigo_nombre_via_principal) REFERENCES nombre_via (codigo_nombre_via);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_03 
	FOREIGN KEY (codigo_cardinalidad_via_secun) REFERENCES cardinalidad_direccion (codigo_cardinalidad);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_04 
	FOREIGN KEY (id_localidad) REFERENCES localidad (id_localidad);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_05 
	FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_06 
	FOREIGN KEY (codigo_nombre_via_secundaria) REFERENCES nombre_via (codigo_nombre_via);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_07 
	FOREIGN KEY (codigo_tipo_via_principal) REFERENCES tipo_via (codigo_tipo_via);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_08 
	FOREIGN KEY (codigo_tipo_via_secundaria) REFERENCES tipo_via (codigo_tipo_via);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_09 
	FOREIGN KEY (codigo_tipo_coordenada) REFERENCES tipo_coordenada (codigo_tipo_coordenada);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_10 
	FOREIGN KEY (codigo_tipo_ubicabilidad) REFERENCES tipo_ubicabilidad (codigo_tipo_ubicabilidad);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_11 
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais);

ALTER TABLE direccion ADD CONSTRAINT FK_direc_12 
	FOREIGN KEY (id_departamento) REFERENCES departamento (id_departamento);

ALTER TABLE nombre_via ADD CONSTRAINT FK_nombr_via_01 
	FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio);

ALTER TABLE nombre_via ADD CONSTRAINT FK_nombr_via_02 
	FOREIGN KEY (codigo_tipo_via) REFERENCES tipo_via (codigo_tipo_via);

ALTER TABLE tipo_via ADD CONSTRAINT FK_tipo_via_01 
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais);

ALTER TABLE nivel_educativo ADD CONSTRAINT FK_nivel_educa_01 
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais);

ALTER TABLE tipo_vivienda ADD CONSTRAINT FK_tipo_vivie_01 
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais);
	
ALTER TABLE persona ADD CONSTRAINT FK_perso_02 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE persona ADD CONSTRAINT FK_perso_07 
	FOREIGN KEY (id_estado_civil) REFERENCES estado_civil (id_estado_civil);

ALTER TABLE persona ADD CONSTRAINT FK_perso_04 
	FOREIGN KEY (codigo_nivel_educativo) REFERENCES nivel_educativo (codigo_nivel_educativo);

ALTER TABLE persona ADD CONSTRAINT FK_perso_01 
	FOREIGN KEY (codigo_tipo_vivienda) REFERENCES tipo_vivienda (codigo_tipo_vivienda);

ALTER TABLE persona ADD CONSTRAINT FK_perso_06 
	FOREIGN KEY (id_municipio_expedicion_docum) REFERENCES municipio (id_municipio);

ALTER TABLE persona ADD CONSTRAINT FK_perso_05 
	FOREIGN KEY (codigo_fuente_informacion) REFERENCES tipo_fuente_informacion (codigo_fuente_informacion);

ALTER TABLE persona ADD CONSTRAINT FK_perso_03 
	FOREIGN KEY (id_tipo_identificacion) REFERENCES tipo_identificacion_persona (id_tipo_identificacion);

ALTER TABLE persona_juridica ADD CONSTRAINT FK_perso_jurid_01 
	FOREIGN KEY (id_tipo_sociedad) REFERENCES tipo_sociedad (id_tipo_sociedad);

ALTER TABLE persona_juridica ADD CONSTRAINT FK_perso_jurid_02 
	FOREIGN KEY (id_persona_juridica) REFERENCES persona (id_persona);

ALTER TABLE persona_juridica ADD CONSTRAINT FK_perso_jurid_03 
	FOREIGN KEY (id_clase_actividad_economica) REFERENCES clase_actividad_economica (id_clase_actividad_economica);

ALTER TABLE persona_juridica ADD CONSTRAINT FK_perso_jurid_04 
	FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio);

ALTER TABLE clase_actividad_economica ADD CONSTRAINT FK_clase_activ_econo_01 
	FOREIGN KEY (id_grupo_actividad_economica) REFERENCES grupo_actividad_economica (id_grupo_actividad_economica);

ALTER TABLE division_actividad_economica ADD CONSTRAINT FK_divis_activ_01 
	FOREIGN KEY (id_seccion_actividad_econo) REFERENCES seccion_actividad_economica (id_seccion_actividad_econo);

ALTER TABLE grupo_actividad_economica ADD CONSTRAINT FK_grupo_activ_01 
	FOREIGN KEY (id_division_actividad_econo) REFERENCES division_actividad_economica (id_division_actividad_econo);

ALTER TABLE seccion_actividad_economica ADD CONSTRAINT FK_secci_activ_econo_01 
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais);

ALTER TABLE tipo_sociedad ADD CONSTRAINT FK_tipo_socie_01 
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais);

ALTER TABLE direccion_organismo ADD CONSTRAINT FK_direc_organ_01 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE direccion_organismo ADD CONSTRAINT FK_direc_organ_02 
	FOREIGN KEY (id_direccion) REFERENCES direccion (id_direccion);

ALTER TABLE direccion_persona ADD CONSTRAINT FK_direc_perso_01 
	FOREIGN KEY (codigo_fuente_informacion) REFERENCES tipo_fuente_informacion (codigo_fuente_informacion);

ALTER TABLE direccion_persona ADD CONSTRAINT FK_direc_perso_02 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona);

ALTER TABLE direccion_persona ADD CONSTRAINT FK_direc_perso_03 
	FOREIGN KEY (id_direccion) REFERENCES direccion (id_direccion);

ALTER TABLE parentesco_persona ADD CONSTRAINT FK_paren_perso_01 
	FOREIGN KEY (id_tipo_parentesco_persona) REFERENCES tipo_parentesco_persona (id_tipo_parentesco_persona);

ALTER TABLE parentesco_persona ADD CONSTRAINT FK_paren_perso_02 
	FOREIGN KEY (id_persona_1) REFERENCES persona (id_persona);

ALTER TABLE parentesco_persona ADD CONSTRAINT FK_paren_perso_03 
	FOREIGN KEY (id_persona_2) REFERENCES persona (id_persona);

ALTER TABLE persona_historico ADD CONSTRAINT FK_perso_histo_07 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE persona_historico ADD CONSTRAINT FK_perso_histo_09 
	FOREIGN KEY (id_clase_actividad_economica) REFERENCES clase_actividad_economica (id_clase_actividad_economica);

ALTER TABLE persona_historico ADD CONSTRAINT FK_perso_histo_10 
	FOREIGN KEY (id_estado_civil) REFERENCES estado_civil (id_estado_civil);

ALTER TABLE persona_historico ADD CONSTRAINT FK_perso_histo_01 
	FOREIGN KEY (id_tipo_identificacion) REFERENCES tipo_identificacion_persona (id_tipo_identificacion);

ALTER TABLE persona_historico ADD CONSTRAINT FK_perso_histo_02 
	FOREIGN KEY (id_tipo_sociedad) REFERENCES tipo_sociedad (id_tipo_sociedad);

ALTER TABLE persona_historico ADD CONSTRAINT FK_perso_histo_03 
	FOREIGN KEY (codigo_fuente_informacion) REFERENCES tipo_fuente_informacion (codigo_fuente_informacion);

ALTER TABLE persona_historico ADD CONSTRAINT FK_perso_histo_04 
	FOREIGN KEY (codigo_tipo_vivienda) REFERENCES tipo_vivienda (codigo_tipo_vivienda);

ALTER TABLE persona_historico ADD CONSTRAINT FK_perso_histo_05 
	FOREIGN KEY (codigo_nivel_educativo) REFERENCES nivel_educativo (codigo_nivel_educativo);

ALTER TABLE persona_historico ADD CONSTRAINT FK_perso_histo_06 
	FOREIGN KEY (id_municipio_expedicion_docum) REFERENCES municipio (id_municipio);

ALTER TABLE persona_historico ADD CONSTRAINT FK_perso_histo_11 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona);
	
ALTER TABLE estado_civil ADD CONSTRAINT FK_estad_civil_01 
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais);

--------------------------------------------------------------------------
-- General de sistema
--------------------------------------------------------------------------

ALTER TABLE catalogo ADD CONSTRAINT FK_catal_01 FOREIGN KEY (id_catalogo_dependencia) REFERENCES catalogo (id_catalogo);

ALTER TABLE parametro ADD CONSTRAINT FK_param_01 FOREIGN KEY (codigo_tipo_variable) REFERENCES tipo_variable (codigo_tipo_variable);

ALTER TABLE parametro ADD CONSTRAINT FK_param_02 FOREIGN KEY (codigo_modulo) REFERENCES modulo (codigo_modulo);

ALTER TABLE parametro_organismo ADD CONSTRAINT FK_param_organ_02 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE parametro_organismo ADD CONSTRAINT FK_param_organ_01 
	FOREIGN KEY (codigo_parametro) REFERENCES parametro (codigo_parametro);

ALTER TABLE proceso ADD CONSTRAINT FK_proce_01 
	FOREIGN KEY (codigo_modulo) REFERENCES modulo (codigo_modulo);

ALTER TABLE proceso ADD CONSTRAINT FK_proce_02 
	FOREIGN KEY (codigo_proceso_padre) REFERENCES proceso (codigo_proceso);

-- -------------------------------------------------------------------------------------
-- comparendos
-- -------------------------------------------------------------------------------------
ALTER TABLE tipo_infractor_confi_infra ADD CONSTRAINT FK_tipo_infra_confi_infra_01 
	FOREIGN KEY (id_configuracion_infraccion) REFERENCES configuracion_infraccion (id_configuracion_infraccion);

ALTER TABLE tipo_infractor_confi_infra ADD CONSTRAINT FK_tipo_infra_confi_infra_02 
	FOREIGN KEY (codigo_tipo_infractor) REFERENCES tipo_infractor (codigo_tipo_infractor);

ALTER TABLE tipo_entidad_agente_transito ADD CONSTRAINT FK_tipo_entid_agent_trans_01 
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais);

ALTER TABLE tipo_sancion_confi_infra ADD CONSTRAINT FK_tipo_sanci_confi_infra_01 
	FOREIGN KEY (id_configuracion_infraccion) REFERENCES configuracion_infraccion (id_configuracion_infraccion);

ALTER TABLE tipo_sancion_confi_infra ADD CONSTRAINT FK_tipo_sanci_confi_infra_02 
	FOREIGN KEY (id_tipo_sancion) REFERENCES tipo_sancion (id_tipo_sancion);

ALTER TABLE normatividad ADD CONSTRAINT FK_norma_01 
	FOREIGN KEY (id_ordenamiento_pais) REFERENCES ordenamiento_pais (id_ordenamiento_pais);

ALTER TABLE normatividad_organismo ADD CONSTRAINT FK_norma_organ_01 
	FOREIGN KEY (id_normatividad) REFERENCES normatividad (id_normatividad);

ALTER TABLE normatividad_organismo ADD CONSTRAINT FK_norma_organ_02 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE configuracion_infraccion ADD CONSTRAINT FK_confi_infra_01 
	FOREIGN KEY (id_infraccion) REFERENCES infraccion (id_infraccion);

ALTER TABLE configuracion_infraccion ADD CONSTRAINT FK_confi_infra_02 
	FOREIGN KEY (id_normatividad) REFERENCES normatividad (id_normatividad);

ALTER TABLE configuracion_infraccion ADD CONSTRAINT FK_confi_infra_04 
	FOREIGN KEY (id_entidad_agente) REFERENCES tipo_entidad_agente_transito (id_entidad_agente);

ALTER TABLE configuracion_infraccion ADD CONSTRAINT FK_confi_infra_05 
	FOREIGN KEY (id_causal_infraccion) REFERENCES causal_infraccion (id_causal_infraccion);

ALTER TABLE configuracion_infraccion ADD CONSTRAINT FK_confi_infra_06 
	FOREIGN KEY (id_ordenamiento_pais) REFERENCES ordenamiento_pais (id_ordenamiento_pais);

ALTER TABLE infraccion_tipo_respo_solid ADD CONSTRAINT FK_infra_tipo_respo_solid_01 
	FOREIGN KEY (id_configuracion_infraccion) REFERENCES configuracion_infraccion (id_configuracion_infraccion);

ALTER TABLE infraccion_tipo_respo_solid ADD CONSTRAINT FK_infra_tipo_respo_solid_02 
	FOREIGN KEY (codigo_responsable) REFERENCES tipo_responsable_solidario (codigo_responsable);

ALTER TABLE comparendo_cartera ADD CONSTRAINT FK_compa_carte_01 FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);
	
ALTER TABLE comparendo_cartera ADD CONSTRAINT FK_compa_carte_02 FOREIGN KEY (id_cartera) REFERENCES cartera (id_cartera);	

ALTER TABLE comparendo_patio ADD CONSTRAINT FK_compa_patio_01 
	FOREIGN KEY (id_comparendo_patio) REFERENCES comparendo (cicomparendo);

ALTER TABLE comparendo_patio ADD CONSTRAINT FK_compa_patio_02 
	FOREIGN KEY (id_patio) REFERENCES patio (id_patio);

ALTER TABLE comparendo_patio ADD CONSTRAINT FK_compa_patio_03
	FOREIGN KEY (id_direccion) REFERENCES direccion (id_direccion);

ALTER TABLE agente ADD CONSTRAINT FK_agent_01 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE agente ADD CONSTRAINT FK_agent_02 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona);

ALTER TABLE agente ADD CONSTRAINT FK_agent_03 
	FOREIGN KEY (id_entidad_agente_transito) REFERENCES tipo_entidad_agente_transito (id_entidad_agente);

ALTER TABLE comparendo_agente ADD CONSTRAINT FK_compa_agent_01 
	FOREIGN KEY (id_comparendo_agente) REFERENCES comparendo (cicomparendo);

ALTER TABLE comparendo_agente ADD CONSTRAINT FK_compa_agent_02 
	FOREIGN KEY (id_tipo_identificacion) REFERENCES tipo_identificacion_persona (id_tipo_identificacion);

ALTER TABLE comparendo_agente ADD CONSTRAINT FK_compa_agent_03 
	FOREIGN KEY (id_unificacion_responsable) REFERENCES unificacion_responsable (id_unificacion_responsable);

ALTER TABLE comparendo_agente ADD CONSTRAINT FK_compa_agent_04 
	FOREIGN KEY (id_agente_transito) REFERENCES agente (id_agente_transito);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_01 
	FOREIGN KEY (id_comparendo_vehiculo) REFERENCES comparendo (cicomparendo);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_02 
	FOREIGN KEY (id_tipo_servicio) REFERENCES tipo_servicio (id_tipo_servicio);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_03 
	FOREIGN KEY (id_radio_accion) REFERENCES radio_accion (id_radio_accion);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_04 
	FOREIGN KEY (id_modalidad) REFERENCES modalidad (id_modalidad);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_05 
	FOREIGN KEY (id_clase_vehiculo) REFERENCES clase_vehiculo (id_clase_vehiculo);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_06 
	FOREIGN KEY (id_nivel_servicio) REFERENCES nivel_servicio (id_nivel_servicio);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_07 
	FOREIGN KEY (id_marca_vehiculo) REFERENCES marca_vehiculo (id_marca_vehiculo);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_08 
	FOREIGN KEY (id_linea_vehiculo) REFERENCES linea_vehiculo (id_linea_vehiculo);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_09 
	FOREIGN KEY (id_color) REFERENCES color (id_color);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_10 
	FOREIGN KEY (codigo_tipo_transporte_pasaj) REFERENCES tipo_transporte_pasajero (codigo_tipo_transporte_pasaj);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_11 
	FOREIGN KEY (codigo_organismo_matri_vehic) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE comparendo_vehiculo ADD CONSTRAINT FK_compa_vehic_12 
	FOREIGN KEY (codigo_organismo_licen_trans) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE empresa_transporte ADD CONSTRAINT FK_empre_trans_01 
	FOREIGN KEY (id_empresa_transporte) REFERENCES persona_juridica (id_persona_juridica);

ALTER TABLE comparendo_persona ADD CONSTRAINT FK_compa_perso_08 
	FOREIGN KEY (id_licencia) REFERENCES licencia_conduccion (id_licencia);

ALTER TABLE comparendo_persona ADD CONSTRAINT FK_compa_perso_01 
	FOREIGN KEY (codigo_tipo_persona_comparendo) REFERENCES tipo_persona_comparendo (codigo_tipo_persona_comparendo);

ALTER TABLE comparendo_persona ADD CONSTRAINT FK_compa_perso_02 
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE comparendo_persona ADD CONSTRAINT FK_compa_perso_03 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona);

ALTER TABLE comparendo_persona ADD CONSTRAINT FK_compa_perso_04 
	FOREIGN KEY (id_tipo_identificacion) REFERENCES tipo_identificacion_persona (id_tipo_identificacion);

ALTER TABLE comparendo_persona ADD CONSTRAINT FK_compa_perso_05 
	FOREIGN KEY (id_direccion) REFERENCES direccion (id_direccion);

ALTER TABLE comparendo_persona ADD CONSTRAINT FK_compa_perso_06 
	FOREIGN KEY (id_categoria_licencia_condu) REFERENCES tipo_categ_licen_condu (id_categoria_licencia_conduc);

ALTER TABLE comparendo_persona ADD CONSTRAINT FK_compa_perso_07 
	FOREIGN KEY (codigo_organismo_licencia) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE orden_comparendo_nacional ADD CONSTRAINT FK_orden_compa_nacio_01 
	FOREIGN KEY (codigo_organismo_transito) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE procesa_evidencia ADD CONSTRAINT FK_proce_evide_01 
	FOREIGN KEY (id_procesa_comparendo) REFERENCES procesa_comparendo (id_procesamiento);

ALTER TABLE procesa_comparendo_persona ADD CONSTRAINT FK_proce_compa_perso_01 
	FOREIGN KEY (id_procesamiento) REFERENCES procesa_comparendo (id_procesamiento);

ALTER TABLE procesa_comparendo_persona ADD CONSTRAINT FK_proce_compa_perso_02 
	FOREIGN KEY (id_procesa_direccion) REFERENCES procesa_direccion (id_procesa_direccion);

ALTER TABLE procesa_comparendo ADD CONSTRAINT FK_proce_compa_01 
	FOREIGN KEY (codigo_organismo_transito) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE procesa_comparendo ADD CONSTRAINT FK_proce_compa_02 
	FOREIGN KEY (id_procesa_direccion_compa) REFERENCES procesa_direccion (id_procesa_direccion);

ALTER TABLE procesa_comparendo ADD CONSTRAINT FK_proce_compa_03 
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE procesa_comparendo ADD CONSTRAINT FK_proce_compa_04 
	FOREIGN KEY (id_usuario) REFERENCES usuario_persona (id_usuario);

ALTER TABLE procesa_comparendo ADD CONSTRAINT FK_proce_compa_05
	FOREIGN KEY (id_procesa_direccion_patio) REFERENCES procesa_direccion (id_procesa_direccion);
	
ALTER TABLE grado_alcoholemia ADD CONSTRAINT FK_grado_alcoh_01 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE evidencia ADD CONSTRAINT FK_evide_01 
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE evidencia ADD CONSTRAINT FK_evide_02 
	FOREIGN KEY (codigo_tipo_evidencia) REFERENCES tipo_evidencia (codigo_tipo_evidencia);

ALTER TABLE nivel_servicio ADD CONSTRAINT FK_nivel_servi_01 
	FOREIGN KEY (id_pais) REFERENCES pais (id_pais);

ALTER TABLE detalle_procesamiento ADD CONSTRAINT FK_detal_proce_01 
	FOREIGN KEY (id_error_procesamiento) REFERENCES error_procesamiento (id_error_procesamiento);

ALTER TABLE detalle_procesamiento ADD CONSTRAINT FK_detal_proce_02 
	FOREIGN KEY (id_procesamiento) REFERENCES procesa_comparendo (id_procesamiento);

ALTER TABLE detalle_procesamiento ADD CONSTRAINT FK_detal_proce_03 
	FOREIGN KEY (codigo_campo) REFERENCES campo_entidad (codigo_campo);

ALTER TABLE ruta ADD CONSTRAINT FK_ruta_02 
	FOREIGN KEY (id_empresa_transporte) REFERENCES empresa_transporte (id_empresa_transporte);

ALTER TABLE trazabilidad_comparendo ADD CONSTRAINT FK_traza_compa_01 
	FOREIGN KEY (id_actividad) REFERENCES actividad (id_actividad);

ALTER TABLE trazabilidad_comparendo ADD CONSTRAINT FK_traza_compa_02 
	FOREIGN KEY (id_usuario) REFERENCES usuario_persona (id_usuario);

ALTER TABLE trazabilidad_comparendo ADD CONSTRAINT FK_traza_compa_03 
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE trazabilidad_comparendo ADD CONSTRAINT FK_traza_compa_04 
	FOREIGN KEY (id_estado_comparendo) REFERENCES estado_comparendo (id_estado_comparendo);

ALTER TABLE infraccion ADD CONSTRAINT FK_infra_01
	FOREIGN KEY (id_tipo_comparendo) REFERENCES tipo_comparendo (id_tipo_comparendo);

ALTER TABLE infraccion ADD CONSTRAINT FK_infra_03 
	FOREIGN KEY (id_tipo_infraccion) REFERENCES tipo_infraccion (id_tipo_infraccion);

ALTER TABLE comparendo ADD CONSTRAINT FK_compa_01 
	FOREIGN KEY (id_tipo_comparendo) REFERENCES tipo_comparendo (id_tipo_comparendo);

ALTER TABLE comparendo ADD CONSTRAINT FK_compa_02 
	FOREIGN KEY (id_infraccion) REFERENCES infraccion (id_infraccion);

ALTER TABLE comparendo ADD CONSTRAINT FK_compa_03 
	FOREIGN KEY (id_direccion_comparendo) REFERENCES direccion (id_direccion);

ALTER TABLE comparendo ADD CONSTRAINT FK_compa_04 
	FOREIGN KEY (codigo_medio_imposicion) REFERENCES medio_imposicion_comparendo (codigo_medio_imposicion);

ALTER TABLE comparendo ADD CONSTRAINT FK_compa_05 
	FOREIGN KEY (codigo_origen) REFERENCES tipo_origen_comparendo (codigo_origen);

ALTER TABLE comparendo ADD CONSTRAINT FK_compa_07 
	FOREIGN KEY (codigo_tipo_infractor) REFERENCES tipo_infractor (codigo_tipo_infractor);

ALTER TABLE comparendo ADD CONSTRAINT FK_compa_08 
	FOREIGN KEY (id_usuario) REFERENCES usuario_persona (id_usuario);

ALTER TABLE comparendo ADD CONSTRAINT FK_compa_09 
	FOREIGN KEY (id_ruta) REFERENCES ruta (id_ruta);

ALTER TABLE comparendo ADD CONSTRAINT FK_compa_10 
	FOREIGN KEY (id_estado_comparendo) REFERENCES estado_comparendo (id_estado_comparendo);

ALTER TABLE comparendo ADD CONSTRAINT FK_compa_12 
	FOREIGN KEY (id_tipo_notificacion_compa) REFERENCES tipo_notificacion_comparendo (id_tipo_notificacion);
ALTER TABLE comparendo ADD CONSTRAINT FK_compa_15 
	FOREIGN KEY (id_grado_alcoholemia) REFERENCES grado_alcoholemia (id_grado_alcoholemia);

ALTER TABLE comparendo ADD CONSTRAINT FK_compa_17 
	FOREIGN KEY (cicomparendo) REFERENCES orden_comparendo_nacional (cicomparendo);

-- configuraciones
ALTER TABLE valor_configuracion ADD CONSTRAINT FK_valor_confi_01 FOREIGN KEY (id_configuracion) REFERENCES configuracion (id_configuracion);

-- dependencias
-- patios
ALTER TABLE patio ADD CONSTRAINT FK_patio_01 FOREIGN KEY (id_direccion_patio) REFERENCES direccion (id_direccion);
ALTER TABLE patio ADD CONSTRAINT FK_patio_02 FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

-- registro vehicular
ALTER TABLE linea_vehiculo ADD CONSTRAINT FK_linea_vehic_01 FOREIGN KEY (id_marca_vehiculo) REFERENCES marca_vehiculo (id_marca_vehiculo);

ALTER TABLE licencia_conduccion ADD CONSTRAINT FK_licen_condu_01 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE licencia_conduccion ADD CONSTRAINT FK_licen_condu_02 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona);

ALTER TABLE licencia_conduccion ADD CONSTRAINT FK_licen_condu_03 
	FOREIGN KEY (id_estado_licencia) REFERENCES estado_licencia (id_estado_licencia);

ALTER TABLE licencia_conduccion ADD CONSTRAINT FK_licen_condu_04 
	FOREIGN KEY (id_categoria_licencia_conduc) REFERENCES tipo_categ_licen_condu (id_categoria_licencia_conduc);

ALTER TABLE empresa_vehiculo ADD CONSTRAINT FK_empre_vehic_01 
	FOREIGN KEY (id_persona_juridica) REFERENCES persona_juridica (id_persona_juridica);

ALTER TABLE empresa_vehiculo ADD CONSTRAINT FK_empre_vehic_02 
	FOREIGN KEY (id_vehiculo) REFERENCES vehiculo (id_vehiculo);

ALTER TABLE historico_vehiculo ADD CONSTRAINT FK_histo_vehic_01 
	FOREIGN KEY (id_vehiculo) REFERENCES vehiculo (id_vehiculo);

ALTER TABLE historico_vehiculo ADD CONSTRAINT FK_histo_vehic_02 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE historico_vehiculo ADD CONSTRAINT FK_histo_vehic_03 
	FOREIGN KEY (id_clase_vehiculo) REFERENCES clase_vehiculo (id_clase_vehiculo);

ALTER TABLE historico_vehiculo ADD CONSTRAINT FK_histo_vehic_04 
	FOREIGN KEY (id_tipo_servicio) REFERENCES tipo_servicio (id_tipo_servicio);

ALTER TABLE historico_vehiculo ADD CONSTRAINT FK_histo_vehic_05 
	FOREIGN KEY (id_radio_accion) REFERENCES radio_accion (id_radio_accion);

ALTER TABLE historico_vehiculo ADD CONSTRAINT FK_histo_vehic_06 
	FOREIGN KEY (id_modalidad) REFERENCES modalidad (id_modalidad);

ALTER TABLE historico_vehiculo ADD CONSTRAINT FK_histo_vehic_07 
	FOREIGN KEY (id_color) REFERENCES color (id_color);

ALTER TABLE propietario_vehiculo ADD CONSTRAINT FK_propi_vehic_01 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona);

ALTER TABLE propietario_vehiculo ADD CONSTRAINT FK_propi_vehic_02 
	FOREIGN KEY (id_vehiculo) REFERENCES vehiculo (id_vehiculo);

ALTER TABLE propietario_vehiculo ADD CONSTRAINT FK_propi_vehic_03 
	FOREIGN KEY (id_tipo_propietario) REFERENCES tipo_propietario (id_tipo_propietario);

ALTER TABLE seguimiento_estado_licencia ADD CONSTRAINT FK_segui_estad_licen_01 
	FOREIGN KEY (id_licencia) REFERENCES licencia_conduccion (id_licencia);

ALTER TABLE seguimiento_estado_licencia ADD CONSTRAINT FK_segui_estad_licen_02 
	FOREIGN KEY (id_estado_licencia) REFERENCES estado_licencia (id_estado_licencia);

ALTER TABLE vehiculo ADD CONSTRAINT FK_vehic_01 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE vehiculo ADD CONSTRAINT FK_vehic_02 
	FOREIGN KEY (id_clase_vehiculo) REFERENCES clase_vehiculo (id_clase_vehiculo);

ALTER TABLE vehiculo ADD CONSTRAINT FK_vehic_03 
	FOREIGN KEY (id_tipo_servicio) REFERENCES tipo_servicio (id_tipo_servicio);

ALTER TABLE vehiculo ADD CONSTRAINT FK_vehic_04 
	FOREIGN KEY (id_radio_accion) REFERENCES radio_accion (id_radio_accion);

ALTER TABLE vehiculo ADD CONSTRAINT FK_vehic_05 
	FOREIGN KEY (id_modalidad) REFERENCES modalidad (id_modalidad);

ALTER TABLE vehiculo ADD CONSTRAINT FK_vehic_06 
	FOREIGN KEY (id_linea_vehiculo) REFERENCES linea_vehiculo (id_linea_vehiculo);

ALTER TABLE vehiculo ADD CONSTRAINT FK_vehic_07 
	FOREIGN KEY (id_color) REFERENCES color (id_color);
-- administracion
ALTER TABLE campo_entidad ADD CONSTRAINT FK_campo_entid_01 
	FOREIGN KEY (codigo_entidad) REFERENCES entidad (codigo_entidad);
	
ALTER TABLE actividad ADD CONSTRAINT FK_activ_01 
	FOREIGN KEY (codigo_proceso) REFERENCES proceso (codigo_proceso);
	
ALTER TABLE dia_no_habil ADD CONSTRAINT FK_dia_no_habil_01
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

-- CARTERA
ALTER TABLE log_afectar_cartera ADD CONSTRAINT FK_log_afect_carte_01 
	FOREIGN KEY (id_usuario) REFERENCES usuario_persona (id_usuario);

ALTER TABLE log_afectar_cartera ADD CONSTRAINT FK_log_afect_carte_02 
	FOREIGN KEY (id_concepto) REFERENCES concepto_cartera (id_concepto);

ALTER TABLE log_afectar_cartera ADD CONSTRAINT FK_log_afect_carte_03 
	FOREIGN KEY (id_cartera) REFERENCES cartera (id_cartera);

ALTER TABLE trazabilidad_cartera ADD CONSTRAINT FK_traza_carte_01 
	FOREIGN KEY (codigo_estado_obligacion) REFERENCES estado_obligacion (codigo_estado_obligacion);

ALTER TABLE trazabilidad_cartera ADD CONSTRAINT FK_traza_carte_02 
	FOREIGN KEY (id_cartera) REFERENCES cartera (id_cartera);

ALTER TABLE trazabilidad_cartera ADD CONSTRAINT FK_traza_carte_03 
	FOREIGN KEY (id_actividad_cartera) REFERENCES actividad_cartera (id_actividad_cartera);

ALTER TABLE saldo_cartera ADD CONSTRAINT FK_saldo_carte_01 
	FOREIGN KEY (id_cartera) REFERENCES cartera (id_cartera);

ALTER TABLE saldo_cartera ADD CONSTRAINT FK_saldo_carte_02 
	FOREIGN KEY (id_tipo_saldo) REFERENCES tipo_saldo (id_tipo_saldo);

ALTER TABLE cartera ADD CONSTRAINT FK_carte_01 
	FOREIGN KEY (id_deudor) REFERENCES persona (id_persona);

ALTER TABLE cartera ADD CONSTRAINT FK_carte_02 
	FOREIGN KEY (codigo_estado_obligacion) REFERENCES estado_obligacion (codigo_estado_obligacion);

ALTER TABLE cartera ADD CONSTRAINT FK_carte_03 
	FOREIGN KEY (codigo_tipo_obligacion) REFERENCES tipo_obligacion (codigo_tipo_obligacion);

ALTER TABLE movimientos_cartera ADD CONSTRAINT FK_movim_carte_02 
	FOREIGN KEY (id_concepto) REFERENCES concepto_cartera (id_concepto);

ALTER TABLE movimientos_cartera ADD CONSTRAINT FK_movim_carte_03 
	FOREIGN KEY (id_cartera) REFERENCES cartera (id_cartera);

ALTER TABLE concepto_cartera ADD CONSTRAINT FK_conce_carte_01 
	FOREIGN KEY (id_tipo_saldo) REFERENCES tipo_saldo (id_tipo_saldo);

ALTER TABLE concepto_cartera ADD CONSTRAINT FK_conce_carte_02 
	FOREIGN KEY (codigo_tipo_concepto) REFERENCES tipo_concepto_cartera (codigo_tipo_concepto);
	
-- SPRINT 05
ALTER TABLE tarifa_infraccion ADD CONSTRAINT FK_tarif_infra_01 
	FOREIGN KEY (id_infraccion) REFERENCES infraccion (id_infraccion);

-- SPRINT 06

-- SPRINT 07
ALTER TABLE comparendo ADD CONSTRAINT FK_compa_06 
	FOREIGN KEY (id_estado_comparendo_simit) REFERENCES estado_comparendo_simit (id_estado_comparendo_simit);

ALTER TABLE notificacion_simit ADD CONSTRAINT FK_notif_simit_01 
	FOREIGN KEY (id_tipo_documento_envio_simit) REFERENCES tipo_documento_envio_simit (id_tipo_documento_envio_simit);

ALTER TABLE notificacion_simit ADD CONSTRAINT FK_notif_simit_02 
	FOREIGN KEY (id_tipo_resultado_envio_simit) REFERENCES tipo_resultado_envio_simit (id_tipo_resultado_envio_simit);
	
ALTER TABLE notificacion_simit ADD CONSTRAINT FK_notif_simit_03 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE archivo_notificacion_simit ADD CONSTRAINT FK_archi_notif_simit_01 
	FOREIGN KEY (id_notificacion_simit) REFERENCES notificacion_simit (id_notificacion_simit);

ALTER TABLE valor_homologacion ADD CONSTRAINT FK_valor_homol_01 
	FOREIGN KEY (id_tipo_homologacion) REFERENCES tipo_homologacion (id_tipo_homologacion);
	
ALTER TABLE tipo_homologacion ADD CONSTRAINT FK_tipo_homol_01 
	FOREIGN KEY (id_servicio_homologacion) REFERENCES servicio_homologacion (id_servicio_homologacion);

ALTER TABLE interes ADD CONSTRAINT FK_inter_01
	FOREIGN KEY (id_clase_interes) REFERENCES clase_interes (id_clase_interes);

ALTER TABLE interes ADD CONSTRAINT FK_inter_02 
	FOREIGN KEY (id_periodo_interes) REFERENCES periodo_interes (id_periodo_interes);

ALTER TABLE consecutivo ADD CONSTRAINT FK_conse_01
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE consecutivo ADD CONSTRAINT FK_conse_02
	FOREIGN KEY (id_tipo_consecutivo) REFERENCES tipo_consecutivo (id_tipo_consecutivo);

-- SPRINT 08
ALTER TABLE detalle_descuento ADD CONSTRAINT FK_detal_descu_02 
	FOREIGN KEY (id_descuento) REFERENCES configuracion_descuento (id_descuento);

ALTER TABLE detalle_descuento ADD CONSTRAINT FK_detal_descu_03 
	FOREIGN KEY (id_concepto) REFERENCES concepto_cartera (id_concepto);

ALTER TABLE descuento_notificacion ADD CONSTRAINT FK_descu_notif_01 
	FOREIGN KEY (id_descuento) REFERENCES configuracion_descuento (id_descuento);

ALTER TABLE descuento_notificacion ADD CONSTRAINT FK_descu_notif_02 
	FOREIGN KEY (id_tipo_notificacion) REFERENCES tipo_notificacion_comparendo (id_tipo_notificacion);

ALTER TABLE descuento_medio_imposicion ADD CONSTRAINT FK_descu_medio_impos_01 
	FOREIGN KEY (id_descuento) REFERENCES configuracion_descuento (id_descuento);

ALTER TABLE descuento_medio_imposicion ADD CONSTRAINT FK_descu_medio_impos_02 
	FOREIGN KEY (codigo_medio_imposicion) REFERENCES medio_imposicion_comparendo (codigo_medio_imposicion);

ALTER TABLE configuracion_descuento ADD CONSTRAINT FK_confi_descu_01 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE configuracion_descuento ADD CONSTRAINT FK_confi_descu_03 
	FOREIGN KEY (codigo_fecha_origen) REFERENCES tipo_fecha_origen (codigo_fecha_origen);

ALTER TABLE configuracion_descuento ADD CONSTRAINT FK_confi_descu_02 
	FOREIGN KEY (codigo_tipo_descuento) REFERENCES tipo_descuento (codigo_tipo_descuento);

-- SPRINT 09
ALTER TABLE campo_rectifica_comparendo ADD CONSTRAINT FK_campo_recti_compa_01 
	FOREIGN KEY (id_rectifica_comparendo) REFERENCES rectifica_comparendo (id_rectifica_comparendo);

ALTER TABLE campo_rectifica_comparendo ADD CONSTRAINT FK_campo_recti_compa_02 
	FOREIGN KEY (codigo_campo) REFERENCES campo_entidad (codigo_campo);

ALTER TABLE rectifica_comparendo ADD CONSTRAINT FK_recti_compa_01 
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE notificacion_correo_comparendo ADD CONSTRAINT FK_notif_corre_compa_01 
	FOREIGN KEY (id_notificacion_correo) REFERENCES notificacion_correo (id_notificacion_correo);

ALTER TABLE notificacion_correo_comparendo ADD CONSTRAINT FK_notif_corre_compa_02 
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE pago_comparendo ADD CONSTRAINT FK_pago_compa_01
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE comparendo_proceso_contra ADD CONSTRAINT FK_compa_proceso_contra_01 
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE comparendo_proceso_contra ADD CONSTRAINT FK_compa_proceso_contra_02 
	FOREIGN KEY (id_proceso_contravencional) REFERENCES proceso_contravencional (id_proceso_contravencional);

ALTER TABLE proceso_contravencional ADD CONSTRAINT FK_proceso_contra_01 
	FOREIGN KEY (id_tipo_proceso) REFERENCES tipo_proceso (id_tipo_proceso);

ALTER TABLE proceso_contravencional ADD CONSTRAINT FK_proceso_contra_02 
	FOREIGN KEY (id_estado_proceso) REFERENCES estado_proceso (id_estado_proceso);

ALTER TABLE curso_pedagogico ADD CONSTRAINT FK_curso_pedago_01 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona);

ALTER TABLE curso_pedagogico ADD CONSTRAINT FK_curso_pedago_02 
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE comparendo_resolucion ADD CONSTRAINT FK_compa_resol_01 
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE comparendo_resolucion ADD CONSTRAINT FK_compa_resol_02 
	FOREIGN KEY (id_resolucion) REFERENCES resolucion (id_resolucion);

ALTER TABLE resolucion ADD CONSTRAINT FK_resol_01 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

ALTER TABLE resolucion ADD CONSTRAINT FK_resol_02 
	FOREIGN KEY (id_tipo_resolucion) REFERENCES tipo_resolucion (id_tipo_resolucion);

ALTER TABLE resolucion ADD CONSTRAINT FK_resol_03 
	FOREIGN KEY (id_estado_resolucion) REFERENCES estado_resolucion (id_estado_resolucion);

ALTER TABLE resolucion ADD CONSTRAINT FK_resol_04 
	FOREIGN KEY (id_resolucion_anterior) REFERENCES resolucion (id_resolucion);

-- SPRINT 10
ALTER TABLE error_cargue_acuse ADD CONSTRAINT FK_error_cargu_acuse_01 
	FOREIGN KEY (id_campo_archivo_acuse) REFERENCES campo_archivo_acuse (id_campo_archivo_acuse);

ALTER TABLE error_cargue_acuse ADD CONSTRAINT FK_error_cargu_acuse_02 
	FOREIGN KEY (id_mensaje_error_cargue_acuse) REFERENCES mensaje_error_cargue_acuse (id_mensaje_error_cargue_acuse);

ALTER TABLE error_cargue_acuse ADD CONSTRAINT FK_error_cargu_acuse_03 
	FOREIGN KEY (id_comparendo_archivo_acuse) REFERENCES comparendo_archivo_acuse (id_comparendo_archivo_acuse);

ALTER TABLE comparendo_archivo_acuse ADD CONSTRAINT FK_compa_archi_acuse_01 
	FOREIGN KEY (id_archivo_acuse_correo) REFERENCES archivo_acuse_notificacion (id_archivo_acuse_correo);

ALTER TABLE comparendo_archivo_acuse ADD CONSTRAINT FK_compa_archi_acuse_02 
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE notificacion_aviso_comparendo ADD CONSTRAINT FK_notif_aviso_compa_01 
	FOREIGN KEY (id_notificacion_aviso) REFERENCES notificacion_aviso (id_notificacion_aviso);

ALTER TABLE notificacion_aviso_comparendo ADD CONSTRAINT FK_notif_aviso_compa_02 
	FOREIGN KEY (cicomparendo) REFERENCES comparendo (cicomparendo);

ALTER TABLE notificacion_aviso ADD CONSTRAINT FK_notif_aviso_01 
	FOREIGN KEY (codigo_organismo) REFERENCES organismo_transito (codigo_organismo);

-- SPRINT 11
ALTER TABLE rectifica_evidencia ADD CONSTRAINT FK_recti_evide_01 
	FOREIGN KEY (id_rectifica_comparendo) REFERENCES rectifica_comparendo (id_rectifica_comparendo);

ALTER TABLE rectifica_evidencia ADD CONSTRAINT FK_recti_evide_02 
	FOREIGN KEY (codigo_tipo_evidencia) REFERENCES tipo_evidencia (codigo_tipo_evidencia);

-- SPRINT 12
ALTER TABLE representante_legal ADD CONSTRAINT FK_repre_legal_01 
	FOREIGN KEY (id_persona) REFERENCES persona (id_persona);

ALTER TABLE representante_legal ADD CONSTRAINT FK_repre_legal_02 
	FOREIGN KEY (id_persona_juridica) REFERENCES persona_juridica (id_persona_juridica);