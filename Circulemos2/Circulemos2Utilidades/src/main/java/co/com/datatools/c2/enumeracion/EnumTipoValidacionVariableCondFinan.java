package co.com.datatools.c2.enumeracion;

/**
 * Tipos de validaciones posibles vara un valor de una determinada variable de condicion de financiacion
 * 
 * @author luis.forero
 * 
 */
public enum EnumTipoValidacionVariableCondFinan {

    INDIVIDUAL(1), //
    RANGO(2), //
    COMPARACION_VARIABLES(3), //
    ;

    private int codigo;// Identifica el tipo de validacion que se lleva a cabo

    private EnumTipoValidacionVariableCondFinan(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
