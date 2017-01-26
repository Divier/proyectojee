--   --------------------------------------------------
--   Insercion de datos de logica de negocio basicos
--   05_datos_menu.sql
--   -------------------------------------------------- 

-- OPCIONES DE MENU PARA PRUEBA 
INSERT INTO recurso_menu(id_recurso_menu,id_recurso,orden,recurso_data,id_menu_padre)
VALUES(1, 13, 1, '<map>\n  <entry>\n    <string>atributo0</string>\n    <string>valor0</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Opcion 1</string>\n  </entry>\n</map>', NULL);

INSERT INTO recurso_menu(id_recurso_menu,id_recurso,orden,recurso_data,id_menu_padre)
VALUES(2, 13, 1, '<map>\n  <entry>\n    <string>atributo0</string>\n    <string>valor0</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Opcion 1.1</string>\n  </entry>\n</map>', 1);

INSERT INTO recurso_menu(id_recurso_menu,id_recurso,orden,recurso_data,id_menu_padre)
VALUES(3, 13, 2, '<map>\n  <entry>\n    <string>atributo0</string>\n    <string>valor0</string>\n  </entry>\n  <entry>\n    <string>label</string>\n    <string>Opcion 1.2</string>\n  </entry>\n</map>', 1);

