package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Listado de los paises del sistema
 * 
 * @author giovanni.velandia
 */
public enum EnumMunicipio implements SearchableEnumeration<Integer> {

    GUAYAQUIL(89), //
    ;

    /**
     * columna codigo_campo de la tabla campo_entidad
     */
    int id;

    private EnumMunicipio(int id) {
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
