package co.com.datatools.c2.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Contiene los valores por los cuales se puede realizar la consulta de resoluciones.
 * 
 * @author julio.pinzon
 */
public class ConsultaResolucionesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private List<Long> idResolucion;

    // --- Constructor
    public ConsultaResolucionesDTO() {
    }

    // --- Getters-Setters

    public List<Long> getIdResolucion() {
        return idResolucion;
    }

    public void setIdResolucion(List<Long> idResolucion) {
        this.idResolucion = idResolucion;
    }

}