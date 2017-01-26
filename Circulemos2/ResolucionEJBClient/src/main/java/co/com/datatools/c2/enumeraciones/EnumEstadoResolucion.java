package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoResolucion implements SearchableEnumeration<Integer> {

    VIGENTE(1), //
    ANULADA(2), //
    ;

    private int codigo;

    EnumEstadoResolucion(int codigo) {
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
