-- USUARIO Administrador C2 pass: admin1
INSERT INTO usuario(id_usuario,nombre, apellido, email, id_estado_usuario, id_estado_password, login, password, ldap, huella, fecha_modifica_password, fecha_modifica_usuario, fecha_inicio_usuario, fecha_fin_usuario) VALUES(-1,'Administrador C2', 'Administrador C2', 'admc2@g.com', 1, 1, 'admin-c2', 'WLVETPG2JTpDF/4S2v9BGni9oKlSebHVdo6/XKYIKeeNqUToqRYKC21CjLIT6BNSWnJlDaxnuIh5OU/2JNpILw==', 0, NULL, SYSDATE(), SYSDATE(), SYSDATE(), NULL);
-- USR: Administrador C2 - ROL: SUPER-ADMIN 
INSERT INTO rol_usuario(id_rol,id_usuario) SELECT id_rol, -1 FROM rol where nombre='Administrador Circulemos 2.0';

