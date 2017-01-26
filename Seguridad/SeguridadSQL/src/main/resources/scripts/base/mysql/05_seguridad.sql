--   -------------------------------------------------- 
--   Solucion de bugs de V.1.1.3
--   -------------------------------------------------- 
-- 2015-06-16 cambio de varchar por TEXT https://redmine.datatools.com.co:8008/issues/29917
ALTER TABLE recurso_menu MODIFY recurso_data TEXT;

--   --------------------------------------------------
--2015-07-09 campo descripcion_cambio no debe ser NULL https://redmine.datatools.com.co:8008/issues/31415
--   --------------------------------------------------
UPDATE historico_usuario SET descripcion_cambio = '' WHERE descripcion_cambio IS NULL;
ALTER TABLE historico_usuario MODIFY descripcion_cambio VARCHAR(250) NOT NULL;
