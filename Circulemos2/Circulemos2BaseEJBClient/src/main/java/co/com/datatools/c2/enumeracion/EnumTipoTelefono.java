package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoTelefono implements SearchableEnumeration<Integer> {

    TELEFONO_FIJO(1), //
    TELEFONO_CELULAR(2), //
    TELEFONO_FIJO_EMPRESA(3), //
    FAX_EMPRESA(4), //
    ;

    Integer tipoTelefono;

    EnumTipoTelefono(Integer tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    @Override
    public Integer getValue() {
        // TODO Auto-generated method stub
        return tipoTelefono;
    }//

}
