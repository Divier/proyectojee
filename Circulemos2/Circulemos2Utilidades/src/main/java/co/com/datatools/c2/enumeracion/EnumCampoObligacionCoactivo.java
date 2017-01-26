package co.com.datatools.c2.enumeracion;

/**
 * Enumeracion que identifica los campos que puede contener una obligacion de coactivo.
 * 
 * @author Dixon.Alvarez
 * 
 */
public enum EnumCampoObligacionCoactivo {

    /**
     * Campo que hace referencia al objeto número de la obligación.
     */
    NUMERO_OBLIGACION(1), //
    /**
     * Campo que hace referencia al valor de la obligación
     */
    VALOR_OBLIGACION(2), //
    /**
     * Campo que hace referencia al valor de los intereses moratorios de la obligacion
     */
    VALOR_INTERES_MORA(3), //
    /**
     * Campo que hace referencia a la direccion del deudor
     */
    DIRECCION_DEUDOR(4), //
    ;
    /**
     * Identifica la posicion que se define del determinado campo.
     */
    private int index;

    private EnumCampoObligacionCoactivo(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
