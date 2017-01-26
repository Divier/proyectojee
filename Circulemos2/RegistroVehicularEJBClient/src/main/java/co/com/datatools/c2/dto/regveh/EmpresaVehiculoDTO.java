package co.com.datatools.c2.dto.regveh;

import java.util.Date;

import co.com.datatools.c2.dto.personas.PersonaJuridicaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Jan 07 08:57:51 COT 2015
 */
public class EmpresaVehiculoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private Date fechaInicio;
    private Date fechaFin;
    private VehiculoDTO vehiculo;
    private PersonaJuridicaDTO personaJuridica;

    // --- Constructor
    public EmpresaVehiculoDTO() {
    }

    public EmpresaVehiculoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public VehiculoDTO getVehiculo() {
        return this.vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }

    public PersonaJuridicaDTO getPersonaJuridica() {
        return this.personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridicaDTO personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

}
