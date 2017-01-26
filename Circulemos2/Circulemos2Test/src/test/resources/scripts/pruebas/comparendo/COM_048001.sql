-- 
--  infraccion
-- 
INSERT INTO infraccion (ID_INFRACCION, ID_TIPO_INFRACCION, NUMERAL_INFRACCION, CODIGO_INFRACCION) VALUES ('-1', '1', '1', 'A1');

-- 
--  configuracion_infraccion
-- 
INSERT INTO configuracion_infraccion(id_configuracion_infraccion, id_infraccion, descripcion, id_normatividad, articulo, fecha_articulo, requiere_placa, infractor_obligatorio, dias_genera_cartera, dia_habil_genera_cartera, id_entidad_agente, id_causal_infraccion, id_ordenamiento_pais, fecha_inicio_vigencia, fecha_fin_vigencia, genera_cartera, aplica_embriaguez, aplica_descuento) 
VALUES (-13, -1, 'NO DETENERSE ANTE UNA LUZ ROJA O AMARILLA DE SEMÁFORO, UNA SEÑAL DE PARE O UN SEMÁFORO INTERMITENT', 2, 'LEY 1383 DE 2010', '2010-03-16', 0, 0, 0, 0, 1, 1, 1, '2008-11-06', NULL, 0, 0, 0);
-- 
--  tarifa_infraccion
-- 
INSERT INTO tarifa_infraccion (id_tarifa_infraccion, id_infraccion, porcentaje_descuento, valor_infraccion,
valor_descuento,valor_comparendo,valor_cia,fecha_inicio_vigencia,fecha_final_vigencia,tarifa_confirmada,
fecha_creacion,fecha_confirmacion) 
VALUES ('-1','-1','0.00','85900.00','85900.00','85900.00','0.00','2015-01-01','2015-12-31','1','2014-12-19','2014-12-23'); 	
