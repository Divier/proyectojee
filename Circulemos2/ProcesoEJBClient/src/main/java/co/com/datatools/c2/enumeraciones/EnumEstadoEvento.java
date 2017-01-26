package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumEstadoEvento implements SearchableEnumeration<Integer> {

    SIN_REGISTRO_ASISTENCIA(1, "Sin registrar asistencia"), //
    CON_REGISTRO_ASISTENCIA(2, "Con registro de asistencia"), //
    ;

    private int codigo;
    private String nombre;

    EnumEstadoEvento(int codigo) {
        this.codigo = codigo;
    }

    EnumEstadoEvento(int codigo, String nombre) {
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