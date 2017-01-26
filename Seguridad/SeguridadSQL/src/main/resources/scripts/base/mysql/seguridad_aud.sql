-- 
-- Disable foreign keys
-- 
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

-- 
-- Set character set the client will use to send SQL statements to the server
--
SET NAMES 'utf8';

-- 
-- Set default database
--
USE seguridad_aud;

--
-- Definition for table revision
--
DROP TABLE IF EXISTS revision;
CREATE TABLE revision (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  fechaAud DATETIME DEFAULT NULL,
  ip VARCHAR(255) DEFAULT NULL,
  usuario VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
AUTO_INCREMENT = 1
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

--
-- Definition for table configuracion_rol_aud
--
DROP TABLE IF EXISTS configuracion_rol_aud;
CREATE TABLE configuracion_rol_aud (
  id_configuracion_rol INT(11) NOT NULL,
  id_revision BIGINT(20) NOT NULL,
  id_tipo_revision TINYINT(4) DEFAULT NULL,
  id_rol_1 INT(11) DEFAULT NULL,
  id_rol_2 INT(11) DEFAULT NULL,
  id_tipo_restriccion INT(11) DEFAULT NULL,
  PRIMARY KEY (id_configuracion_rol, id_revision),
  INDEX IND_id_revis_01 (id_revision),
  CONSTRAINT FK_confi_rol_aud_01 FOREIGN KEY (id_revision)
    REFERENCES revision(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

--
-- Definition for table grupo_aud
--
DROP TABLE IF EXISTS grupo_aud;
CREATE TABLE grupo_aud (
  id_grupo INT(11) NOT NULL,
  id_revision BIGINT(20) NOT NULL,
  id_tipo_revision TINYINT(4) DEFAULT NULL,
  activo TINYINT(1) DEFAULT NULL,
  contexto_aplica VARCHAR(255) DEFAULT NULL,
  descripcion VARCHAR(255) DEFAULT NULL,
  nombre VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id_grupo, id_revision),
  INDEX IND_id_revis_01 (id_revision),
  CONSTRAINT FK_grupo_aud_01 FOREIGN KEY (id_revision)
    REFERENCES revision(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

--
-- Definition for table operacion_aud
--
DROP TABLE IF EXISTS operacion_aud;
CREATE TABLE operacion_aud (
  id_operacion INT(11) NOT NULL,
  id_revision BIGINT(20) NOT NULL,
  id_tipo_revision TINYINT(4) DEFAULT NULL,
  nombre VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id_operacion, id_revision),
  INDEX IND_id_revis_01 (id_revision),
  CONSTRAINT FK_opera_aud_01 FOREIGN KEY (id_revision)
    REFERENCES revision(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

--
-- Definition for table parametro_seguridad_aud
--
DROP TABLE IF EXISTS parametro_seguridad_aud;
CREATE TABLE parametro_seguridad_aud (
  id_parametro_seguridad INT(11) NOT NULL,
  id_revision BIGINT(20) NOT NULL,
  id_tipo_revision TINYINT(4) DEFAULT NULL,
  editable TINYINT(1) DEFAULT NULL,
  nombre_parametro_seguridad VARCHAR(255) DEFAULT NULL,
  valor_parametro_seguridad VARCHAR(255) DEFAULT NULL,
  id_tipo_dato INT(11) DEFAULT NULL,
  PRIMARY KEY (id_parametro_seguridad, id_revision),
  INDEX IND_id_revis_01 (id_revision),
  CONSTRAINT FK_param_segur_aud_01 FOREIGN KEY (id_revision)
    REFERENCES revision(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

--
-- Definition for table recurso_aud
--
DROP TABLE IF EXISTS recurso_aud;
CREATE TABLE recurso_aud (
  id_recurso INT(11) NOT NULL,
  id_revision BIGINT(20) NOT NULL,
  id_tipo_revision TINYINT(4) DEFAULT NULL,
  descripcion VARCHAR(255) DEFAULT NULL,
  nombre VARCHAR(255) DEFAULT NULL,
  id_aplicacion INT(11) DEFAULT NULL,
  id_recurso_padre INT(11) DEFAULT NULL,
  id_tipo_recurso INT(11) DEFAULT NULL,
  PRIMARY KEY (id_recurso, id_revision),
  INDEX IND_id_revis_01 (id_revision),
  CONSTRAINT FK_recur_aud_01 FOREIGN KEY (id_revision)
    REFERENCES revision(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

--
-- Definition for table recurso_menu_aud
--
DROP TABLE IF EXISTS recurso_menu_aud;
CREATE TABLE recurso_menu_aud (
  id_recurso_menu INT(11) NOT NULL,
  id_revision BIGINT(20) NOT NULL,
  id_tipo_revision TINYINT(4) DEFAULT NULL,
  orden SMALLINT(6) DEFAULT NULL,
  recurso_data VARCHAR(255) DEFAULT NULL,
  id_menu_padre INT(11) DEFAULT NULL,
  id_recurso INT(11) DEFAULT NULL,
  PRIMARY KEY (id_recurso_menu, id_revision),
  INDEX IND_id_revis_01 (id_revision),
  CONSTRAINT FK_recur_menu_aud_01 FOREIGN KEY (id_revision)
    REFERENCES revision(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
CHARACTER SET latin1
COLLATE latin1_swedish_ci;

--
-- Definition for table recurso_operacion_aud
--
DROP TABLE IF EXISTS recurso_operacion_aud;
CREATE TABLE recurso_operacion_aud (
  id_recurso_operacion INT(11) NOT NULL,
  id_revision BIGINT(20) NOT NULL,
  id_tipo_revision TINYINT(4) DEFAULT NULL,
  id_operacion INT(11) DEFAULT NULL,
  id_recurso INT(11) DEFAULT NULL,
  PRIMARY KEY (id_recurso_operacion, id_revision),
  INDEX IND_id_revis_01 (id_revision),
  CONSTRAINT FK_recur_opera_aud_01 FOREIGN KEY (id_revision)
    REFERENCES revision(id) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE = INNODB
CHARACTER SET latin1
COLLATE latin1_swedish_ci;


-- 
-- Enable foreign keys
-- 
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;