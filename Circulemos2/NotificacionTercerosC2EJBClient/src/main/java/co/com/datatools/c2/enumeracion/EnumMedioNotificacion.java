package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumMedioNotificacion implements SearchableEnumeration<Integer> {

    CORREO_ELECTRONICO_WS(1, "CORREO ELECTRONICO WEB SERVICE"), //
    CORREO_ELECTRONICO_ENVIO_CORREO(2, "CORREO ELECTRONICO ENVIO CORREO"), //
    ;

    private int codigo;
    private String nombre;

    private EnumMedioNotificacion(int codigo, String nombre) {
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