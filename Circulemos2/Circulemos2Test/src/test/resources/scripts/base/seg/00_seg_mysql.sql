--   --------------------------------------------------
--  Borrado de esquema para crear base de datos en cada test
--   00_seg_mysql.sql
--   -------------------------------------------------- 

-- Borrar todo en motor MySQL
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS aplicacion CASCADE;
DROP TABLE IF EXISTS configuracion_rol CASCADE;
DROP TABLE IF EXISTS estado_ingreso CASCADE;
DROP TABLE IF EXISTS estado_password CASCADE;
DROP TABLE IF EXISTS estado_usuario CASCADE;
DROP TABLE IF EXISTS grupo CASCADE;
DROP TABLE IF EXISTS grupo_rol CASCADE;
DROP TABLE IF EXISTS grupo_usuario CASCADE;
DROP TABLE IF EXISTS historico_rol CASCADE;
DROP TABLE IF EXISTS historico_rol_usuario CASCADE;
DROP TABLE IF EXISTS historico_usuario CASCADE;
DROP TABLE IF EXISTS ingreso_usuario CASCADE;
DROP TABLE IF EXISTS ingreso_usuario_rol CASCADE;
DROP TABLE IF EXISTS operacion CASCADE;
DROP TABLE IF EXISTS parametro_seguridad CASCADE;
DROP TABLE IF EXISTS permiso_recurso_operacion CASCADE;
DROP TABLE IF EXISTS recurso CASCADE;
DROP TABLE IF EXISTS recurso_menu CASCADE;
DROP TABLE IF EXISTS recurso_operacion CASCADE;
DROP TABLE IF EXISTS rol CASCADE;
DROP TABLE IF EXISTS rol_recurso_operacion CASCADE;
DROP TABLE IF EXISTS rol_usuario CASCADE;
DROP TABLE IF EXISTS solicitud_cambio_pass CASCADE;
DROP TABLE IF EXISTS tipo_dato CASCADE;
DROP TABLE IF EXISTS tipo_recurso CASCADE;
DROP TABLE IF EXISTS tipo_restriccion_rol CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS xsd_historico CASCADE;