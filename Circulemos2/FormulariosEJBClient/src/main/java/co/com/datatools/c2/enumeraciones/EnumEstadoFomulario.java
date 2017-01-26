package co.com.datatools.c2.enumeraciones;

import co.com.datatools.c2.util.SearchableEnumeration;

/**
 * Enumeracion que permite identificar los posibles estados de un formulario
 * 
 * @author luis.forero
 * 
 */
public enum EnumEstadoFomulario implements SearchableEnumeration<Integer> {

    PENDIENTE_POR_ASIGNAR(1, "Pendiente por Asignar"), //
    ASIGNADO(2, "Asignado"), //
    DEVUELTO(3, "Devuelto"), //
    ANULADO(4, "Anulado"), //
    CONSUMIDO(5, "Consumido");

    private final Integer id;
    private final String descripcion;

    private EnumEstadoFomulario(Integer id, String descripcion) {
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
