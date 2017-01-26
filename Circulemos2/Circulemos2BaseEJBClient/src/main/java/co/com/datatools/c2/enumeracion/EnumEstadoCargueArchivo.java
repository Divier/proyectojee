package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoCargueArchivo implements SearchableEnumeration<Integer> {
    CARGADO(1), //
    EN_PROCESO(2), //
    PROCESADO(3), //
    CON_ERROR(4), //
	NOTIFICADO(5); //

    Integer idEstadoCargue;

    EnumEstadoCargueArchivo(Integer idTipoCargue) {
        this.idEstadoCargue = idTipoCargue;
    }

    @Override
    public Integer getValue() {
        return idEstadoCargue;
    }

    public static EnumEstadoCargueArchivo obtenerPorValor(Integer idEstadoCargue) {
        EnumEstadoCargueArchivo tipoCargue = null;
        for (EnumEstadoCargueArchivo tipo : EnumEstadoCargueArchivo.values()) {
            if (tipo.equals(idEstadoCargue)) {
                tipoCargue = tipo;
                break;
            }
        }
        return tipoCargue;
    }
}
