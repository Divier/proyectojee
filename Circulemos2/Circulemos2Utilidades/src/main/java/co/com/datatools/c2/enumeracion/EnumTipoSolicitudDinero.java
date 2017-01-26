package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoSolicitudDinero implements SearchableEnumeration<Integer> {

    PAGO_OTRA_OBLIGACION(1), //
    SOLICITUD_DEVOLUCION_DINERO(2), //
    ;
    private int codigo;

    private EnumTipoSolicitudDinero(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
