-- TABLA APLICACION
INSERT INTO APLICACION (id_aplicacion,nombre_aplicacion) VALUES (1,'SEGURIDAD');

-- TABLA ESTADO_USUARIO
INSERT INTO ESTADO_USUARIO (id_estado,nombre) VALUES (1,'Activo');
INSERT INTO ESTADO_USUARIO (id_estado,nombre) VALUES (3,'Cancelado');
INSERT INTO ESTADO_USUARIO (id_estado,nombre) VALUES (2,'Inactivo');

-- TABLA TIPO_DATO
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (1,'Numerico Entero');
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (2,'Numerico Decimal');
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (3,'Texto');
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (4,'Fecha');
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (5,'Hora');
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (6,'Fecha y hora');
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (7,'SI/NO');

-- TABLA PARAMETRO_SEGURIDAD
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'PW_LONGITUD_MIN',8,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'PW_LONGITUD_MAX',16,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'PW_OBLIGA_NUMEROS','SI',1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'PW_OBLIGA_LETRAS','SI',1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'PW_OBLIGA_MAYUSCULAS','SI',1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'PW_OBLIGA_CARACTERES_ESPECIALES','SI',1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'PW_CARACTERES_ESPECIALES','!@#$^&*()_-+ =[]\|,.',1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_HORAS_VIGENCIA_BLOQUEO_PW',6,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'PW_CANTIDAD_DIAS_VIGENCIA',60,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'INITIAL_CONTEXT_FACTORY','com.sun.jndi.ldap.LdapCtxFactory',0);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'SECURITY_AUTHENTICATION','simple',0);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'ALGORITMO_GENERAR_LOGIN','importPackage(java.util);(function(e){resp=new java.util.ArrayList(3);nombres=e.nombres.toLowerCase().split(\" \");apellidos=e.apellidos.toLowerCase().split(\" \");login=\"\";for(var t=0;t<nombres.length&&t<2;t++){login+=nombres[t].charAt(0)}for(var n=0;n<apellidos.length&&n<2;n++){if(n==0){login+=apellidos[n]}else{login+=apellidos[n].charAt(0)}}resp.add(login);resp.add(login+Math.floor(Math.random()*100));resp.add(login+Math.floor(Math.random()*100));return resp})',1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'NIVELES_HERENCIA_ROLES',1,0);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'DESBLOQUEO_PW_AUTOMATICO','SI',1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_PW_HISTORICO',4,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_INTENTOS_FALLIDOS_PW',3,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_HORAS_VALIDA_INTENTOS_FALLIDOS_PW',6,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'PROVIDER_URL',NULL,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'USERS_PATH',NULL,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'AUTHORIZED_GROUP_PATH',NULL,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'SECURITY_PRINCIPAL',NULL,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'SECURITY_CREDENTIALS',NULL,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'PERMITE_AUTENTICACION_LDAP','NO',1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_MIEMBRODE_LDAP',NULL,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_IDUSUARIO_LDAP',NULL,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_LOGIN_LDAP',NULL,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_HORAS_VIGENCIA_VINCULO_TEMPORAL',8,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'ASUNTO_CORREO_RECUPERACION_PW','Olvido de contraseña',1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'CUERPO_CORREO_RECUPERACION_PW','<br/>Estimado Usuario,<br/><br/>Para restablecer su contraseña haga clic en el siguiente link. También puede copiarlo y pegarlo en la barra de direcciones de su navegador de internet.<br/><br/><strong>{0}</strong><br/><br/><strong>Tenga en cuenta que este link expira {1} horas después de la hora en que fue enviado.</strong>',1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_NOMBRES_LDAP',NULL,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_CORREO_LDAP',NULL,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_APELLIDOS_LDAP',NULL,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'URL_APLICACION','<map><entry><string>SEGURIDAD</string><string>http://localhost:8080/SeguridadWEB</string></entry><entry><string>CIRCULEMOS2.0</string><string>http://localhost:8080/Circulemos2PWEB</string></entry></map>',1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_DIAS_CONSULTA_INGRESOS',360,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_DIAS_CONSULTA_ROLES',360,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_DIAS_CONSULTA_USUARIOS',360,1);
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'INSTALADOR_EJECUTADO','NO',0);


-- TABLA OPERACION
INSERT INTO OPERACION (id_operacion,nombre) VALUES (1,'ingresar');
INSERT INTO OPERACION (id_operacion,nombre) VALUES(2,'crear');
INSERT INTO OPERACION (id_operacion,nombre) VALUES(3,'consultar');
INSERT INTO OPERACION (id_operacion,nombre) VALUES(4,'eliminar');
INSERT INTO OPERACION (id_operacion,nombre) VALUES(5,'editar');

-- TABLA ESTADO_PASSWORD
INSERT INTO ESTADO_PASSWORD (id_estado,nombre) values(1,'Activo');
INSERT INTO ESTADO_PASSWORD (id_estado,nombre) values(2,'Vencido');
INSERT INTO ESTADO_PASSWORD (id_estado,nombre) values(3,'Temporal');
INSERT INTO ESTADO_PASSWORD (id_estado,nombre) values(4,'Bloqueado');

-- TABLA ESTADO_INGRESO
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(1, 'Exitoso');
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(2, 'Fallido por contraseña errónea');
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(3, 'Fallido por contraseña bloqueada');
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(4, 'Fallido por contraseña vencida');
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(5, 'Fallido por usuario no activo');
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(6, 'Fallido por contraseña temporal');

-- TABLA xsd_historico
-- Rol
INSERT INTO xsd_historico(id_xsd_historico,contenido_xsd)VALUES(1,'<xs:schema attributeFormDefault="unqualified"  	elementFormDefault="qualified" xmlns:xs="http://www.w3.org/2001/XMLSchema">  	<!-- INICIO Definicion del Tipo para el detalle del recurso -->  	<xs:complexType name="recursoDetalle">  		<xs:sequence>  			<xs:element type="xs:integer" name="idRecurso" />  			<xs:element type="xs:string" name="nombreRecurso" />  			<xs:element type="xs:string" name="descripcion" minOccurs="0" />  			<xs:element type="xs:string" name="hijos" minOccurs="0" />  			<xs:element name="operaciones">  				<xs:complexType>  					<xs:sequence>  						<xs:element name="OperacionDto" minOccurs="1"  							maxOccurs="unbounded">  							<xs:complexType>  								<xs:sequence>  									<xs:element name="idOperacion" type="xs:integer" />  									<xs:element name="nombreOperacion" type="xs:string" />  								</xs:sequence>  							</xs:complexType>  						</xs:element>  					</xs:sequence>  				</xs:complexType>  			</xs:element>  		</xs:sequence>  	</xs:complexType>  	<!-- FIN Definicion del Tipo para el detalle del recurso -->  	<xs:complexType name="RolDetalleDto">  		<xs:sequence>  			<xs:element type="xs:integer" name="idRol" />  			<xs:element type="xs:string" name="nombre" />  			<xs:element type="xs:string" name="descripcion" minOccurs="0" />  			<xs:element type="xs:boolean" name="activo" /> 			  			<xs:element name="recursosAplicacion">  				<xs:complexType>  					<xs:sequence>  						<xs:element name="entry" maxOccurs="unbounded"  							minOccurs="1">  							<xs:complexType>  								<xs:sequence>  									<xs:element type="xs:string" name="string" />  									<xs:element name="list">  										<xs:complexType>  											<xs:sequence>  												<xs:element name="RecursoDetalleDto" maxOccurs="unbounded"  													minOccurs="1" type="recursoDetalle">  												</xs:element>  											</xs:sequence>  										</xs:complexType>  									</xs:element>  								</xs:sequence>  							</xs:complexType>  						</xs:element>  					</xs:sequence>  				</xs:complexType>  			</xs:element>  			<xs:element name="rolPadre" type="RolDetalleDto" minOccurs="0" nillable="true">  			</xs:element>  			<xs:element name="grupos" minOccurs="0" nillable="true">  				<xs:complexType>  					<xs:sequence>  						<xs:element name="GrupoDto" maxOccurs="unbounded"  							minOccurs="0">  							<xs:complexType>  								<xs:sequence>  									<xs:element type="xs:integer" name="idGrupo" />  									<xs:element type="xs:string" name="nombre" />  									<xs:element type="xs:string" name="descripcion" />  									<xs:element type="xs:boolean" name="activo" />  									<xs:element name="clase">  										<xs:simpleType>  											<xs:restriction base="xs:string">  												<xs:enumeration value="Usuarios" />  												<xs:enumeration value="Roles" />  											</xs:restriction>  										</xs:simpleType>  									</xs:element>  								</xs:sequence>  							</xs:complexType>  						</xs:element>  					</xs:sequence>  				</xs:complexType>  			</xs:element>  		</xs:sequence>  	</xs:complexType>  	<!-- INICIO Definicion estructura del XML -->  	<xs:element name="RolDetalleDto" type="RolDetalleDto">  	</xs:element>  	<!-- FIN Definicion estructura del XML -->  </xs:schema>');  
-- Usuario
INSERT INTO xsd_historico(id_xsd_historico,contenido_xsd)VALUES(2,'<xs:schema attributeFormDefault="unqualified"  	elementFormDefault="qualified" xmlns:xs="http://www.w3.org/2001/XMLSchema">  	<xs:element  		name="UsuarioDetalleDto">  		<xs:complexType>  			<xs:sequence>  				<xs:element type="xs:integer" name="id" />  				<xs:element type="xs:string" name="nombres" />  				<xs:element type="xs:string" name="apellidos" />  				<xs:element type="xs:string" name="email" />  				<xs:element type="xs:string" name="estadoUsuario" />  				<xs:element type="xs:string" name="estadoPassword" />  				<xs:element type="xs:boolean" name="autenticacionConLdap" />  				<xs:element type="xs:string" name="fechaInicioUsuario" />  				<xs:element type="xs:string" name="fechaFinUsuario" minOccurs="0" />  				<xs:element type="xs:string" name="fechaBloqueoPassword" minOccurs="0" />  				<xs:element type="xs:string" name="password" minOccurs="0"/>  				<xs:element type="xs:string" name="login" /> 				<xs:element type="xs:string" name="fechaModPass" minOccurs="0" />  				<xs:element type="xs:string" name="fechaModUsuario" />					  				<xs:element name="roles">  					<xs:complexType>  						<xs:sequence>  							<xs:element  								name="RolDetalleDto"  								maxOccurs="unbounded" minOccurs="1">  								<xs:complexType>  									<xs:sequence>  										<xs:element type="xs:integer" name="idRol" />  										<xs:element type="xs:string" name="nombre" />  										<xs:element type="xs:string" name="descripcion" />  										<xs:element type="xs:boolean" name="activo" />  									</xs:sequence>  								</xs:complexType>  							</xs:element>  						</xs:sequence>  					</xs:complexType>  				</xs:element>  				<xs:element name="grupos">  					<xs:complexType>  						<xs:sequence>  							<xs:element  								name="GrupoDto"  								maxOccurs="unbounded" minOccurs="0">  								<xs:complexType>  									<xs:sequence>  										<xs:element type="xs:integer" name="idGrupo" />  										<xs:element type="xs:string" name="nombre" />  										<xs:element type="xs:string" name="descripcion" />  										<xs:element type="xs:boolean" name="activo" />  										<xs:element name="clase">  											<xs:simpleType>  												<xs:restriction base="xs:string">  													<xs:enumeration value="Usuarios" />  													<xs:enumeration value="Roles" />  												</xs:restriction>  											</xs:simpleType>  										</xs:element>  									</xs:sequence>  								</xs:complexType>  							</xs:element>  						</xs:sequence>  					</xs:complexType>  				</xs:element>  			</xs:sequence>  		</xs:complexType>  	</xs:element>  </xs:schema>');
-- Ingreso usuario
INSERT INTO xsd_historico(id_xsd_historico,contenido_xsd)VALUES(3,'<xs:schema attributeFormDefault="unqualified" elementFormDefault="qualified" xmlns:xs="http://www.w3.org/2001/XMLSchema"> <xs:element name="actividadIngreso"> <xs:complexType> <xs:sequence> <xs:element name="InfoAutorizacion" maxOccurs="unbounded" minOccurs="0"> <xs:complexType> <xs:sequence> <xs:element type="xs:string" name="nombreRecurso" /> <xs:element type="xs:string" name="nombreOperacion" minOccurs="0" /> <xs:element type="xs:string" name="horaSolicitud" /> <xs:element type="xs:boolean" name="permitido" /> </xs:sequence> </xs:complexType> </xs:element> </xs:sequence> </xs:complexType> </xs:element> </xs:schema>');

-- ROLES INICIALES 
INSERT INTO ROL(id_rol,nombre,descripcion,activo,id_rol_padre) VALUES (1,'SUPER-ADMIN','Define los permisos para el super administrador del sistema',1,NULL);
INSERT INTO ROL(id_rol,nombre,descripcion,activo,id_rol_padre) VALUES (2,'PUBLICO','Define los permisos para los recursos publicos (accesibles sin autenticacion)',1,NULL);

-- RECURSOS BASE DE APLICACION DE SEGURIDAD PARA ASIGNAR A ROL SUPER-ADMIN
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(1, 'main', 'Pagina Inicio', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(10, 'recuperapw', 'Pagina recuperacion pw', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(12, 'cuenta', 'Mi Cuenta', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(13, 'usuarios', 'Usuarios', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(14, 'roles', 'Roles', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(15, 'recursos', 'Recursos', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(16, 'grupos', 'Grupos', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(17, 'operaciones', 'Operaciones', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(18, 'opcionesmenu', 'Opciones de Menu', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(20, 'parametros', 'Parametros', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(21, 'historico-ingreso', 'Ingresos', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(22, 'historico-roles', 'Histórico de Roles', NULL, NULL, 1);
INSERT INTO RECURSO(id_recurso,nombre,descripcion,id_tipo_recurso,id_recurso_padre,id_aplicacion) VALUES(23, 'historico-usuarios', 'Histórico de Usuarios', NULL, NULL, 1);

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


-- USUARIO SUPER-ADMIN pass: admin1
INSERT INTO usuario(id_usuario,nombre, apellido, email, id_estado_usuario, id_estado_password, login, password, ldap, huella, fecha_modifica_password, fecha_modifica_usuario, fecha_inicio_usuario, fecha_fin_usuario) VALUES(1,'Nombre', 'Apellido', '1@g.com', 1, 1, 'admin', 'WLVETPG2JTpDF/4S2v9BGni9oKlSebHVdo6/XKYIKeeNqUToqRYKC21CjLIT6BNSWnJlDaxnuIh5OU/2JNpILw==', 0, NULL, SYSDATE(), SYSDATE(), SYSDATE(), NULL);
-- USR: SUPER-ADMIN - ROL: SUPER-ADMIN 
INSERT INTO rol_usuario(id_rol,id_usuario) values (1,1);
-- USR: SUPER-ADMIN - ROL: PUBLICO
INSERT INTO rol_usuario(id_rol,id_usuario) values (2,1);


--   Insercion de datos base usuario super-admin
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (45, 13 , 2 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-person</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Usuarios</string>\n  </entry>\n</map>',NULL);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (46, 13 , 1 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-note</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Consultar Usuarios</string>\n  </entry>\n</map>',45);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (47, 13 , 2 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-plusthick</string>\n  </entry>\n  <entry>\n    <string>stateId</string>\n    <string>crear</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Crear Usuario</string>\n  </entry>\n</map>',45);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (48, 17 , 7 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-calculator</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Operaciones</string>\n  </entry>\n</map>',NULL);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (49, 17 , 1 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-note</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Consultar Operación</string>\n  </entry>\n</map>',48);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (50, 17 , 2 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-plusthick</string>\n  </entry>\n  <entry>\n    <string>stateId</string>\n    <string>crear</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Crear Operación</string>\n  </entry>\n</map>',48);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (51, 15 , 5 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-wrench</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Recursos</string>\n  </entry>\n</map>',NULL);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (52, 15 , 1 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-note</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Consultar Recursos</string>\n  </entry>\n</map>',51);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (53, 15 , 2 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-plusthick</string>\n  </entry>\n  <entry>\n    <string>stateId</string>\n    <string>crear</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Crear Recurso</string>\n  </entry>\n</map>',51);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (54, 14 , 3 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-gear</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Roles</string>\n  </entry>\n</map>',NULL);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (55, 14 , 1 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-note</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Consultar Roles</string>\n  </entry>\n</map>',54);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (56, 14 , 2 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-plusthick</string>\n  </entry>\n  <entry>\n    <string>stateId</string>\n    <string>crear</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Crear Rol</string>\n  </entry>\n</map>',54);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (57, 16 , 4 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-contact</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Grupos</string>\n  </entry>\n</map>',NULL);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (58, 16 , 1 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-note</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Consultar Grupos</string>\n  </entry>\n</map>',57);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (59, 16 , 2 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-plusthick</string>\n  </entry>\n  <entry>\n    <string>stateId</string>\n    <string>crear</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Crear Grupo</string>\n  </entry>\n</map>',57);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (60, 18 , 7 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-flag</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Menú</string>\n  </entry>\n</map>',NULL);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (62, 12 , 9 ,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-locked</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Mi Cuenta</string>\n  </entry>\n</map>',NULL);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (63, 20 , 10,'<map>\n  <entry>\n    <string>icon</string>\n    <string>ui-icon-clipboard</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Parámetros</string>\n  </entry>\n</map>',NULL);
INSERT INTO recurso_menu (ID_RECURSO_MENU,ID_RECURSO,ORDEN,RECURSO_DATA,ID_MENU_PADRE) VALUES (64, 21 , 11, '<map><entry><string>icon</string>    <string>ui-icon-flag</string>  </entry>  <entry>    <string>label</string>    <string>Ingresos</string>  </entry></map>', NULL);

