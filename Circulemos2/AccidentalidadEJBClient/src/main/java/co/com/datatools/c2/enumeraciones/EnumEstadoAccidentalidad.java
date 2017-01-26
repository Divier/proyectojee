package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoAccidentalidad implements SearchableEnumeration<Integer> {

    REGISTRADO(1), //
    ;

    private int idEstadoAccidentalidad;

    private EnumEstadoAccidentalidad(int idEstadoAccidentalidad) {
        this.idEstadoAccidentalidad = idEstadoAccidentalidad;
    }

    public int getIdTipoPersoAccid() {
        return idEstadoAccidentalidad;
    }

    @Override
    public Integer getValue() {
        return idEstadoAccidentalidad;
    }
}