package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoNotificacionComparendo implements SearchableEnumeration<Integer> {

    NOTIFICACION_CORREO(1), //
    NOTIFICACION_PERSONAL(2), //
    NOTIFICACION_POR_AVISO(3), //
    NOTIFICACION_POR_CONDUCTA_CONCLUYENTE(4), //
    EN_PROCESO_CORREO_CERTIFICADO(5), //
    EN_PROCESO_NOTIFICACION_POR_AVISO(6), //
    VACIO(null), //
    ;

    private Integer value;

    EnumTipoNotificacionComparendo(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
