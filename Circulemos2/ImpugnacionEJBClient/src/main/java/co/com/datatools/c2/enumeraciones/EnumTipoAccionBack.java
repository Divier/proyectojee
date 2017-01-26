package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoAccionBack implements SearchableEnumeration<Integer> {
    PRUEBAS(1), //
    OBSERVACIONES(2), //
    ;

    /**
     * Contiene el id del tipo de accion back.
     */
    private int id;

    /**
     * Constructor con el id del tipo de accion back.
     * 
     * @param id
     *            id del tipo de accion back
     */
    private EnumTipoAccionBack(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public Integer getValue() {
        return this.id;
    }
}
