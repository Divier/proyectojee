package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion que permite identificar los valores posibles de la tabla "Entidad"
 * 
 * @author luis.forero (2016-02-03)
 */
public enum EnumEntidad implements SearchableEnumeration<Integer> {
    PROCESA_COMPARENDO(1), //
    COMPARENDO(2), //
    ;

    private Integer codigoEntidad;

    private EnumEntidad(Integer codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    @Override
    public Integer getValue() {
        return codigoEntidad;
    }

}
