package co.com.datatools.c2.dto.personas;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Wed Mar 16 15:56:32 COT 2016
 */
public class FuncionarioDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Integer id;
    private Integer codigoProceso;
    private Date fechaFinalVigencia;
    private Date fechaInicioVigencia;
    private Integer idCargo;
    private PersonaDTO persona;

    // --- Constructor
    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Integer id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoProceso() {
        return this.codigoProceso;
    }

    public void setCodigoProceso(Integer codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    public Date getFechaFinalVigencia() {
        return this.fechaFinalVigencia;
    }

    public void setFechaFinalVigencia(Date fechaFinalVigencia) {
        this.fechaFinalVigencia = fechaFinalVigencia;
    }

    public Date getFechaInicioVigencia() {
        return this.fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Integer getIdCargo() {
        return this.idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public PersonaDTO getPersona() {
        return this.persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

}
