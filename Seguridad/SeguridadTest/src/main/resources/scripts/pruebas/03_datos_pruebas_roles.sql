--   --------------------------------------------------
--   Insercion de datos de logica de negocio basicos
--   03_datos_pruebas_roles.sql
--   -------------------------------------------------- 

-- ROL PARA PRUEBA 
INSERT INTO ROL(id_rol,nombre,descripcion,activo,id_rol_padre) VALUES (1000,'Rol prueba 000','Rol Prueba',1,NULL);

INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(7,1000);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(8,1000);
INSERT INTO permiso_recurso_operacion(id_recurso_operacion,id_rol) values(9,1000);

INSERT INTO historico_rol( id_rol, id_rol_padre, fecha_inicio_rol, fecha_fin_rol, xml_historico, id_xsd_historico)VALUES(1000, NULL, '2014-04-21 15:21:32', NULL, '<co.com.datatools.seguridad.entidades.Rol>\n  <idRol>1000</idRol>\n  <nombre>Rol prueba 000</nombre>\n  <descripcion>Rol prueba</descripcion>\n  </co.com.datatools.seguridad.entidades.Rol>', 1);

--ROL PARA CONSULTA DE HISTORICO
INSERT INTO ROL(id_rol,nombre,descripcion,activo,id_rol_padre) VALUES (1001,'Rol prueba 1001','Rol Prueba',1,NULL);
INSERT INTO historico_rol( id_rol, id_rol_padre, fecha_inicio_rol, fecha_fin_rol, xml_historico, id_xsd_historico)VALUES(1001, NULL, '2014-04-21 15:21:32', NULL, '<RolDetalleDto><idRol>1001</idRol><nombre>Rol prueba 000</nombre><descripcion>Rol prueba</descripcion><activo>true</activo><recursosAplicacion><entry><string>SEGURIDAD</string><list><RecursoDetalleDto><idRecurso>16</idRecurso><nombreRecurso>grupos</nombreRecurso><descripcion>Grupos</descripcion><hijos/><operaciones><OperacionDto><idOperacion>24</idOperacion><nombreOperacion>consultar</nombreOperacion></OperacionDto><OperacionDto><idOperacion>23</idOperacion><nombreOperacion>crear</nombreOperacion></OperacionDto><OperacionDto><idOperacion>26</idOperacion><nombreOperacion>editar</nombreOperacion></OperacionDto><OperacionDto><idOperacion>25</idOperacion><nombreOperacion>eliminar</nombreOperacion></OperacionDto><OperacionDto><idOperacion>22</idOperacion><nombreOperacion>ingresar</nombreOperacion></OperacionDto></operaciones></RecursoDetalleDto></list></entry> </recursosAplicacion><grupos><GrupoDto><idGrupo>2</idGrupo><nombre>Grupo1-Rol</nombre><descripcion>Grupo1-Rol aplica solo roles</descripcion><activo>true</activo><clase>Roles</clase></GrupoDto></grupos></RolDetalleDto>', 1);


