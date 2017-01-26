package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion que permite identificar los posibles mandos existentes en el sistema
 * 
 * @author luis.forero
 * 
 */
public enum EnumMando implements SearchableEnumeration<Integer> {
    AGENTE(1), //
    PATRULLERO(2), //
    SUBINTENDENTE(3), //
    SARGENTO_PRIMERO(4), //
    SARGENTO_SEGUNDO(5), //
    SARGENTO_VICEPRIMERO(6), //
    TENIENTE_CORONEL(7), //
    ;

    private Integer id;

    private EnumMando(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
