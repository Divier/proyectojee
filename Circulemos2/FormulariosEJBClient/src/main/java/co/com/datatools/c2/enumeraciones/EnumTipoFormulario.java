package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoFormulario implements SearchableEnumeration<Integer> {
    COMPARENDO_UNICO_NACIONAL(1);
    Integer id;

    EnumTipoFormulario(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        // TODO Auto-generated method stub
        return id;
    }

}
