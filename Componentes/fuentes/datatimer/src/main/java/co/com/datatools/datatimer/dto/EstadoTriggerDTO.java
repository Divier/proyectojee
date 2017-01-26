package co.com.datatools.datatimer.dto;

import java.io.Serializable;

/**
 * No volver a autogenerar
 * 
 * @author Generated
 * @version 3.0 - Thu Apr 28 17:53:15 COT 2016
 */
public class EstadoTriggerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private String nombreEstadoTrigger;

    // --- Constructor
    public EstadoTriggerDTO() {
    }

    public EstadoTriggerDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEstadoTrigger() {
        return this.nombreEstadoTrigger;
    }

    public void setNombreEstadoTrigger(String nombreEstadoTrigger) {
        this.nombreEstadoTrigger = nombreEstadoTrigger;
    }
}