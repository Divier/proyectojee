package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoFechaOrigen implements SearchableEnumeration<Integer> {

    FECHA_NOTIFICACION(1), //
    FECHA_DE_IMPOSICION(2);

    private int codigo;

    EnumTipoFechaOrigen(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }
}
