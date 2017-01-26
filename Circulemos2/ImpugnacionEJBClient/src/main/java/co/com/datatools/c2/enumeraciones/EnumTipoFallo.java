package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion de los tipos de fallo
 * 
 * @author julio.pinzon 2016-06-13
 *
 */
public enum EnumTipoFallo implements SearchableEnumeration<Integer> {
    ABSOLUTORIO(1), //
    CONDENATORIO(2), //
    CONDENATORIO_PARCIAL(3), //
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
    private EnumTipoFallo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return this.codigo;
    }

}
