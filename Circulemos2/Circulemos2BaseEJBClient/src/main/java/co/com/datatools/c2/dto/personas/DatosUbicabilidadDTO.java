package co.com.datatools.c2.dto.personas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatosUbicabilidadDTO implements Serializable {

    /**
     * 
     * @author diego.fonseca
     * 
     *         DTO que almacena ids de direccionePersona, TelefonoPersona, y CorreoPersona
     */
    private static final long serialVersionUID = 1L;

    private List<Long> idDireccionPersonas = new ArrayList<>();
    private List<Long> idDireccionPersonasNoActualizadas = new ArrayList<>();
    private List<Long> idTelefonoPersonas = new ArrayList<>();
    private List<Long> idTelefonoPersonasNoActualizadas = new ArrayList<>();
    private List<Long> idCorreoPersonas = new ArrayList<>();
    private List<Long> idCorreoPersonasNoActualizadas = new ArrayList<>();

    public List<Long> getIdDireccionPersonas() {
        return idDireccionPersonas;
    }

    public void setIdDireccionPersonas(List<Long> idDireccionPersonas) {
        this.idDireccionPersonas = idDireccionPersonas;
    }

    public List<Long> getIdDireccionPersonasNoActualizadas() {
        return idDireccionPersonasNoActualizadas;
    }

    public void setIdDireccionPersonasNoActualizadas(List<Long> idDireccionPersonasNoActualizadas) {
        this.idDireccionPersonasNoActualizadas = idDireccionPersonasNoActualizadas;
    }

    public List<Long> getIdTelefonoPersonas() {
        return idTelefonoPersonas;
    }

    public void setIdTelefonoPersonas(List<Long> idTelefonoPersonas) {
        this.idTelefonoPersonas = idTelefonoPersonas;
    }

    public List<Long> getIdTelefonoPersonasNoActualizadas() {
        return idTelefonoPersonasNoActualizadas;
    }

    public void setIdTelefonoPersonasNoActualizadas(List<Long> idTelefonoPersonasNoActualizadas) {
        this.idTelefonoPersonasNoActualizadas = idTelefonoPersonasNoActualizadas;
    }

    public List<Long> getIdCorreoPersonas() {
        return idCorreoPersonas;
    }

    public void setIdCorreoPersonas(List<Long> idCorreoPersonas) {
        this.idCorreoPersonas = idCorreoPersonas;
    }

    public List<Long> getIdCorreoPersonasNoActualizadas() {
        return idCorreoPersonasNoActualizadas;
    }

    public void setIdCorreoPersonasNoActualizadas(List<Long> idCorreoPersonasNoActualizadas) {
        this.idCorreoPersonasNoActualizadas = idCorreoPersonasNoActualizadas;
    }

}
