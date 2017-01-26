package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoProceso implements SearchableEnumeration<Integer> {

    IMPUGNACION_COMPARENDO(1, "Impugnación de comparendo"), //
    FINANCIACION_COMPARENDO(2, "Financiación comparendo"), //
    ANULACION_COMPARENDO(3, "Anulación de comparendo"), //
    COACTIVO(4, "Coactivo"), //
    UBICABILIDAD(5, "Ubicabilidad"), //
    IMPUGNACION_SOLUCION_INMEDIATA(6, "Impugnación con solución inmediata"), //
    IMPUGNACION_SIN_SOLUCION_INMEDIATA(7, "Impugnación sin solución inmediata"), //
    IMPUGNACION_FALLO(8, "Impugnación fallo")//
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