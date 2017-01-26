package co.com.datatools.c2.dto.comparendo;

import java.util.Date;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Tue Oct 13 14:49:35 COT 2015
 */
public class HistoricoAgenteDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private AgenteDTO agente;
    private Date fechaFinVigencia;
    private Date fechaInicioVigencia;
    private Integer entidadAgenteTransito;
    private Long persona;
    private String placa;
    private Integer organismoTransito;
    private Date fechaActualizacion;
    private Integer usuarioActualizacion;
    private Integer motivoVigenciaAgente;

    // --- Constructor
    public HistoricoAgenteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AgenteDTO getAgente() {
        return agente;
    }

    public void setAgente(AgenteDTO agente) {
        this.agente = agente;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Integer getEntidadAgenteTransito() {
        return entidadAgenteTransito;
    }

    public void setEntidadAgenteTransito(Integer entidadAgenteTransito) {
        this.entidadAgenteTransito = entidadAgenteTransito;
    }

    public Long getPersona() {
        return persona;
    }

    public void setPersona(Long persona) {
        this.persona = persona;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(Integer organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(Integer usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Integer getMotivoVigenciaAgente() {
        return motivoVigenciaAgente;
    }

    public void setMotivoVigenciaAgente(Integer motivoVigenciaAgente) {
        this.motivoVigenciaAgente = motivoVigenciaAgente;
    }

}
