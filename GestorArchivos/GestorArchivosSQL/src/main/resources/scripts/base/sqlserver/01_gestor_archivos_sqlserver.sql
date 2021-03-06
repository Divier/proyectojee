--   -------------------------------------------------- 
--   Generated by Enterprise Architect Version 9.0.908
--   Created On : martes, 07 junio, 2016 
--   DBMS       : SQL Server 2008 
--   -------------------------------------------------- 


--  Drop Foreign Key Constraints 
IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = object_id('FK_version_data_documento_data_documento') AND OBJECTPROPERTY(id, 'IsForeignKey') = 1)
ALTER TABLE version_data_documento DROP CONSTRAINT FK_version_data_documento_data_documento
;


--  Drop Tables, Stored Procedures and Views 

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = object_id('version_data_documento') AND  OBJECTPROPERTY(id, 'IsUserTable') = 1)
DROP TABLE version_data_documento
;

IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = object_id('data_documento') AND  OBJECTPROPERTY(id, 'IsUserTable') = 1)
DROP TABLE data_documento
;


--  Create Tables 
CREATE TABLE version_data_documento ( 
	id_version int identity(1,1)  NOT NULL,
	codigo_documento int NOT NULL,    --  documento al cual pertenece la version 
	contenido_binario varbinary(max) NOT NULL,    --  guarda el contenido binario del documento 
	fecha datetime2(7),    --  fecha del sistema en que es cerado el contenido 
	version int NOT NULL
)
;

CREATE TABLE data_documento ( 
	codigo_documento int identity(1,1)  NOT NULL,    --  codigo unico asignado al documento por el cual se identifica y recupera el documento en el repositorio 
	descripcion varchar(512),    --  descripcion del contenido del documento 
	nombre varchar(100) NOT NULL,    --  nombre del documento almacenado en el repositorio 
	folder varchar(256),    --  indica la ubicacion detallada del documento en el repositorio 
	extension varchar(20),    --  extension del archivo que se está creando 
	nombre_real varchar(50)
)
;


--  Create Indexes 
ALTER TABLE version_data_documento
	ADD CONSTRAINT UQ_versi_data_docum_01 UNIQUE (codigo_documento, version)
;

ALTER TABLE data_documento
	ADD CONSTRAINT UQ_data_docum_nombr_01 UNIQUE (nombre, folder)
;

--  Create Primary Key Constraints 
ALTER TABLE version_data_documento ADD CONSTRAINT PK_version_data_documento 
	PRIMARY KEY CLUSTERED (id_version)
;

ALTER TABLE data_documento ADD CONSTRAINT PK_data_documento 
	PRIMARY KEY CLUSTERED (codigo_documento)
;



--  Create Foreign Key Constraints 
ALTER TABLE version_data_documento ADD CONSTRAINT FK_version_data_documento_data_documento 
	FOREIGN KEY (codigo_documento) REFERENCES data_documento (codigo_documento)
;









EXEC sp_addextendedproperty 'MS_Description', 'maneja las versiones del documento', 'Schema', dbo, 'table', version_data_documento
;

EXEC sp_addextendedproperty 'MS_Description', 'documento al cual pertenece la version', 'Schema', dbo, 'table', version_data_documento, 'column', codigo_documento
;

EXEC sp_addextendedproperty 'MS_Description', 'guarda el contenido binario del documento', 'Schema', dbo, 'table', version_data_documento, 'column', contenido_binario
;

EXEC sp_addextendedproperty 'MS_Description', 'fecha del sistema en que es cerado el contenido', 'Schema', dbo, 'table', version_data_documento, 'column', fecha
;


EXEC sp_addextendedproperty 'MS_Description', 'Tabla temporal encargada de almacenar los binarios de los documentos', 'Schema', dbo, 'table', data_documento
;
EXEC sp_addextendedproperty 'MS_Description', 'codigo unico asignado al documento por el cual se identifica y recupera el documento en el repositorio', 'Schema', dbo, 'table', data_documento, 'column', codigo_documento
;

EXEC sp_addextendedproperty 'MS_Description', 'descripcion del contenido del documento', 'Schema', dbo, 'table', data_documento, 'column', descripcion
;

EXEC sp_addextendedproperty 'MS_Description', 'nombre del documento almacenado en el repositorio', 'Schema', dbo, 'table', data_documento, 'column', nombre
;

EXEC sp_addextendedproperty 'MS_Description', 'indica la ubicacion detallada del documento en el repositorio', 'Schema', dbo, 'table', data_documento, 'column', folder
;

EXEC sp_addextendedproperty 'MS_Description', 'extension del archivo que se está creando', 'Schema', dbo, 'table', data_documento, 'column', extension
;
