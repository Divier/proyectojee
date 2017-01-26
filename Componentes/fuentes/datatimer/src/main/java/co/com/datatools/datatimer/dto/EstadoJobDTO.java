package co.com.datatools.datatimer.dto;

import java.io.Serializable;

/**
 * No volver a autogenerar
 * 
 * @author Generated
 * @version 3.0 - Wed Apr 27 14:53:46 COT 2016
 */
public class EstadoJobDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private int id;
    private String nombreEstadoJob;

    // --- Constructor
    public EstadoJobDTO() {
    }

    public EstadoJobDTO(int id) {
        this.id = id;
    }

    // --- Getters-Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEstadoJob() {
        return this.nombreEstadoJob;
    }

    public void setNombreEstadoJob(String nombreEstadoJob) {
        this.nombreEstadoJob = nombreEstadoJob;
    }
}