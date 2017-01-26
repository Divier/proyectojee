package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoArchivoPersona implements SearchableEnumeration<Integer> {
    NO_UBICABILIDAD(1) //
    ;

    private Integer pk;

    private EnumTipoArchivoPersona(Integer pk) {
        this.pk = pk;
    }

    @Override
    public Integer getValue() {
        return pk;
    }

}
