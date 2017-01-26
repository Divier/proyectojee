package co.com.datatools.c2.enumeracion;

/**
 * Definicion de los campos q aplican para la entidad amnistia. <br>
 * 
 * @author luis.forero
 * 
 */
public enum EnumCampoAmnistia {

    AMNISTIA(0, 0), //
    ID_AMNISTIA(1, -1), //
    CONCEPTO(1, -1), //
    CODIGO_ORGANISMO(2, -1), //
    TIPO_AMNISTIA(3, -1), //
    TIPO_OBLIGACION(4, -1), //
    REQUIERE_RECURSO(5, 1), //
    NOMBRE(6, 255), //
    PORCENTAJE_AMNISTIA(7, -1), //
    NORMATIVIDAD(8, 300), //
    OBSERVACION(9, 300), //
    FECHA_INICIAL(10, -1), //
    FECHA_FINAL(11, -1), //
    FECHA_MAXIMA_OBLIGACION(12, -1), //
    ;
    /**
     * Identifica el codigo del campo en la base de datos.
     */
    int codigo;
    /**
     * Identifica la longitud del campo.
     */
    int longitud;

    private EnumCampoAmnistia(int codigo, int longitud) {
        this.codigo = codigo;
        this.longitud = longitud;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getLongitud() {
        return longitud;
    }

}
