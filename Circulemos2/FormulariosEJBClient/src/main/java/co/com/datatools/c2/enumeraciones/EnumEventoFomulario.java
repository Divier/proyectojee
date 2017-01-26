package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion que permite identificar los posibles eventos de un formulario
 * 
 * @author julio.pinzon
 * 
 */
public enum EnumEventoFomulario implements SearchableEnumeration<Integer> {
    ASIGNADO(2, "Asignado"), //
    DEVUELTO(3, "Devuelto"), //
    ANULADO(4, "Anulado");

    private final Integer id;
    private final String descripcion;

    private EnumEventoFomulario(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    @Override
    public Integer getValue() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
