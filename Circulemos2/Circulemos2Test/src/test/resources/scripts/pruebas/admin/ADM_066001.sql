INSERT INTO configuracion (id_configuracion,codigo,nombre,descripcion,plantilla) VALUES (
-1,
'Prueba',
'Prueba',
'Prueba',
'<?xml version="1.0" encoding="UTF-8"?>
<configuracion_negocio xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" id="50" nombre="nombre0">
    <entrada>
        <NUMERO obligatorio="true">
            <codigo>entrada1</codigo>
            <nombre>Llave [Numero] </nombre>
            <descripcion/>
        </NUMERO>
        <CADENA obligatorio="true" longitud="5">
            <codigo>entrada2</codigo>
            <nombre>Llave [Cadena]</nombre>
            <descripcion/>
        </CADENA>
    </entrada>
    <salida>
        <CATALOGOSIMPLE obligatorio="true">
            <codigo>tipoPropietario</codigo>
            <nombre>Tipo Propietario 1</nombre>
            <descripcion>Desc[CatSimple]</descripcion>
            <nombreClaseCatalogo>TipoPropietario</nombreClaseCatalogo>
        </CATALOGOSIMPLE>
        <CATALOGOSIMPLE obligatorio="true" seleccion="2">
            <codigo>tipoPropietario2</codigo>
            <nombre>Tipo Propietario 1</nombre>
            <descripcion>Desc[CatSimple]</descripcion>
            <nombreClaseCatalogo>TipoPropietario</nombreClaseCatalogo>
        </CATALOGOSIMPLE>
    </salida>
</configuracion_negocio>');

INSERT INTO configuracion (id_configuracion,codigo,nombre,descripcion,plantilla) VALUES (
-2,
'Rango',
'Rango',
'Rango',
'<?xml version="1.0" encoding="UTF-8"?>
<configuracion_negocio xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "configuracion.xsd" id="50" nombre="nombre0">
    <entrada>
        <RANGOFECHA>
            <codigo>periodoEntrada</codigo>
            <INICIAL obligatorio="true">
                <codigo>inicialEntrada</codigo>
                <nombre>Fecha Inicial Entrada</nombre>
                <descripcion/>
            </INICIAL>
            <FINAL>
                <codigo>finalEntrada</codigo>
                <nombre>Fecha Final Entrada</nombre>
                <descripcion/>
            </FINAL>
        </RANGOFECHA>
    </entrada>
    <salida>
        <NUMERO>
            <codigo>numeroSalida</codigo>
            <nombre>Variable Salida</nombre>
            <descripcion/>
            <validacion>
                <operador>MAYOR</operador>
                <operando>
                    <constante>10</constante>
                </operando>
            </validacion>
        </NUMERO>
        <CADENA longitud="5">
            <codigo>cadenaSalida</codigo>
            <nombre>Cadena Salida</nombre>
            <descripcion/>
        </CADENA>
    </salida>
</configuracion_negocio>');


INSERT INTO valor_configuracion (id_configuracion,valor) VALUES (
-2,
'<?xml version="1.0" encoding="UTF-8"?>
<valor_configuracion_negocio>
    <campo>
        <codigo>inicialEntrada</codigo>
        <valor>2015-01-01</valor>
    </campo>
    <campo>
        <codigo>finalEntrada</codigo>
        <valor>##NULL##</valor>
    </campo>
    <campo>
        <codigo>numeroSalida</codigo>
        <valor>2015</valor>
    </campo>
    <campo>
        <codigo>cadenaSalida</codigo>
        <valor>2015</valor>
    </campo>
</valor_configuracion_negocio>');
INSERT INTO valor_configuracion (id_configuracion,valor) VALUES (
-2,
'<?xml version="1.0" encoding="UTF-8"?>
<valor_configuracion_negocio>
    <campo>
        <codigo>inicialEntrada</codigo>
        <valor>2014-01-01</valor>
    </campo>
    <campo>
        <codigo>finalEntrada</codigo>
        <valor>2014-12-31</valor>
    </campo>
    <campo>
        <codigo>numeroSalida</codigo>
        <valor>2014</valor>
    </campo>
    <campo>
        <codigo>cadenaSalida</codigo>
        <valor>2014</valor>
    </campo>
</valor_configuracion_negocio>');
INSERT INTO valor_configuracion (id_configuracion,valor) VALUES (
-2,
'<?xml version="1.0" encoding="UTF-8"?>
<valor_configuracion_negocio>
    <campo>
        <codigo>inicialEntrada</codigo>
        <valor>2013-01-01</valor>
    </campo>
    <campo>
        <codigo>finalEntrada</codigo>
        <valor>2013-12-31</valor>
    </campo>
    <campo>
        <codigo>numeroSalida</codigo>
        <valor>2013</valor>
    </campo>
    <campo>
        <codigo>cadenaSalida</codigo>
        <valor>2013</valor>
    </campo>
</valor_configuracion_negocio>');