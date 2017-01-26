package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Contiene los tipos de obligaciones existentes. corresponde a la tabla tipo_obligacion
 * 
 * @author robert.bautista
 */
public enum EnumTipoObligacion implements SearchableEnumeration<Integer> {
    COMPARENDO(1), //
    COACTIVO(2), //
    FINANCIACION(6), //
    ;

    /**
     * Contiene el pk de el tipo de obligacion
     */
    private int pk;

    /**
     * Constructor con pk
     * 
     * @param pk
     *            contiene el pk
     */
    private EnumTipoObligacion(int pk) {
        this.pk = pk;
    }

    @Override
    public Integer getValue() {
        return pk;
    }

}
