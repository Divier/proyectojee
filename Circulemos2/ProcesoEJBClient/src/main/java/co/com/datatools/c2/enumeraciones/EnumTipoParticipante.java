package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoParticipante implements SearchableEnumeration<Integer> {

    ABOGADO(1), //
    INFRACTOR(2), //
    ;

    private int codigo;

    EnumTipoParticipante(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

}
