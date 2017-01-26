--   --------------------------------------------------
--   Insercion de datos pruebas usuario
--   02_datos_pruebas_usuarios.sql
--   -------------------------------------------------- 

-- USUARIO INICIAL
INSERT INTO usuario(id_usuario,nombre,apellido,email,id_estado_usuario,id_estado_password,login,password,ldap,huella,fecha_modifica_password,fecha_modifica_usuario,fecha_inicio_usuario,fecha_fin_usuario)
VALUES(1000,'Nombre 11','Apellido 11','11@g.com',1,1,'login11','pass11',0,null,'2014-01-01 00:00:00','2014-01-01 00:00:00','2014-01-01 00:00:00','2014-01-01 00:00:00');

INSERT INTO rol_usuario(id_rol,id_usuario) values (1,1000);

--HISTORICO DE USUARIO 
INSERT INTO usuario(id_usuario,nombre,apellido,email,id_estado_usuario,id_estado_password,login,password,ldap,huella,fecha_modifica_password,fecha_modifica_usuario,fecha_inicio_usuario,fecha_fin_usuario)
VALUES(1001,'Jose','Cardenas','jcardenas@gmail.com',1,1,'jcardenas','pass11',0,null,'2014-01-01 00:00:00','2014-01-01 00:00:00','2014-01-01 00:00:00','2014-12-31 00:00:00');

INSERT INTO historico_usuario(id_historico_usuario, id_usuario, password, fecha_inicio, fecha_final, fecha_modifica_password, xml_historico, id_xsd_historico)
values (1000, 1001, 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', '2014-05-16 15:55:29', null, '2014-05-16 15:55:29', '<UsuarioDetalleDto><id>1001</id><nombres>Jose</nombres><apellidos>Cardenas</apellidos><email>jcardenas@gmail.com</email><estadoUsuario>Activo</estadoUsuario><estadoPassword>Activo</estadoPassword><autenticacionConLdap>false</autenticacionConLdap><fechaInicioUsuario>2014-07-23 20:49:49.0 UTC</fechaInicioUsuario><password>4AmWK10lFS7Hzb4HifNoLDA953q4yqdPisirepl4fJQjZZncWNT3ruXqYa/fnUIEBH5OW5wk9wIt1H+9GBDUaw==</password><login>jcardenas</login><fechaModPass>2014-07-24 22:25:01.0 UTC</fechaModPass><fechaModUsuario>2014-10-21 21:25:36.717 UTC</fechaModUsuario><roles><RolDetalleDto><idRol>-1</idRol><nombre>Administrador Circulemos 2</nombre><descripcion>Rol con todos los permisos Circulemos 2</descripcion><activo>true</activo></RolDetalleDto><RolDetalleDto><idRol>2</idRol><nombre>PUBLICO</nombre><descripcion>Define los permisos para los recursos publicos (accesibles sin autenticacion)</descripcion><activo>true</activo></RolDetalleDto><RolDetalleDto><idRol>3</idRol><nombre>Administrador seguridad</nombre><descripcion>Administrador del sistema, puede realizar todos los crud de seguridad</descripcion><activo>true</activo></RolDetalleDto></roles><grupos/></UsuarioDetalleDto>', 2);

--INGRESO USUARIO
INSERT INTO ingreso_usuario(id_ingreso_usuario, id_usuario, fecha_inicio, fecha_cierre, ip_equipo, id_estado_ingreso, xml_actividad_ingreso, id_xsd, id_aplicacion)
values(1, 1001, '2014-10-23 08:51:52', '2014-10-23 08:59:53', '127.0.0.1', 1, '<actividadIngreso>\n  <InfoAutorizacion>\n    <nombreRecurso>main</nombreRecurso>\n    <nombreOperacion>INGRESAR</nombreOperacion>\n    <horaSolicitud>2014-10-23 13:51:53.687 UTC</horaSolicitud>\n    <permitido>true</permitido>\n  </InfoAutorizacion>\n  <InfoAutorizacion>\n    <nombreRecurso>historico-ingreso</nombreRecurso>\n    <nombreOperacion>INGRESAR</nombreOperacion>\n    <horaSolicitud>2014-10-23 13:54:56.390 UTC</horaSolicitud>\n    <permitido>true</permitido>\n  </InfoAutorizacion>\n  <InfoAutorizacion>\n    <nombreRecurso>historico-ingreso</nombreRecurso>\n    <nombreOperacion>CONSULTAR</nombreOperacion>\n    <horaSolicitud>2014-10-23 13:55:08.866 UTC</horaSolicitud>\n    <permitido>true</permitido>\n  </InfoAutorizacion>\n  <InfoAutorizacion>\n    <nombreRecurso>historico-ingreso</nombreRecurso>\n    <nombreOperacion>CONSULTAR</nombreOperacion>\n    <horaSolicitud>2014-10-23 13:55:30.132 UTC</horaSolicitud>\n    <permitido>true</permitido>\n  </InfoAutorizacion>\n  <InfoAutorizacion>\n    <nombreRecurso>main</nombreRecurso>\n    <nombreOperacion>INGRESAR</nombreOperacion>\n    <horaSolicitud>2014-10-23 13:59:53.810 UTC</horaSolicitud>\n    <permitido>true</permitido>\n  </InfoAutorizacion>\n</actividadIngreso>', 3, 1);



