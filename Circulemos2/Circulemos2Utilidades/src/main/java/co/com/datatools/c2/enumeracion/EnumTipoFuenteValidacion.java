package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoFuenteValidacion implements SearchableEnumeration<Integer> {
    CIUDADANO(1), //
    SISTEMA_INTEGRADOR_PRIMARIO(2), //
    PROCESO_COBRANZA(3), //
    BASE_DATOS_EXTERNA(4), //
    COURIER(5),//
    ;

    private int pk;

    private EnumTipoFuenteValidacion(int pk) {
        this.pk = pk;
    }

    @Override
    public Integer getValue() {
        return pk;
    }

}
