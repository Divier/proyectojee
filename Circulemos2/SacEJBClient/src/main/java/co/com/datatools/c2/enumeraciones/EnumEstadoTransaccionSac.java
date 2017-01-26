package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * @author Javier.Fajardo
 * 
 */
public enum EnumEstadoTransaccionSac implements SearchableEnumeration<Integer> {
    TRANSACCION_NULA(0), //
    PROCESADO_POR_SAC(1), //
    INCONSISTENTE(2), //
    ;

    private Integer id;

    private EnumEstadoTransaccionSac(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }
}
