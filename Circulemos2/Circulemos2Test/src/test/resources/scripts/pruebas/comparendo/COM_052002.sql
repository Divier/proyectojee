SET FOREIGN_KEY_CHECKS = 0;
DELETE FROM trazabilidad_comparendo;
DELETE FROM orden_comparendo_nacional;
DELETE FROM unificacion_responsable;
DELETE FROM responsable_formulario;
DELETE FROM persona WHERE ID_PERSONA <> -1;
DELETE FROM direccion;
DELETE FROM persona_juridica;
DELETE FROM comparendo;
DELETE FROM comparendo_vehiculo;
DELETE FROM comparendo_agente;
DELETE FROM comparendo_patio;
DELETE FROM comparendo_persona;
DELETE FROM agente;
DELETE FROM comparendo_cartera;
DELETE FROM cartera;

SET FOREIGN_KEY_CHECKS = 1;