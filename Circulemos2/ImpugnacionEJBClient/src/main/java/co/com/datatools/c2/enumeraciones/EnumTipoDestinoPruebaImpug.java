package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion de los tipos de fallo
 * 
 * @author julio.pinzon 2016-06-13
 *
 */
public enum EnumTipoDestinoPruebaImpug implements SearchableEnumeration<Integer> {
    CONTROL_TRANSITO(1), //
    INFORMATICA(2), //
    TRANSP_PUBLICO(3), //
    REG_TEC_VEHICULAR(4), //
    OTRAS(5), //
    ;

    /**
     * Contiene el código del tipo de fallo.
     */
    private int codigo;

    /**
     * Constructor con el código del tipo de fallo.
     * 
     * @param codigo
     *            código del tipo de fallo
     */
    private EnumTipoDestinoPruebaImpug(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return this.codigo;
    }

}
