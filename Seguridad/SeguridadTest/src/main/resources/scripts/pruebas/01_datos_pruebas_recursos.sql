--   --------------------------------------------------
--   Insercion de datos de logica de negocio basicos
--   01_datos_pruebas_recursos.sql
--   -------------------------------------------------- 

-- RECURSO INICIAL
INSERT INTO recurso(id_recurso,nombre, descripcion, id_recurso_padre, id_aplicacion) VALUES (1000,'recurso0001', 'Recurso 0001', null, '1');
INSERT INTO recurso(id_recurso,nombre, descripcion, id_recurso_padre, id_aplicacion) VALUES (1001,'recurso0002', 'Recurso 0002', null, '1');

INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) VALUES(1001,1000, 1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) VALUES(1002,1000, 2);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) VALUES(1003,1000, 3);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) VALUES(1004,1000, 4);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) VALUES(1005,1000, 5);


