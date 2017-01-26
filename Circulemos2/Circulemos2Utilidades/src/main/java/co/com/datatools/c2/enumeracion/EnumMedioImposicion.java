package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumMedioImposicion implements SearchableEnumeration<Integer> {
    MANUAL(1), // (PEDESTRE GUAYAQUIL)
    FOTOMULTA(2), // (ELECTRONICO GUAYAQUIL)
    ELECTRONICO_DEAP(3)//
    ;

    private int pk;

    EnumMedioImposicion(int pk) {
        this.pk = pk;
    }

    public int getPk() {
        return pk;
    }

    @Override
    public Integer getValue() {
        return pk;
    }
}