package co.com.datatools.c2.dto.cargue;

import java.util.Date;

import co.com.datatools.c2.dto.comun.UsuarioPersonaDTO;
import co.com.datatools.c2.util.EntidadDtoC2;

/**
 * @author Generated
 * @version 3.0 - Mon Oct 24 11:58:04 COT 2016
 */
public class CargueArchivoDTO implements EntidadDtoC2 {
    private static final long serialVersionUID = 1L;
    // --- Atributos
    private Long id;
    private String consecutivo;
    private Date fechaCargue;
    private Date fechaFinaliza;
    private Long idDocumentoCargue;
    private UsuarioPersonaDTO usuarioCargue;
    private TipoCargueArchivoDTO tipoCargueArchivo;
    private EstadoCargueArchivoDTO estadoCargueArchivo;
    private Long idDocumentoInconsistencias;
    private Integer totalLeidos;
    private Integer totalRegistros;
    private Integer totalInconsistencias;
    private Integer totalAgregados;
    private Integer totalActualizados;
    private DocumentoResultadoCargueDTO documentoResultadoCargueDTO;

    // --- Constructor
    public CargueArchivoDTO() {
    }

    public CargueArchivoDTO(Long id) {
        this.id = id;

    }

    // --- Getters-Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFechaCargue() {
        return this.fechaCargue;
    }

    public void setFechaCargue(Date fechaCargue) {
        this.fechaCargue = fechaCargue;
    }

    public Long getIdDocumentoCargue() {
        return this.idDocumentoCargue;
    }

    public void setIdDocumentoCargue(Long idDocumentoCargue) {
        this.idDocumentoCargue = idDocumentoCargue;
    }

    public UsuarioPersonaDTO getUsuarioCargue() {
        return usuarioCargue;
    }

    public void setUsuarioCargue(UsuarioPersonaDTO usuarioCargue) {
        this.usuarioCargue = usuarioCargue;
    }

    public TipoCargueArchivoDTO getTipoCargueArchivo() {
        return tipoCargueArchivo;
    }

    public void setTipoCargueArchivo(TipoCargueArchivoDTO tipoCargueArchivo) {
        this.tipoCargueArchivo = tipoCargueArchivo;
    }

    public EstadoCargueArchivoDTO getEstadoCargueArchivo() {
        return estadoCargueArchivo;
    }

    public void setEstadoCargueArchivo(EstadoCargueArchivoDTO estadoCargueArchivo) {
        this.estadoCargueArchivo = estadoCargueArchivo;
    }

    public Long getIdDocumentoInconsistencias() {
        return idDocumentoInconsistencias;
    }

    public void setIdDocumentoInconsistencias(Long idDocumentoInconsistencias) {
        this.idDocumentoInconsistencias = idDocumentoInconsistencias;
    }

    public Integer getTotalLeidos() {
        return totalLeidos;
    }

    public void setTotalLeidos(Integer totalLeidos) {
        this.totalLeidos = totalLeidos;
    }

    public Integer getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(Integer totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public Integer getTotalInconsistencias() {
        return totalInconsistencias;
    }

    public void setTotalInconsistencias(Integer totalInconsistencias) {
        this.totalInconsistencias = totalInconsistencias;
    }

    public DocumentoResultadoCargueDTO getDocumentoResultadoCargueDTO() {
        return documentoResultadoCargueDTO;
    }

    public void setDocumentoResultadoCargueDTO(DocumentoResultadoCargueDTO documentoResultadoCargueDTO) {
        this.documentoResultadoCargueDTO = documentoResultadoCargueDTO;
    }

    public Integer getTotalAgregados() {
        return totalAgregados;
    }

    public void setTotalAgregados(Integer totalAgregados) {
        this.totalAgregados = totalAgregados;
    }

    public Integer getTotalActualizados() {
        return totalActualizados;
    }

    public void setTotalActualizados(Integer totalActualizados) {
        this.totalActualizados = totalActualizados;
    }

    public Date getFechaFinaliza() {
        return fechaFinaliza;
    }

    public void setFechaFinaliza(Date fechaFinaliza) {
        this.fechaFinaliza = fechaFinaliza;
    }

}
