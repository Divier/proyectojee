package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoDocumentoUbicaTercero implements SearchableEnumeration<Integer> {

    CED(1), RUC(2), PAS(3), CRE(4), MIG(5), TAM(6),;

    private Integer id;

    private EnumTipoDocumentoUbicaTercero(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }
}