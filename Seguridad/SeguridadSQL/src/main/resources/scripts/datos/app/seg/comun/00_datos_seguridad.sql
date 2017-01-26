--   --------------------------------------------------
--   Insercion de datos de catalogos basicos
--   00_datos_seguridad.sql
--   -------------------------------------------------- 

-- TABLA APLICACION
INSERT INTO APLICACION (id_aplicacion,nombre_aplicacion) VALUES (1,'SEGURIDAD')
;

-- TABLA ESTADO_USUARIO
INSERT INTO ESTADO_USUARIO (id_estado,nombre) VALUES (1,'Activo')
;
INSERT INTO ESTADO_USUARIO (id_estado,nombre) VALUES (3,'Cancelado')
;
INSERT INTO ESTADO_USUARIO (id_estado,nombre) VALUES (2,'Inactivo')
;

-- TABLA TIPO_DATO
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (1,'Numerico Entero')
;
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (2,'Numerico Decimal')
;
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (3,'Texto')
;
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (4,'Fecha')
;
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (5,'Hora')
;
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (6,'Fecha y hora')
;
INSERT INTO TIPO_DATO (id_tipo_dato,nombre_tipo_dato) VALUES (7,'SI/NO')
;

-- TABLA PARAMETRO_SEGURIDAD
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'PW_LONGITUD_MIN',8,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'PW_LONGITUD_MAX',16,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'PW_OBLIGA_NUMEROS','SI',1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'PW_OBLIGA_LETRAS','SI',1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'PW_OBLIGA_MAYUSCULAS','SI',1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'PW_OBLIGA_CARACTERES_ESPECIALES','SI',1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'PW_CARACTERES_ESPECIALES','!@#$^&*()_-+ =[]\|,.',1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_HORAS_VIGENCIA_BLOQUEO_PW',6,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'PW_CANTIDAD_DIAS_VIGENCIA',60,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'INITIAL_CONTEXT_FACTORY','com.sun.jndi.ldap.LdapCtxFactory',0)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'SECURITY_AUTHENTICATION','simple',0)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'ALGORITMO_GENERAR_LOGIN','importPackage(java.util);(function(e){resp=new java.util.ArrayList(3);nombres=e.nombres.toLowerCase().split(\" \");apellidos=e.apellidos.toLowerCase().split(\" \");login=\"\";for(var t=0;t<nombres.length&&t<2;t++){login+=nombres[t].charAt(0)}for(var n=0;n<apellidos.length&&n<2;n++){if(n==0){login+=apellidos[n]}else{login+=apellidos[n].charAt(0)}}resp.add(login);resp.add(login+Math.floor(Math.random()*100));resp.add(login+Math.floor(Math.random()*100));return resp})',1);
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'NIVELES_HERENCIA_ROLES',1,0)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'DESBLOQUEO_PW_AUTOMATICO','SI',1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_PW_HISTORICO',4,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_INTENTOS_FALLIDOS_PW',3,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_HORAS_VALIDA_INTENTOS_FALLIDOS_PW',6,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'PROVIDER_URL',NULL,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'USERS_PATH',NULL,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'AUTHORIZED_GROUP_PATH',NULL,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'SECURITY_PRINCIPAL',NULL,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'SECURITY_CREDENTIALS',NULL,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'PERMITE_AUTENTICACION_LDAP','NO',1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_MIEMBRODE_LDAP',NULL,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_IDUSUARIO_LDAP',NULL,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_LOGIN_LDAP',NULL,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_HORAS_VIGENCIA_VINCULO_TEMPORAL',8,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'ASUNTO_CORREO_RECUPERACION_PW','Olvido de contraseña',1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'CUERPO_CORREO_RECUPERACION_PW','<br/>Estimado Usuario,<br/><br/>Para restablecer su contraseña haga clic en el siguiente link. También puede copiarlo y pegarlo en la barra de direcciones de su navegador de internet.<br/><br/><strong>{0}</strong><br/><br/><strong>Tenga en cuenta que este link expira {1} horas después de la hora en que fue enviado.</strong>',1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_NOMBRES_LDAP',NULL,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_CORREO_LDAP',NULL,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'NOMBRE_ATRIBUTO_APELLIDOS_LDAP',NULL,1)
;
--INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'PERMITE_VARIAS_SESIONES_ABIERTAS','NO',1)
--;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (3,'URL_APLICACION','<map><entry><string>SEGURIDAD</string><string>http://localhost:8080/SeguridadWEB</string></entry><entry><string>CIRCULEMOS2.0</string><string>http://localhost:8080/Circulemos2PWEB</string></entry></map>',1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_DIAS_CONSULTA_INGRESOS',360,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_DIAS_CONSULTA_ROLES',360,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (1,'CANTIDAD_DIAS_CONSULTA_USUARIOS',360,1)
;
INSERT INTO PARAMETRO_SEGURIDAD(id_tipo_dato,nombre_PARAMETRO_SEGURIDAD,valor_PARAMETRO_SEGURIDAD,editable) VALUES (7,'INSTALADOR_EJECUTADO','NO',0)
;


-- TABLA OPERACION
INSERT INTO OPERACION (id_operacion,nombre) VALUES (1,'ingresar')
;
INSERT INTO OPERACION (id_operacion,nombre) VALUES(2,'crear')
;
INSERT INTO OPERACION (id_operacion,nombre) VALUES(3,'consultar')
;
INSERT INTO OPERACION (id_operacion,nombre) VALUES(4,'eliminar')
;
INSERT INTO OPERACION (id_operacion,nombre) VALUES(5,'editar')
;

-- TABLA ESTADO_PASSWORD
INSERT INTO ESTADO_PASSWORD (id_estado,nombre) values(1,'Activo')
;
INSERT INTO ESTADO_PASSWORD (id_estado,nombre) values(2,'Vencido')
;
INSERT INTO ESTADO_PASSWORD (id_estado,nombre) values(3,'Temporal')
;
INSERT INTO ESTADO_PASSWORD (id_estado,nombre) values(4,'Bloqueado')
;

-- TABLA ESTADO_INGRESO
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(1, 'Exitoso');
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(2, 'Fallido por contraseña errónea');
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(3, 'Fallido por contraseña bloqueada');
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(4, 'Fallido por contraseña vencida');
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(5, 'Fallido por usuario no activo');
INSERT INTO estado_ingreso(id_estado_ingreso, nombre) VALUES(6, 'Fallido por contraseña temporal');

-- TABLA xsd_historico
-- Rol
INSERT INTO xsd_historico(id_xsd_historico,contenido_xsd)VALUES(1,'<xs:schema attributeFormDefault="unqualified"
  	elementFormDefault="qualified" xmlns:xs="http://www.w3.org/2001/XMLSchema">
  	<!-- INICIO Definicion del Tipo para el detalle del recurso -->
  	<xs:complexType name="recursoDetalle">
  		<xs:sequence>
  			<xs:element type="xs:integer" name="idRecurso" />
  			<xs:element type="xs:string" name="nombreRecurso" />
  			<xs:element type="xs:string" name="descripcion" minOccurs="0" />
  			<xs:element type="xs:string" name="hijos" minOccurs="0" />
  			<xs:element name="operaciones">
  				<xs:complexType>
  					<xs:sequence>
  						<xs:element name="OperacionDto" minOccurs="1"
  							maxOccurs="unbounded">
  							<xs:complexType>
  								<xs:sequence>
  									<xs:element name="idOperacion" type="xs:integer" />
  									<xs:element name="nombreOperacion" type="xs:string" />
  								</xs:sequence>
  							</xs:complexType>
  						</xs:element>
  					</xs:sequence>
  				</xs:complexType>
  			</xs:element>
  		</xs:sequence>
  	</xs:complexType>
  	<!-- FIN Definicion del Tipo para el detalle del recurso -->
  	<xs:complexType name="RolDetalleDto">
  		<xs:sequence>
  			<xs:element type="xs:integer" name="idRol" />
  			<xs:element type="xs:string" name="nombre" />
  			<xs:element type="xs:string" name="descripcion" minOccurs="0" />
  			<xs:element type="xs:boolean" name="activo" /> 			
  			<xs:element name="recursosAplicacion">
  				<xs:complexType>
  					<xs:sequence>
  						<xs:element name="entry" maxOccurs="unbounded"
  							minOccurs="1">
  							<xs:complexType>
  								<xs:sequence>
  									<xs:element type="xs:string" name="string" />
  									<xs:element name="list">
  										<xs:complexType>
  											<xs:sequence>
  												<xs:element name="RecursoDetalleDto" maxOccurs="unbounded"
  													minOccurs="1" type="recursoDetalle">
  												</xs:element>
  											</xs:sequence>
  										</xs:complexType>
  									</xs:element>
  								</xs:sequence>
  							</xs:complexType>
  						</xs:element>
  					</xs:sequence>
  				</xs:complexType>
  			</xs:element>
  			<xs:element name="rolPadre" type="RolDetalleDto" minOccurs="0" nillable="true">
  			</xs:element>
  			<xs:element name="grupos" minOccurs="0" nillable="true">
  				<xs:complexType>
  					<xs:sequence>
  						<xs:element name="GrupoDto" maxOccurs="unbounded"
  							minOccurs="0">
  							<xs:complexType>
  								<xs:sequence>
  									<xs:element type="xs:integer" name="idGrupo" />
  									<xs:element type="xs:string" name="nombre" />
  									<xs:element type="xs:string" name="descripcion" />
  									<xs:element type="xs:boolean" name="activo" />
  									<xs:element name="clase">
  										<xs:simpleType>
  											<xs:restriction base="xs:string">
  												<xs:enumeration value="Usuarios" />
  												<xs:enumeration value="Roles" />
  											</xs:restriction>
  										</xs:simpleType>
  									</xs:element>
  								</xs:sequence>
  							</xs:complexType>
  						</xs:element>
  					</xs:sequence>
  				</xs:complexType>
  			</xs:element>
  		</xs:sequence>
  	</xs:complexType>
  	<!-- INICIO Definicion estructura del XML -->
  	<xs:element name="RolDetalleDto" type="RolDetalleDto">
  	</xs:element>
  	<!-- FIN Definicion estructura del XML -->
  </xs:schema>');
  
-- Usuario
INSERT INTO xsd_historico(id_xsd_historico,contenido_xsd)VALUES(2,'
 <xs:schema attributeFormDefault="unqualified"
  	elementFormDefault="qualified" xmlns:xs="http://www.w3.org/2001/XMLSchema">
  	<xs:element
  		name="UsuarioDetalleDto">
  		<xs:complexType>
  			<xs:sequence>
  				<xs:element type="xs:integer" name="id" />
  				<xs:element type="xs:string" name="nombres" />
  				<xs:element type="xs:string" name="apellidos" />
  				<xs:element type="xs:string" name="email" />
  				<xs:element type="xs:string" name="estadoUsuario" />
  				<xs:element type="xs:string" name="estadoPassword" />
  				<xs:element type="xs:boolean" name="autenticacionConLdap" />
  				<xs:element type="xs:string" name="fechaInicioUsuario" />
  				<xs:element type="xs:string" name="fechaFinUsuario" minOccurs="0" />
  				<xs:element type="xs:string" name="fechaBloqueoPassword" minOccurs="0" />
  				<xs:element type="xs:string" name="password" minOccurs="0"/>
  				<xs:element type="xs:string" name="login" />
 				<xs:element type="xs:string" name="fechaModPass" minOccurs="0" />
  				<xs:element type="xs:string" name="fechaModUsuario" />					
  				<xs:element name="roles">
  					<xs:complexType>
  						<xs:sequence>
  							<xs:element
  								name="RolDetalleDto"
  								maxOccurs="unbounded" minOccurs="1">
  								<xs:complexType>
  									<xs:sequence>
  										<xs:element type="xs:integer" name="idRol" />
  										<xs:element type="xs:string" name="nombre" />
  										<xs:element type="xs:string" name="descripcion" />
  										<xs:element type="xs:boolean" name="activo" />
  									</xs:sequence>
  								</xs:complexType>
  							</xs:element>
  						</xs:sequence>
  					</xs:complexType>
  				</xs:element>
  				<xs:element name="grupos">
  					<xs:complexType>
  						<xs:sequence>
  							<xs:element
  								name="GrupoDto"
  								maxOccurs="unbounded" minOccurs="0">
  								<xs:complexType>
  									<xs:sequence>
  										<xs:element type="xs:integer" name="idGrupo" />
  										<xs:element type="xs:string" name="nombre" />
  										<xs:element type="xs:string" name="descripcion" />
  										<xs:element type="xs:boolean" name="activo" />
  										<xs:element name="clase">
  											<xs:simpleType>
  												<xs:restriction base="xs:string">
  													<xs:enumeration value="Usuarios" />
  													<xs:enumeration value="Roles" />
  												</xs:restriction>
  											</xs:simpleType>
  										</xs:element>
  									</xs:sequence>
  								</xs:complexType>
  							</xs:element>
  						</xs:sequence>
  					</xs:complexType>
  				</xs:element>
  			</xs:sequence>
  		</xs:complexType>
  	</xs:element>
  </xs:schema>');
  
-- Ingreso usuario
INSERT INTO xsd_historico(id_xsd_historico,contenido_xsd)VALUES(3,'<xs:schema attributeFormDefault="unqualified" elementFormDefault="qualified" xmlns:xs="http://www.w3.org/2001/XMLSchema"> <xs:element name="actividadIngreso"> <xs:complexType> <xs:sequence> <xs:element name="InfoAutorizacion" maxOccurs="unbounded" minOccurs="0"> <xs:complexType> <xs:sequence> <xs:element type="xs:string" name="nombreRecurso" /> <xs:element type="xs:string" name="nombreOperacion" minOccurs="0" /> <xs:element type="xs:string" name="horaSolicitud" /> <xs:element type="xs:boolean" name="permitido" /> </xs:sequence> </xs:complexType> </xs:element> </xs:sequence> </xs:complexType> </xs:element> </xs:schema>');

