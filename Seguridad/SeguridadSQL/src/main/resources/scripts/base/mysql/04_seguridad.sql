--   -------------------------------------------------- 
--   Iteracion 2 componente de seguridad
--   -------------------------------------------------- 

ALTER TABLE historico_usuario ADD COLUMN descripcion_cambio VARCHAR(250);
ALTER TABLE historico_usuario ADD COLUMN usuario_cambio VARCHAR(60) NOT NULL;
ALTER TABLE historico_rol ADD COLUMN usuario_cambio VARCHAR(60) NOT NULL;
ALTER TABLE parametro_seguridad ADD COLUMN editable TINYINT NOT NULL DEFAULT 1;