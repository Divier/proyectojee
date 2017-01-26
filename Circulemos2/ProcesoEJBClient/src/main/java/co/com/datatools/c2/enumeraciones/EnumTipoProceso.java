package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoProceso implements SearchableEnumeration<Integer> {

    IMPUGNACION_COMPARENDO(1, "Impugnaci�n de comparendo"), //
    FINANCIACION_COMPARENDO(2, "Financiaci�n comparendo"), //
    ANULACION_COMPARENDO(3, "Anulaci�n de comparendo"), //
    COACTIVO(4, "Coactivo"), //
    UBICABILIDAD(5, "Ubicabilidad"), //
    IMPUGNACION_SOLUCION_INMEDIATA(6, "Impugnaci�n con soluci�n inmediata"), //
    IMPUGNACION_SIN_SOLUCION_INMEDIATA(7, "Impugnaci�n sin soluci�n inmediata"), //
    IMPUGNACION_FALLO(8, "Impugnaci�n fallo")//
    ;

    private int codigo;
    private String nombre;

    EnumTipoProceso(int codigo) {
        this.codigo = codigo;
    }

    EnumTipoProceso(int codigo, String nombre) {
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