SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM responsable_formulario;
DELETE FROM unificacion_responsable;
DELETE FROM numeracion_formulario;
DELETE FROM rango_formulario;
DELETE FROM detalle_cambio_estado;
DELETE FROM formulario;
DELETE FROM seguimiento_formulario;

SET FOREIGN_KEY_CHECKS = 1;