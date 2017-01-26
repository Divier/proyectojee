package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoLectura implements SearchableEnumeration<Short> {

    NUEVO(Short.valueOf("1")), //
    RECIBIDO(Short.valueOf("2")), //
    NO_RECIBIDO(Short.valueOf("3")); //

    private short codigo;

    private EnumEstadoLectura(short codigo) {
        this.codigo = codigo;
    }

    @Override
    public Short getValue() {
        return codigo;
    }
}
