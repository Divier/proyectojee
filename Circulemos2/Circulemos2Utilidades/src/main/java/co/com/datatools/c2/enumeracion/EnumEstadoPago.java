package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoPago implements SearchableEnumeration<Integer> {

    PAGO_NO_CONCILIADO(1), //
    PAGO_APLICADO(2), //
    PARA_NOTIFICAR(3), //
    ;

    private int value;

    EnumEstadoPago(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
