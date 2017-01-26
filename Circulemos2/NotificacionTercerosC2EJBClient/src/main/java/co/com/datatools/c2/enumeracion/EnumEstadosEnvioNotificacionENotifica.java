package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadosEnvioNotificacionENotifica implements SearchableEnumeration<Integer> {

    CREADO(1), //
    DESCARGADO(2), //
    RECHAZADO(4), //
    CADUCADO(5), //
    ;

    private int idEstado;

    private EnumEstadosEnvioNotificacionENotifica(int idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public Integer getValue() {
        return idEstado;
    }
}