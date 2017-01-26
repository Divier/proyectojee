package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumCamposInconsistencias implements SearchableEnumeration<Integer> {

    NUMERO_COMPARENDO(1, "NUMERO DE CITACION"), //
    NOMBRE_ORIGEN_COMPARENDO(2, "NOMBRE DE ORIGEN DE LA CITACION"), //
    CODIGO_INFRACCION(3, "CODIGO DE INFRACCION"), //
    FECHA_REGISTRO(4, "FECHA DE REGISTRO"), //
    FECHA_HORA_IMPOSICION_COMPARENDO(5, "FECHA Y HORA DE IMPOSICION CITACION"), //
    NOMBRE_CAMPO_CON_INCONSISTENCIAS(6, "NOMBRE DE CAMPO CON INCONSISTENCIAS"), //
    ERROR_PROCESAMIENTO(7, "ERROR DE PROCESAMIENTO"), //
    PLACA_VEHICULO(8, "PLACA VEHICULO"), //
    TIPO_DOCUMENTO_INFRACTOR(9, "TIPO DE DOCUMENTO DEL INFRACTOR"), //
    NUMERO_DOCUMENTO_INFRACTOR(10, "NUMERO DOCUMENTO DEL INFRACTOR"), //
    NOMBRE_INFRACTOR(11, "NOMBRE 1 DEL INFRACTOR"), //
    APELLIDO_INFRACTOR(12, "APELLIDO 1 DEL INFRACTOR"), //
    USUARIO(13, "USUARIO"), //
    ;

    private int codigo;
    private String nombre;

    EnumCamposInconsistencias(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }
}