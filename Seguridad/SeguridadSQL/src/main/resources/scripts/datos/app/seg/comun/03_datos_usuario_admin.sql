--   --------------------------------------------------
--   Insercion de datos base usuario super-admin
--   03_datos_usuario_admin.sql
--   -------------------------------------------------- 

-- USUARIO SUPER-ADMIN pass: admin1
INSERT INTO usuario(id_usuario,nombre, apellido, email, id_estado_usuario, id_estado_password, login, password, ldap, huella, fecha_modifica_password, fecha_modifica_usuario, fecha_inicio_usuario, fecha_fin_usuario) VALUES(1,'Nombre', 'Apellido', '1@g.com', 1, 1, 'admin', 'WLVETPG2JTpDF/4S2v9BGni9oKlSebHVdo6/XKYIKeeNqUToqRYKC21CjLIT6BNSWnJlDaxnuIh5OU/2JNpILw==', 0, NULL, SYSDATE(), SYSDATE(), SYSDATE(), NULL);
-- USR: SUPER-ADMIN - ROL: SUPER-ADMIN 
INSERT INTO rol_usuario(id_rol,id_usuario) values (1,1);
-- USR: SUPER-ADMIN - ROL: PUBLICO
INSERT INTO rol_usuario(id_rol,id_usuario) values (2,1);

