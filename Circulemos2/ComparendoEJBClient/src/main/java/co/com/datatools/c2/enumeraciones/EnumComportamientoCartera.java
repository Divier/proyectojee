package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Se encarga de definir el comportamiento de afectar cartera a una persona en especifica
 * 
 * @author giovanni.velandia
 * 
 */
public enum EnumComportamientoCartera implements SearchableEnumeration<Integer> {
    PERMITE_CARTERA_PROPIETARIO(1), //
    PERSONA_DOCUMENTO_0(2), //
    ;

    EnumComportamientoCartera(int codigo) {
        this.codigo = codigo;
    }

    private int codigo;

    @Override
    public Integer getValue() {
        return codigo;
    }

}
