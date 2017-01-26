--   --------------------------------------------------
--   Insercion de datos de logica de negocio basicos
--   01_datos_roles.sql
--   -------------------------------------------------- 

-- ROLES INICIALES 
INSERT INTO ROL(id_rol,nombre,descripcion,activo,id_rol_padre) VALUES (1,'SUPER-ADMIN','Define los permisos para el super administrador del sistema',1,NULL)
;
INSERT INTO ROL(id_rol,nombre,descripcion,activo,id_rol_padre) VALUES (2,'PUBLICO','Define los permisos para los recursos publicos (accesibles sin autenticacion)',1,NULL)
;


-- RECURSOS BASE DE APLICACION DE SEGURIDAD PARA ASIGNAR A ROL SUPER-ADMIN
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(1, 'main', 'Pagina Inicio', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(10, 'recuperapw', 'Pagina recuperacion pw', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(12, 'cuenta', 'Mi Cuenta', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(13, 'usuarios', 'Usuarios', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(14, 'roles', 'Roles', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(15, 'recursos', 'Recursos', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(16, 'grupos', 'Grupos', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(17, 'operaciones', 'Operaciones', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(18, 'opcionesmenu', 'Opciones de Menu', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(20, 'parametros', 'Parametros', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(21, 'historico-ingreso', 'Ingresos', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(22, 'historico-roles', 'Histórico de Roles', NULL, NULL, 1)
;
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(23, 'historico-usuarios', 'Histórico de Usuarios', NULL, NULL, 1)
;

-- RECURSO-OPERACION PARA ASIGNAR A ROL SUPER-ADMIN
-- main - ingreso
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (2,1,1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (6,12,1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (7,13,1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (8,13,2);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (9,13,3);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (10,13,4);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (11,13,5);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (12,14,1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (13,14,2);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (14,14,3);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (15,14,4);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (16,14,5);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (17,15,1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (18,15,2);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (19,15,3);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (20,15,4);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (21,15,5);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (22,16,1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (23,16,2);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (24,16,3);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (25,16,4);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (26,16,5);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (27,17,1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (28,17,2);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (29,17,3);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (30,17,4);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (31,17,5);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (32,18,1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (33,18,3);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (34,18,5);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (35,12,5);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (36,20,1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (37,20,3);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (38,20,5);
-- recuperapw - ingreso
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (39,10,1);
-- historicos
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (40,21,1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (41,22,1);
INSERT INTO recurso_operacion(id_recurso_operacion,id_recurso, id_operacion) values (42,23,1);


-- PERMISO-RECURSO-OPERACION PARA ASIGNAR A ROL SUPER-ADMIN
-- main - ingreso
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(2,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(6,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(7,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(8,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(9,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(10,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(11,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(12,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(13,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(14,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(15,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(16,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(17,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(18,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(19,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(20,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(21,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(22,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(23,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(24,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(25,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(26,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(27,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(28,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(29,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(30,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(31,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(32,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(33,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(34,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(35,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(36,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(37,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(38,1);
-- recuperapw - ingreso
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(39,1);
-- historicos
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(40,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(41,1);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(42,1);

-- PERMISO-RECURSO-OPERACION PARA ASIGNAR A ROL PUBLICO
-- main - ingreso
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(2,2);
-- recuperapw - ingreso
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(39,2);








