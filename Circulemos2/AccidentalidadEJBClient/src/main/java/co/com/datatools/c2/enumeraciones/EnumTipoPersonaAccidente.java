package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoPersonaAccidente implements SearchableEnumeration<Integer> {

    CONDUCTOR(1), //
    PASAJERO(2), //
    PEATON(3), //
    TESTIGO(4), //
    ;

    private int idTipoPersoAccid;

    private EnumTipoPersonaAccidente(int idTipoPersoAccid) {
        this.idTipoPersoAccid = idTipoPersoAccid;
    }

    public int getIdTipoPersoAccid() {
        return idTipoPersoAccid;
    }

    @Override
    public Integer getValue() {
        return idTipoPersoAccid;
    }
}