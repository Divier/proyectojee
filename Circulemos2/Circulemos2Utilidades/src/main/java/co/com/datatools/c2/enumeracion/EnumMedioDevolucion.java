package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion para los medios de devolucion
 * 
 * @author luis.forero
 * 
 */
public enum EnumMedioDevolucion implements SearchableEnumeration<Integer> {
    CHEQUE(1), //
    TRANSFERENCIA_CUENTA(2), //
    ;
    private int codigo;

    private EnumMedioDevolucion(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
