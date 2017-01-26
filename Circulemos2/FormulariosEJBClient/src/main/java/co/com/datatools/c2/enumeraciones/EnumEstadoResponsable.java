package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion que permite identificar los estados de un responsable
 * 
 * @author luis.forero
 * 
 */
public enum EnumEstadoResponsable implements SearchableEnumeration<Integer> {
    ACTIVO(1), //
    INACTIVO(2), //
    VACACIONES(3), //
    LICENCIA(4), //
    INCAPACIDAD(5), //
    ;

    private Integer id;

    private EnumEstadoResponsable(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
