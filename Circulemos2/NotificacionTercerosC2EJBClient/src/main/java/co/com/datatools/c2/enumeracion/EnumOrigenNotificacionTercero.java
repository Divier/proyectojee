package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumOrigenNotificacionTercero implements SearchableEnumeration<Integer> {

    REGISTRAR(1), //
    ACTUALIZAR(2), //
    RECTIFICAR(3);

    private int id;

    private EnumOrigenNotificacionTercero(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
