package co.com.datatools.c2.numeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Contiene los tipos de consecutivos manejados en el sistema.
 * 
 * @author julio.pinzon
 * 
 */
public enum EnumCargo implements SearchableEnumeration<Integer> {
    COORDINADOR_PROCESOS_COACTIVA(1), //
    ABOGADO(2), //
    COORDINADOR_IMPUGNACIONES(3), //
    AGENTE(5), //
    ;
    private int pk;

    private EnumCargo(int pk) {
        this.pk = pk;
    }

    @Override
    public Integer getValue() {
        return pk;
    }
}
