package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoComparendoSimit implements SearchableEnumeration<Integer> {

    GENERADO(1), //
    CORREGIDO(2), //
    ;

    private int pk;

    EnumEstadoComparendoSimit(int pk) {
        this.pk = pk;
    }

    @Override
    public Integer getValue() {
        return pk;
    }

}
