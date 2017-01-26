package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoOficio implements SearchableEnumeration<Integer> {
    INDIVIDUAL(1), //
    MASIVO(2), //
    ;

    private int codigo;

    private EnumTipoOficio(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }
}
