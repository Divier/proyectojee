package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoDescuento implements SearchableEnumeration<Integer> {

    CONFIG_POR_NOTIFICACION_COMPARENDO(1, "Por notificacion de comparendo"), //
    CONFIGN_POR_DIAS_TRANSCURRIDOS(2, "Por dias transcurridos");

    private Integer codigo;
    private String nombre;

    EnumTipoDescuento(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }
}