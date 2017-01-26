package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Listado de los paises del sistema
 * 
 * @author giovanni.velandia
 */
public enum EnumPais implements SearchableEnumeration<Integer> {

    COLOMBIA(47), //
    PERU(177), //
    ECUADOR(58), //
    ;

    /**
     * columna codigo_campo de la tabla campo_entidad
     */
    int id;

    private EnumPais(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public Integer getValue() {
        return id;
    }
}
