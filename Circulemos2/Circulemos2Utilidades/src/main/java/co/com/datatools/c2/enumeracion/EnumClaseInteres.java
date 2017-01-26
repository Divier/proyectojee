package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Permite identificar los tipos de clases de interes existentes.
 * 
 * @author luis.forero
 * 
 */
public enum EnumClaseInteres implements SearchableEnumeration<Integer> {
    MORA_COMPARENDOS(1), //
    MORA_FINANCIACIONES(2), //
    FINANCIACIONES(3), //
    ;

    private int id;

    private EnumClaseInteres(int id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
