package co.com.datatools.c2.enumeracion;

/**
 * Listado de campos asociados a la entidad Resolucion
 * 
 * @author rodrigo.cruz
 * 
 */
public enum EnumCampoResolucion {

    RESOLUCION(0), //
    CODIGO_ORGANISMO(1), //
    ANO_RESOLUCION(2), //
    NUMERO_RESOLUCION(3), //
    FECHA_RESOLUCION(4), //
    TIPO_RESOLUCION(5), //
    ;

    private int codigo;

    private EnumCampoResolucion(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
