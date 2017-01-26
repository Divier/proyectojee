package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoPrecoactivo implements SearchableEnumeration<Integer> {

    CARGADO(1), //
    AUTORIZADO(2), //
    RECHAZADO_ERROR(3), //
    EN_COACTIVO(4), //
    VALIDADO(5), //
    ;

    private int pk;

    EnumEstadoPrecoactivo(int pk) {
        this.pk = pk;
    }

    @Override
    public Integer getValue() {
        return pk;
    }

}
