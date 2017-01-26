package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion de tipos de amnistias
 * 
 * @author felipe.martinez
 */
public enum EnumTipoAmnistia implements SearchableEnumeration<Integer> {

    TIPO_DE_AMNISTIA_INTERESES(1), //
    TIPO_DE_AMNISTIA_CAPITAL(2), //
    TIPO_DE_AMNISTIA_CAPITAL_E_INTERESES(3); //

    private int codigo;

    EnumTipoAmnistia(int codigo) {
        this.codigo = codigo;
    }

    public int getPk() {
        return codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
