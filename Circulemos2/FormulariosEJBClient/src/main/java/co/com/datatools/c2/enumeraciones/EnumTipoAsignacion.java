package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion para los tipos de asignacion de formularios
 * 
 * @author claudia.rodriguez
 * 
 */
public enum EnumTipoAsignacion implements SearchableEnumeration<Integer> {

    AGENTE(1), //
    EMPRESA(2), //
    RADICADOR(3);

    private Integer id;

    private EnumTipoAsignacion(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
