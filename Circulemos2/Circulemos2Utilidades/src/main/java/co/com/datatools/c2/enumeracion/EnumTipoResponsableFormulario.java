package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * 
 * @author luis.forero
 * 
 */
public enum EnumTipoResponsableFormulario implements SearchableEnumeration<Integer> {
    ORGANISMO_TRANSITO(1), //
    EMPRESA(2), //
    ;
    private Integer id;

    private EnumTipoResponsableFormulario(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
