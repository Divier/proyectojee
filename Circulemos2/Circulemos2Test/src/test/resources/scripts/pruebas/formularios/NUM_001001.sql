-- 
-- persona_juridica
-- 
INSERT INTO persona_juridica (ID_PERSONA_JURIDICA, DIGITO_VERIFICACION, NOMBRE_COMERCIAL, SIGLA, ID_CLASE_ACTIVIDAD_ECONOMICA, ID_MUNICIPIO) VALUES ('-1', '6', 'PERSONA JUR�DICA', 'PJ', '379', '149');
-- 
-- responsable_formulario
-- 
INSERT INTO responsable_formulario (ID_RESPONSABLE_FORMULARIO, FECHA_INICIO_VINCULA, FECHA_FIN_VINCULA, CODIGO_ORGANISMO, ID_TIPO_RESPONSABLE, CORREO_RESPONSABLE_FORMULARIO) VALUES ('-1', CURRENT_DATE - INTERVAL 1 DAY, CURRENT_DATE + INTERVAL 1 DAY, '11001', '1', 'rodrigo.cruz@datatools.com.co');
INSERT INTO responsable_formulario (ID_RESPONSABLE_FORMULARIO, FECHA_INICIO_VINCULA, FECHA_FIN_VINCULA, CODIGO_ORGANISMO, ID_TIPO_RESPONSABLE, CORREO_RESPONSABLE_FORMULARIO) VALUES ('-2', CURRENT_DATE - INTERVAL 1 DAY, CURRENT_DATE + INTERVAL 1 DAY, '11001', '2', 'rodrigo.cruz.hz@gmail.com');
-- 
-- stock_tipo_responsable
-- 
INSERT INTO stock_tipo_responsable (ID_STOCK_TIPO_RESPONSABLE, ID_TIPO_FORMULARIO, ID_TIPO_RESPONSABLE, STOCK_MINIMO, STOCK_MAXIMO, ESTADO_STOCK, CODIGO_ORGANISMO) VALUES ('3', '2', '1', '50', '100', '1', '11001');
-- 
-- unificacion_responsable
-- 
INSERT INTO unificacion_responsable (ID_UNIFICACION_RESPONSABLE, ID_PERSONA, CODIGO_ORGANISMO, ID_RESPONSABLE_FORMULARIO) VALUES ('-1', '-1', '11001', '-1');
--
-- numeracion_formulario
--
INSERT INTO numeracion_formulario (ID_NUMERACION, ID_TIPO_FORMULARIO, FECHA_VIGENCIA_INICIAL, FECHA_VIGENCIA_FINAL, DIGITOS, ACTIVO, TRAMA) VALUES ('-1', '2', '2015-01-01', '2015-01-31', '5', '1', 'AA000');
--
-- detalle_numeracion
--
INSERT INTO detalle_numeracion (ID_DETALLE_NUMERACION, ID_NUMERACION, DIGITO, CARACTER_INICIO, CARACTER_FIN, INCREMENTAL, NUMERICO) VALUES ('-1', '-1', '1', 'A', 'Z', '1', '0');
INSERT INTO detalle_numeracion (ID_DETALLE_NUMERACION, ID_NUMERACION, DIGITO, CARACTER_INICIO, CARACTER_FIN, INCREMENTAL, NUMERICO) VALUES ('-2', '-1', '2', 'A', 'Z', '1', '0');
INSERT INTO detalle_numeracion (ID_DETALLE_NUMERACION, ID_NUMERACION, DIGITO, CARACTER_INICIO, CARACTER_FIN, INCREMENTAL, NUMERICO) VALUES ('-3', '-1', '3', '0', '9', '1', '1');
INSERT INTO detalle_numeracion (ID_DETALLE_NUMERACION, ID_NUMERACION, DIGITO, CARACTER_INICIO, CARACTER_FIN, INCREMENTAL, NUMERICO) VALUES ('-4', '-1', '4', '0', '9', '1', '1');
INSERT INTO detalle_numeracion (ID_DETALLE_NUMERACION, ID_NUMERACION, DIGITO, CARACTER_INICIO, CARACTER_FIN, INCREMENTAL, NUMERICO) VALUES ('-5', '-1', '5', '0', '9', '1', '1');
-- 
-- rango_formulario
--
INSERT INTO rango_formulario (ID_RANGO_FORMULARIO, CODIGO_ORGANISMO, ID_TIPO_FORMULARIO, ID_NUMERACION, NUMERO_INICIAL, NUMERO_FINAL, FECHA_AUTORIZACION, CANTIDAD_TOTAL, CANTIDAD_DISPONIBLE, ALERTA_STOCK) VALUES ('-1', '11001', '2', '-1', 'AB700', 'AB800', '2015-01-14', '70', '30', '0');