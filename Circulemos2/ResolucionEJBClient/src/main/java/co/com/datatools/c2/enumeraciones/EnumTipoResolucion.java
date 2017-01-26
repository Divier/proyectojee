package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoResolucion implements SearchableEnumeration<Integer> {

    SANCION(1), //
    RESOLUCION_RECTIFICACION(2), //
    ;

    private int codigo;

    EnumTipoResolucion(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
