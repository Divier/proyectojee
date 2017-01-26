--   -------------------------------------------------- 
--   Vista Rol_Recurso_Operacion
--   -------------------------------------------------- 


--  Create Vista Roles 
CREATE OR REPLACE VIEW rol_recurso_operacion AS 
Select
  rol.id_rol,
  rol.nombre As rol_nombre,
  recurso.id_aplicacion As id_aplicacion,
  aplicacion.nombre_aplicacion As nombre_aplicacion,
  recurso.id_recurso As id_recurso,
  recurso.nombre As recurso_nombre,
  recurso.descripcion As recurso_descripcion,
  operacion.nombre As operacion_nombre,
recurso_operacion.id_recurso_operacion
From
  rol Inner Join
  permiso_recurso_operacion
    On permiso_recurso_operacion.id_rol = rol.id_rol
  Inner Join
  recurso_operacion
    On permiso_recurso_operacion.id_recurso_operacion =
    recurso_operacion.id_recurso_operacion Inner Join
  recurso On recurso_operacion.id_recurso =
    recurso.id_recurso Inner Join
  operacion On recurso_operacion.id_operacion =
    operacion.id_operacion Inner Join 
aplicacion On recurso.id_aplicacion=aplicacion.id_aplicacion;