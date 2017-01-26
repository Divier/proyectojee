package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumCausalRechazo implements SearchableEnumeration<Integer> {

    RECHAZO_POR_OBLIGACIONES(1), //
    RECHAZO_FALTA_RECURSOS(2), //
    ;

    private Integer codigo;

    private EnumCausalRechazo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
