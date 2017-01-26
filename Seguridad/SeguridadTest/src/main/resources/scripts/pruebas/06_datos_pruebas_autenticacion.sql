--   --------------------------------------------------
--   Insercion de datos de logica de negocio basicos
--   06_datos_pruebas_autenticacion.sql
--   -------------------------------------------------- 

-- OPCIONES DE MENU PARA PRUEBA 

--Cambiar valor de algunos parametros para que la prueba sea valida despues de la fecha de creacion de la prueba, ya que la autenticacion fallida/Exitosa depende de varias fechas en el registro de usuario
UPDATE PARAMETRO_SEGURIDAD SET valor_PARAMETRO_SEGURIDAD= 'NO' WHERE nombre_PARAMETRO_SEGURIDAD='DESBLOQUEO_PW_AUTOMATICO';
UPDATE PARAMETRO_SEGURIDAD SET valor_PARAMETRO_SEGURIDAD=1460 WHERE nombre_PARAMETRO_SEGURIDAD='PW_CANTIDAD_DIAS_VIGENCIA';
UPDATE PARAMETRO_SEGURIDAD SET valor_PARAMETRO_SEGURIDAD=3 WHERE nombre_PARAMETRO_SEGURIDAD='CANTIDAD_INTENTOS_FALLIDOS_PW';

--Usuario Pw Estado= 1	Activo
INSERT INTO usuario
(id_usuario,nombre, apellido, email, id_estado_usuario, id_estado_password, login, password, ldap, huella, fecha_modifica_password, fecha_modifica_usuario, fecha_inicio_usuario, fecha_fin_usuario)
VALUES(1002,'Cristian', 'Palomino', 'jimena.ud@gmail.com', 1, 1, 'usuarioPwActivo', 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', 0, NULL, '2014-05-16 00:00:00','2014-05-16 00:00:00','2014-05-16 00:00:00',null);
INSERT INTO historico_usuario(id_historico_usuario, id_usuario, password, fecha_inicio, fecha_final, fecha_modifica_password, xml_historico, id_xsd_historico)
values (1, 1002, 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', '2014-05-16 15:55:29', null, '2014-05-16 15:55:29', '<co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto>\n  <id>1</id>\n  <nombres>Nombre1</nombres>\n  <apellidos>Apellido1</apellidos>\n  <email>1@g.com</email>\n  <idEstadoUsuario>1</idEstadoUsuario>\n  <estadoUsuario>Activo</estadoUsuario>\n  <idEstadoPassword>1</idEstadoPassword>\n  <estadoPassword>Activo</estadoPassword>\n  <ldap>false</ldap>\n  <fechaInicioUsuario class=\"sql-timestamp\">2014-01-01 00:00:00.0</fechaInicioUsuario>\n  <login>1</login>\n  <fechaModPass class=\"sql-timestamp\">2014-04-28 10:03:00.0</fechaModPass>\n  <fechaModUsuario>2014-05-06 20:55:29.33 UTC</fechaModUsuario>\n  <roles>\n    <co.com.datatools.seguridad.dto.autorizacion.RolDto>\n      <id>3</id>\n      <nombre>Administrados basico</nombre>\n      <descripcion>Administrados basico, puede realizar todos los crud de seguridad</descripcion>\n      <activo>true</activo>\n    </co.com.datatools.seguridad.dto.autorizacion.RolDto>\n  </roles>\n  <grupos/>\n</co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto>', 2);

--Usuario Pw Estado=3	Temporal
INSERT INTO usuario
(id_usuario,nombre, apellido, email, id_estado_usuario, id_estado_password, login, password, ldap, huella, fecha_modifica_password, fecha_modifica_usuario, fecha_inicio_usuario, fecha_fin_usuario)
VALUES(1003,'Cristian', 'Palomino', 'cristian2@gmail.com', 1, 3, 'usuarioPwTemporal', 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', 0, NULL, '2014-05-16 00:00:00','2014-05-16 00:00:00','2014-05-16 00:00:00',null);

--Usuario Pw Estado=4	Bloqueado
INSERT INTO usuario
(id_usuario,nombre, apellido, email, id_estado_usuario, id_estado_password, login, password, ldap, huella, fecha_modifica_password, fecha_modifica_usuario, fecha_inicio_usuario, fecha_fin_usuario)
VALUES(1004,'Cristian', 'Palomino', 'cristian3@gmail.com', 1, 4, 'usuarioPwBloqueado', 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', 0, NULL, '2014-05-16 00:00:00','2014-05-16 00:00:00','2014-05-16 00:00:00',null);

--Usuario Estado=3	Cancelado
INSERT INTO usuario
(id_usuario,nombre, apellido, email, id_estado_usuario, id_estado_password, login, password, ldap, huella, fecha_modifica_password, fecha_modifica_usuario, fecha_inicio_usuario, fecha_fin_usuario)
VALUES(1005,'Cristian', 'Palomino', 'cristian4@gmail.com', 3, 1, 'usuarioCancelado', 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', 0, NULL, '2014-05-16 00:00:00','2014-05-16 00:00:00','2014-05-16 00:00:00',null);

--Usuario Pw Estado= 1	Activo (para bloquearlo)
INSERT INTO usuario
(id_usuario,nombre, apellido, email, id_estado_usuario, id_estado_password, login, password, ldap, huella, fecha_modifica_password, fecha_modifica_usuario, fecha_inicio_usuario, fecha_fin_usuario)
VALUES(1006,'Cristian', 'Palomino', 'cristian5@gmail.com', 1, 1, 'usuarioPruebaBloqueo', 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', 0, NULL, '2014-05-16 00:00:00','2014-05-16 00:00:00','2014-05-16 00:00:00',null);
INSERT INTO historico_usuario(id_historico_usuario, id_usuario, password, fecha_inicio, fecha_final, fecha_modifica_password, xml_historico, id_xsd_historico)
values (2, 1006, 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', '2014-05-16 15:55:29', null, '2014-05-16 15:55:29', '<co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto>\n  <id>1</id>\n  <nombres>Nombre1</nombres>\n  <apellidos>Apellido1</apellidos>\n  <email>1@g.com</email>\n  <idEstadoUsuario>1</idEstadoUsuario>\n  <estadoUsuario>Activo</estadoUsuario>\n  <idEstadoPassword>1</idEstadoPassword>\n  <estadoPassword>Activo</estadoPassword>\n  <ldap>false</ldap>\n  <fechaInicioUsuario class=\"sql-timestamp\">2014-01-01 00:00:00.0</fechaInicioUsuario>\n  <login>1</login>\n  <fechaModPass class=\"sql-timestamp\">2014-04-28 10:03:00.0</fechaModPass>\n  <fechaModUsuario>2014-05-06 20:55:29.33 UTC</fechaModUsuario>\n  <roles>\n    <co.com.datatools.seguridad.dto.autorizacion.RolDto>\n      <id>3</id>\n      <nombre>Administrados basico</nombre>\n      <descripcion>Administrados basico, puede realizar todos los crud de seguridad</descripcion>\n      <activo>true</activo>\n    </co.com.datatools.seguridad.dto.autorizacion.RolDto>\n  </roles>\n  <grupos/>\n</co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto>', 3);

--Usuario pw estado activo para cambiarle la contraseña
INSERT INTO usuario
(id_usuario,nombre, apellido, email, id_estado_usuario, id_estado_password, login, password, ldap, huella, fecha_modifica_password, fecha_modifica_usuario, fecha_inicio_usuario, fecha_fin_usuario)
VALUES(1008,'Cristian', 'Palomino', 'cp@gmail.com', 1, 1, 'usuarioPwActivo2', 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', 0, NULL, '2014-05-16 00:00:00','2014-05-16 00:00:00','2014-05-16 00:00:00',null);
INSERT INTO historico_usuario(id_historico_usuario, id_usuario, password, fecha_inicio, fecha_final, fecha_modifica_password, xml_historico, id_xsd_historico)
values (3, 1008, 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', '2014-05-16 15:55:29', null, '2014-05-16 15:55:29', '<co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto>\n  <id>1</id>\n  <nombres>Nombre1</nombres>\n  <apellidos>Apellido1</apellidos>\n  <email>1@g.com</email>\n  <idEstadoUsuario>1</idEstadoUsuario>\n  <estadoUsuario>Activo</estadoUsuario>\n  <idEstadoPassword>1</idEstadoPassword>\n  <estadoPassword>Activo</estadoPassword>\n  <ldap>false</ldap>\n  <fechaInicioUsuario class=\"sql-timestamp\">2014-01-01 00:00:00.0</fechaInicioUsuario>\n  <login>1</login>\n  <fechaModPass class=\"sql-timestamp\">2014-04-28 10:03:00.0</fechaModPass>\n  <fechaModUsuario>2014-05-06 20:55:29.33 UTC</fechaModUsuario>\n  <roles>\n    <co.com.datatools.seguridad.dto.autorizacion.RolDto>\n      <id>3</id>\n      <nombre>Administrados basico</nombre>\n      <descripcion>Administrados basico, puede realizar todos los crud de seguridad</descripcion>\n      <activo>true</activo>\n    </co.com.datatools.seguridad.dto.autorizacion.RolDto>\n  </roles>\n  <grupos/>\n</co.com.datatools.seguridad.dto.autenticacion.UsuarioDetalleDto>', 3);


--Inserto solicitud de recuperacion para el usuario insertado con login "usuarioPwActivo", actualizo el parametro de horas de cigencia del vinculo para que no se venza la solicitud y esta sea valida
UPDATE PARAMETRO_SEGURIDAD SET valor_PARAMETRO_SEGURIDAD=50000 WHERE nombre_PARAMETRO_SEGURIDAD='CANTIDAD_HORAS_VIGENCIA_VINCULO_TEMPORAL';
INSERT INTO usuario
(id_usuario,nombre, apellido, email, id_estado_usuario, id_estado_password, login, password, ldap, huella, fecha_modifica_password, fecha_modifica_usuario, fecha_inicio_usuario, fecha_fin_usuario)
VALUES(1007,'Cristian', 'Palomino', 'prueba@gmail.com', 1, 1, 'usrPruebaRecuperaPw', 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', 0, NULL, '2014-05-26 00:00:00','2014-05-26 00:00:00','2014-05-26 00:00:00',null);
INSERT INTO solicitud_cambio_pass(id_solicitud_cambio_pass,codigo_verificacion,fecha_solicitud,fecha_cierre_solicitud,id_usuario,solicitud_activa)
VALUES(1001,'XSfKMA6JLIXDEMD4n1P9GMeWYN3vvvHYRb2RvQZ0uw4/oZh6qreirMxQPVusqGqsWXWa5JEg4K685h1Kb9EqKQ==','2014-05-26 10:05:00',null,1007,true);

--Usuario con solicitud para hacer la recuperacion exitosa
INSERT INTO usuario
(id_usuario,nombre, apellido, email, id_estado_usuario, id_estado_password, login, password, ldap, huella, fecha_modifica_password, fecha_modifica_usuario, fecha_inicio_usuario, fecha_fin_usuario)
VALUES(1009,'Cristian', 'Palomino', 'pruebarecupera@gmail.com', 1, 1, 'usrRecuperaPw', 'JDmmS+kamRUfbPBlxCLTpCU92dNoSluvFVLTWrk+00i1CT31RyAhFzEDaYZ0dKe4qkRStv0njsNCIE6t3+AYiA==', 0, NULL, '2014-05-26 00:00:00','2014-05-26 00:00:00','2014-05-26 00:00:00',null);
INSERT INTO solicitud_cambio_pass(id_solicitud_cambio_pass,codigo_verificacion,fecha_solicitud,fecha_cierre_solicitud,id_usuario,solicitud_activa)
VALUES(1002,'CfDxsMh/P0OQ5wYM7pEqhuLEK1xgFDBZH6fW4TfgMxuLEP/ojqfW29MuTgAuXp6l6f3R2rnkuhh+r7kntYZcDg==','2014-05-26 10:05:00',null,1009,true);

