package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoTasaInteres implements SearchableEnumeration<String> {

    ANUAL("a"), //
    MENSUAL("m"), //
    ;

    private String nombre;

    private EnumTipoTasaInteres(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String getValue() {
        return nombre;
    }
}
