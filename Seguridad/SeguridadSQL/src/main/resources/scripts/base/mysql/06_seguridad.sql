--   -------------------------------------------------- 
--   Solucion de bugs de V.2.0.0-SNAPSHOT
--   -------------------------------------------------- 

-- ----------------------------------------------------
-- 2015-07-09 campo fecha_inicio_rol no debe ser NULL https://redmine.datatools.com.co:8008/issues/31419
-- Incidencia Diseño paquete de datos técnicos, se solicita ejecucion a CM con la misma incidencia
-- ----------------------------------------------------
ALTER TABLE historico_rol MODIFY fecha_inicio_rol DATETIME NOT NULL;
