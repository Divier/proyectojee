package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoSaldo implements SearchableEnumeration<Integer> {

    CAPITAL(1, "Capital"), //
    INTERES(2, "Interés"), //
    COSTAS_PROCESALES(3, "Costas procesales"), //
    ;

    private Integer id;
    private String nombre;

    EnumTipoSaldo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Integer getValue() {
        return id;
    }

}
