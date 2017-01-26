package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoEvento implements SearchableEnumeration<Integer> {

    CITA(1, "Cita"), //
    // TAREA(2, "Tarea"), //se deshabilita a espera de la especificacion de los eventos tipo TAREA
    ;

    private int codigo;
    private String nombre;

    EnumTipoEvento(int codigo) {
        this.codigo = codigo;
    }

    EnumTipoEvento(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public Integer getValue() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}