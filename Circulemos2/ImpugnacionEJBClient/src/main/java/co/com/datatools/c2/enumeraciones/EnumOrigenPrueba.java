package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion de los origenes de prueba
 * 
 * 
 */
public enum EnumOrigenPrueba implements SearchableEnumeration<Integer> {
    CIUDADANO(1), //
    TERCEROS(2), //
    ;

    /**
     * Contiene el código del origen de prueba.
     */
    private int codigo;

    /**
     * Constructor con el código del origen de prueba.
     * 
     * @param codigo
     *            código del origen de prueba
     */
    private EnumOrigenPrueba(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return this.codigo;
    }

}
