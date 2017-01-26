DELETE FROM saldo_cartera;
DELETE FROM trazabilidad_cartera;
DELETE FROM movimientos_cartera;
DELETE FROM cartera;
DELETE FROM usuario_persona WHERE id_usuario = -2;
DELETE FROM persona WHERE id_persona = -2;