--   -------------------------------------------------- 
--   Iteracion 2 componente de seguridad
--   -------------------------------------------------- 

ALTER TABLE ingreso_usuario ADD COLUMN id_aplicacion INTEGER NOT NULL;
ALTER TABLE ingreso_usuario ADD CONSTRAINT FK_ingre_usuar_04 
	FOREIGN KEY (id_aplicacion) REFERENCES aplicacion (id_aplicacion);