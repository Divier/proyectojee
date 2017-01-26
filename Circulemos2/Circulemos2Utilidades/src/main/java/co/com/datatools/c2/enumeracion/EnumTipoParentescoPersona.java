package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Contiene los tipos de parentescos posibles. Contiene la informacion de la tabla tipo_parentesco_persona
 * 
 * @author robert.bautista - 10/06/2014
 */
public enum EnumTipoParentescoPersona implements SearchableEnumeration<Integer> {
    CONYUGUE(1) //
    ;

    final private Integer id;

    /**
     * @param id
     */
    private EnumTipoParentescoPersona(int id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public Integer getValue() {
        return this.id;
    }

}
