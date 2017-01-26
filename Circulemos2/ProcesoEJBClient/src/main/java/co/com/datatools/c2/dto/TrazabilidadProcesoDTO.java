package co.com.datatools.c2.dto;

import java.util.Date;
import java.util.List;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Jun 13 10:51:52 COT 2016
 */
public class TrazabilidadProcesoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private ProcesoDTO proceso;
    private EstadoProcesoDTO estadoProceso;
    private Date fechaInicio;
    private Date fechaFin;
    private UsuarioPersonaDTO usuario;
    private List<DocumentoProcesoDTO> documentos;

    // --- Constructor
    public TrazabilidadProcesoDTO() {
    }

    public TrazabilidadProcesoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcesoDTO getProceso() {
        return this.proceso;
    }

    public void setProceso(ProcesoDTO proceso) {
        this.proceso = proceso;
    }

    public EstadoProcesoDTO getEstadoProceso() {
        return this.estadoProceso;
    }

    public void setEstadoProceso(EstadoProcesoDTO estadoProceso) {
        this.estadoProceso = estadoProceso;
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

    public UsuarioPersonaDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioPersonaDTO usuario) {
        this.usuario = usuario;
    }

    public List<DocumentoProcesoDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoProcesoDTO> documentos) {
        this.documentos = documentos;
    }

}
