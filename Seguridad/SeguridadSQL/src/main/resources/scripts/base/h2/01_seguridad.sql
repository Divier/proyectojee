--   -------------------------------------------------- 
--   BD Version Desarrollo 21 abril 2014
--   -------------------------------------------------- 

--  Create Tables 
CREATE TABLE aplicacion
(
	id_aplicacion INTEGER NOT NULL,
	nombre_aplicacion VARCHAR(70) NOT NULL,
	PRIMARY KEY (id_aplicacion),
	UNIQUE UQ_aplic_01(nombre_aplicacion)

) 
;


CREATE TABLE configuracion_rol
(
	id_configuracion_rol INTEGER NOT NULL AUTO_INCREMENT,
	id_rol_1 INTEGER NOT NULL,
	id_rol_2 INTEGER NOT NULL,
	id_tipo_restriccion INTEGER NOT NULL,
	PRIMARY KEY (id_configuracion_rol),
	KEY (id_tipo_restriccion),
	KEY (id_rol_1),
	KEY (id_rol_2)

) 
;


CREATE TABLE estado_ingreso
(
	id_estado_ingreso INTEGER NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	PRIMARY KEY (id_estado_ingreso),
	UNIQUE UQ_estad_ingre_01(nombre)

) 
;


CREATE TABLE estado_password
(
	id_estado INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_estado),
	UNIQUE UQ_estad_passw_01(nombre)

) 
;


CREATE TABLE estado_usuario
(
	id_estado INTEGER NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	PRIMARY KEY (id_estado),
	UNIQUE UQ_estad_usuar_01(nombre)

) 
;


CREATE TABLE grupo
(
	id_grupo INTEGER NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	activo TINYINT NOT NULL DEFAULT 0,
	descripcion VARCHAR(256),
	contexto_aplica VARCHAR(20) NOT NULL,
	PRIMARY KEY (id_grupo),
	UNIQUE UQ_grupo_01(nombre)

) 
;


CREATE TABLE grupo_rol
(
	id_rol INTEGER NOT NULL,
	id_grupo INTEGER NOT NULL,
	PRIMARY KEY (id_rol, id_grupo),
	KEY (id_grupo),
	KEY (id_rol)

) 
;


CREATE TABLE grupo_usuario
(
	id_grupo INTEGER NOT NULL,
	id_usuario INTEGER NOT NULL,
	PRIMARY KEY (id_grupo, id_usuario),
	KEY (id_usuario),
	KEY (id_grupo)

) 
;


CREATE TABLE historico_rol
(
	id_historico_rol BIGINT NOT NULL AUTO_INCREMENT,
	id_rol INTEGER NOT NULL,
	id_rol_padre INTEGER,
	fecha_inicio_rol DATETIME,
	fecha_fin_rol DATETIME,
	xml_historico TEXT NOT NULL,
	id_xsd_historico INTEGER NOT NULL,
	PRIMARY KEY (id_historico_rol),
	KEY (id_rol),
	KEY (id_rol_padre),
	KEY (id_xsd_historico)

) 
;


CREATE TABLE historico_rol_usuario
(
	id_rol INTEGER NOT NULL,
	id_historico_usuario BIGINT NOT NULL,
	PRIMARY KEY (id_rol, id_historico_usuario),
	KEY (id_historico_usuario),
	KEY (id_rol)

) 
;


CREATE TABLE historico_usuario
(
	id_historico_usuario BIGINT NOT NULL AUTO_INCREMENT,
	id_usuario INTEGER NOT NULL,
	password VARCHAR(1024),
	fecha_inicio DATETIME NOT NULL,
	fecha_final DATETIME,
	fecha_modifica_password DATETIME,
	xml_historico TEXT NOT NULL,
	id_xsd_historico INTEGER NOT NULL,
	PRIMARY KEY (id_historico_usuario),
	KEY (id_usuario),
	KEY (id_xsd_historico)

) 
;


CREATE TABLE ingreso_usuario
(
	id_ingreso_usuario INTEGER NOT NULL AUTO_INCREMENT,
	id_usuario INTEGER NOT NULL,
	fecha_inicio DATETIME NOT NULL,
	fecha_cierre DATETIME,
	ip_equipo VARCHAR(15) NOT NULL,
	id_estado_ingreso INTEGER NOT NULL,
	xml_actividad_ingreso TEXT,
	id_xsd INTEGER,
	PRIMARY KEY (id_ingreso_usuario),
	KEY (id_estado_ingreso),
	KEY (id_usuario),
	KEY (id_xsd)

) 
;


CREATE TABLE ingreso_usuario_rol
(
	id_ingreso_usuario INTEGER NOT NULL,
	id_rol INTEGER NOT NULL,
	PRIMARY KEY (id_ingreso_usuario, id_rol),
	KEY (id_ingreso_usuario),
	KEY (id_rol)

) 
;


CREATE TABLE operacion
(
	id_operacion INTEGER NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_operacion),
	UNIQUE UQ_opera_01(nombre)

) 
;


CREATE TABLE parametro_seguridad
(
	id_parametro_seguridad INTEGER NOT NULL AUTO_INCREMENT,
	id_tipo_dato INTEGER NOT NULL,
	nombre_parametro_seguridad VARCHAR(50) NOT NULL,
	valor_parametro_seguridad TEXT NULL,
	PRIMARY KEY (id_parametro_seguridad),
	UNIQUE UQ_param_segur_01(nombre_parametro_seguridad),
	KEY (id_tipo_dato)

) 
;


CREATE TABLE permiso_recurso_operacion
(
	id_permiso_recurso_operacion INTEGER NOT NULL AUTO_INCREMENT,
	id_recurso_operacion INTEGER NOT NULL,
	id_rol INTEGER NOT NULL,
	PRIMARY KEY (id_permiso_recurso_operacion),
	KEY (id_recurso_operacion),
	KEY (id_rol)

) 
;


CREATE TABLE recurso
(
	id_recurso INTEGER NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(250) NOT NULL,
	id_tipo_recurso INTEGER,
	id_recurso_padre INTEGER,
	id_aplicacion INTEGER NOT NULL,
	PRIMARY KEY (id_recurso),
	UNIQUE UQ_recur_01(nombre, id_aplicacion),
	KEY (id_recurso_padre),
	KEY (id_aplicacion),
	KEY (id_tipo_recurso)

) 
;


CREATE TABLE recurso_menu
(
	id_recurso_menu INTEGER NOT NULL AUTO_INCREMENT,
	id_recurso INTEGER NOT NULL,
	orden TINYINT NOT NULL,
	recurso_data VARCHAR(450),
	id_menu_padre INTEGER,
	PRIMARY KEY (id_recurso_menu),
	KEY (id_recurso),
	KEY (id_menu_padre)

) 
;


CREATE TABLE recurso_operacion
(
	id_recurso_operacion INTEGER NOT NULL AUTO_INCREMENT,
	id_recurso INTEGER NOT NULL,
	id_operacion INTEGER NOT NULL,
	PRIMARY KEY (id_recurso_operacion),
	UNIQUE UQ_recur_opera_01(id_recurso, id_operacion),
	KEY (id_operacion),
	KEY (id_recurso)

) 
;


CREATE TABLE rol
(
	id_rol INTEGER NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	descripcion VARCHAR(250) NOT NULL,
	activo TINYINT NOT NULL DEFAULT 0,
	id_rol_padre INTEGER,
	PRIMARY KEY (id_rol),
	UNIQUE UQ_rol_01(nombre),
	KEY (id_rol_padre)

) 
;


CREATE TABLE rol_usuario
(
	id_rol INTEGER NOT NULL,
	id_usuario INTEGER NOT NULL,
	PRIMARY KEY (id_rol, id_usuario),
	KEY (id_usuario),
	KEY (id_rol)

) 
;


CREATE TABLE solicitud_cambio_pass
(
	id_solicitud_cambio_pass BIGINT NOT NULL AUTO_INCREMENT,
	codigo_verificacion VARCHAR(1024) NOT NULL,
	fecha_solicitud DATETIME NOT NULL,
	fecha_cierre_solicitud DATETIME,
	id_usuario INTEGER NOT NULL,
	solicitud_activa TINYINT NOT NULL,
	PRIMARY KEY (id_solicitud_cambio_pass),
	KEY (id_usuario)

) 
;


CREATE TABLE tipo_dato
(
	id_tipo_dato INTEGER NOT NULL,
	nombre_tipo_dato VARCHAR(25) NOT NULL,
	PRIMARY KEY (id_tipo_dato),
	UNIQUE UQ_tipo_dato_01(nombre_tipo_dato)

) 
;


CREATE TABLE tipo_recurso
(
	id_tipo_recurso INTEGER NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_tipo_recurso),
	UNIQUE UQ_tipo_recur_01(nombre)

) 
;


CREATE TABLE tipo_restriccion_rol
(
	id_tipo_restriccion INTEGER NOT NULL,
	nombre_tipo_restriccion VARCHAR(70) NOT NULL,
	PRIMARY KEY (id_tipo_restriccion),
	UNIQUE UQ_tipo_restr_rol_01(nombre_tipo_restriccion)

) 
;


CREATE TABLE usuario
(
	id_usuario INTEGER NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(60) NOT NULL,
	apellido VARCHAR(60),
	email VARCHAR(256) NOT NULL,
	id_estado_usuario INTEGER NOT NULL,
	id_estado_password INTEGER,
	login VARCHAR(60) NOT NULL,
	password VARCHAR(1024),
	ldap TINYINT NOT NULL DEFAULT 0,
	huella TEXT,
	fecha_modifica_password DATETIME,
	fecha_modifica_usuario DATETIME NOT NULL,
	fecha_inicio_usuario DATETIME NOT NULL,
	fecha_fin_usuario DATETIME,
	fecha_bloqueo_password DATETIME,
	PRIMARY KEY (id_usuario),
	UNIQUE UQ_usuar_01(login),
	UNIQUE UQ_usuar_02(email),
	KEY (id_estado_usuario),
	KEY (id_estado_password)

) 
;


CREATE TABLE xsd_historico
(
	id_xsd_historico INTEGER NOT NULL AUTO_INCREMENT,
	contenido_xsd TEXT NOT NULL,
	PRIMARY KEY (id_xsd_historico)

) 
;



-- SET FOREIGN_KEY_CHECKS=1;


ALTER TABLE configuracion_rol ADD CONSTRAINT FK_confi_rol_03 
	FOREIGN KEY (id_tipo_restriccion) REFERENCES tipo_restriccion_rol (id_tipo_restriccion)
;

ALTER TABLE configuracion_rol ADD CONSTRAINT FK_confi_rol_01 
	FOREIGN KEY (id_rol_1) REFERENCES rol (id_rol)
;

ALTER TABLE configuracion_rol ADD CONSTRAINT FK_confi_rol_02 
	FOREIGN KEY (id_rol_2) REFERENCES rol (id_rol)
;

ALTER TABLE grupo_rol ADD CONSTRAINT FK_grupo_rol_01 
	FOREIGN KEY (id_grupo) REFERENCES grupo (id_grupo)
;

ALTER TABLE grupo_rol ADD CONSTRAINT FK_grupo_rol_02 
	FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
;

ALTER TABLE grupo_usuario ADD CONSTRAINT FK_grupo_usuar_01 
	FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
;

ALTER TABLE grupo_usuario ADD CONSTRAINT FK_grupo_usuar_02 
	FOREIGN KEY (id_grupo) REFERENCES grupo (id_grupo)
;

ALTER TABLE historico_rol ADD CONSTRAINT FK_histo_rol_01 
	FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
;

ALTER TABLE historico_rol ADD CONSTRAINT FK_histo_rol_02 
	FOREIGN KEY (id_rol_padre) REFERENCES rol (id_rol)
;

ALTER TABLE historico_rol ADD CONSTRAINT FK_histo_rol_03 
	FOREIGN KEY (id_xsd_historico) REFERENCES xsd_historico (id_xsd_historico)
;

ALTER TABLE historico_rol_usuario ADD CONSTRAINT FK_histo_rol_usuar_01 
	FOREIGN KEY (id_historico_usuario) REFERENCES historico_usuario (id_historico_usuario)
;

ALTER TABLE historico_rol_usuario ADD CONSTRAINT FK_histo_rol_usuar_02 
	FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
;

ALTER TABLE historico_usuario ADD CONSTRAINT FK_histo_usuar_01 
	FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
;

ALTER TABLE historico_usuario ADD CONSTRAINT FK_histo_usuar_02 
	FOREIGN KEY (id_xsd_historico) REFERENCES xsd_historico (id_xsd_historico)
;

ALTER TABLE ingreso_usuario ADD CONSTRAINT FK_ingre_usuar_01 
	FOREIGN KEY (id_estado_ingreso) REFERENCES estado_ingreso (id_estado_ingreso)
;

ALTER TABLE ingreso_usuario ADD CONSTRAINT FK_ingre_usuar_02 
	FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
;

ALTER TABLE ingreso_usuario ADD CONSTRAINT FK_ingre_usuar_03 
	FOREIGN KEY (id_xsd) REFERENCES xsd_historico (id_xsd_historico)
;

ALTER TABLE ingreso_usuario_rol ADD CONSTRAINT FK_ingre_usuar_rol_01 
	FOREIGN KEY (id_ingreso_usuario) REFERENCES ingreso_usuario (id_ingreso_usuario)
;

ALTER TABLE ingreso_usuario_rol ADD CONSTRAINT FK_ingre_usuar_rol_02 
	FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
;

ALTER TABLE parametro_seguridad ADD CONSTRAINT FK_param_segur_01 
	FOREIGN KEY (id_tipo_dato) REFERENCES tipo_dato (id_tipo_dato)
;

ALTER TABLE permiso_recurso_operacion ADD CONSTRAINT FK_permi_recur_opera_01 
	FOREIGN KEY (id_recurso_operacion) REFERENCES recurso_operacion (id_recurso_operacion)
;

ALTER TABLE permiso_recurso_operacion ADD CONSTRAINT FK_permi_recur_opera_02 
	FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
;

ALTER TABLE recurso ADD CONSTRAINT FK_recur_01 
	FOREIGN KEY (id_recurso_padre) REFERENCES recurso (id_recurso)
;

ALTER TABLE recurso ADD CONSTRAINT FK_recur_03 
	FOREIGN KEY (id_aplicacion) REFERENCES aplicacion (id_aplicacion)
;

ALTER TABLE recurso ADD CONSTRAINT FK_recur_02 
	FOREIGN KEY (id_tipo_recurso) REFERENCES tipo_recurso (id_tipo_recurso)
;

ALTER TABLE recurso_menu ADD CONSTRAINT FK_recur_menu_01 
	FOREIGN KEY (id_recurso) REFERENCES recurso (id_recurso)
;

ALTER TABLE recurso_menu ADD CONSTRAINT FK_recur_menu_02 
	FOREIGN KEY (id_menu_padre) REFERENCES recurso_menu (id_recurso_menu)
;

ALTER TABLE recurso_operacion ADD CONSTRAINT FK_recur_opera_01 
	FOREIGN KEY (id_operacion) REFERENCES operacion (id_operacion)
;

ALTER TABLE recurso_operacion ADD CONSTRAINT FK_recur_opera_02 
	FOREIGN KEY (id_recurso) REFERENCES recurso (id_recurso)
;

ALTER TABLE rol ADD CONSTRAINT FK_rol_01 
	FOREIGN KEY (id_rol_padre) REFERENCES rol (id_rol)
;

ALTER TABLE rol_usuario ADD CONSTRAINT FK_rol_usuar_01 
	FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
;

ALTER TABLE rol_usuario ADD CONSTRAINT FK_rol_usuar_02 
	FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
;

ALTER TABLE solicitud_cambio_pass ADD CONSTRAINT FK_solic_cambi_pass_01 
	FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
;

ALTER TABLE usuario ADD CONSTRAINT FK_usuar_01 
	FOREIGN KEY (id_estado_usuario) REFERENCES estado_usuario (id_estado)
;

ALTER TABLE usuario ADD CONSTRAINT FK_usuar_02 
	FOREIGN KEY (id_estado_password) REFERENCES estado_password (id_estado)
;

