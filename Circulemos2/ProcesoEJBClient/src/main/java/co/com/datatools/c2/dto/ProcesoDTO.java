package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * DTO de Proceso No autogenerar, tiene datos adicionales trazabilidadProcesoList
 * 
 * @author Generated
 * @version 3.0 - Wed Mar 16 15:56:31 COT 2016
 */
public class ProcesoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private TipoProcesoDTO tipoProceso;
    private String numeroProceso;
    private Date fechaInicio;
    private Date fechaFin;
    private String observacion;
    private List<TrazabilidadProcesoDTO> trazabilidadProcesoList;
    private List<ParticipanteProcesoDTO> participantesProcesoList;
    private EstadoProcesoDTO estadoProceso;

    // --- Constructor
    public ProcesoDTO() {
    }

    public ProcesoDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoProcesoDTO getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(TipoProcesoDTO tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public String getNumeroProceso() {
        return numeroProceso;
    }

    public void setNumeroProceso(String numeroProceso) {
        this.numeroProceso = numeroProceso;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<TrazabilidadProcesoDTO> getTrazabilidadProcesoList() {
        return trazabilidadProcesoList;
    }

    public void setTrazabilidadProcesoList(List<TrazabilidadProcesoDTO> trazabilidadProcesoList) {
        this.trazabilidadProcesoList = trazabilidadProcesoList;
    }

    public List<ParticipanteProcesoDTO> getParticipantesProcesoList() {
        return participantesProcesoList;
    }

    public void setParticipantesProcesoList(List<ParticipanteProcesoDTO> participantesProcesoList) {
        this.participantesProcesoList = participantesProcesoList;
    }

    public EstadoProcesoDTO getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(EstadoProcesoDTO estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

}