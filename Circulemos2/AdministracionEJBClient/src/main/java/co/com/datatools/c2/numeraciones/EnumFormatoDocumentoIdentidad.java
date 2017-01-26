package co.com.datatools.c2.numeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumFormatoDocumentoIdentidad implements SearchableEnumeration<Integer> {
    NUMERICO(1), //
    ALFA_NUMERICO(2), //
    ;

    private int codigo;

    private EnumFormatoDocumentoIdentidad(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return this.codigo;
    }
}
