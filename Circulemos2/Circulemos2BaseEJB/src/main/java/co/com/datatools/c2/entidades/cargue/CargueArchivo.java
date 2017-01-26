package co.com.datatools.c2.entidades.cargue;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.UsuarioPersona;
import co.com.datatools.util.dto.EntidadC2;

/**
 * The persistent class for the cargue_archivo database table.
 * 
 */
@Entity
@Table(name = "cargue_archivo")
@NamedQuery(name = "CargueArchivo.findAll", query = "SELECT c FROM CargueArchivo c")
public class CargueArchivo implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargue_archivo")
    private Long id;

    @Column(name = "consecutivo")
    private String consecutivo;

    @Column(name = "fecha_cargue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCargue;

    @Column(name = "fecha_finaliza")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinaliza;

    @Column(name = "id_documento_cargue")
    private Long idDocumentoCargue;

    @Column(name = "id_documento_inconsistencias")
    private Long idDocumentoInconsistencias;

    @Column(name = "total_leidos")
    private Integer totalLeidos;

    @Column(name = "total_agregados")
    private Integer totalAgregados;

    @Column(name = "total_actualizados")
    private Integer totalActualizados;

    @Column(name = "total_registros")
    private Integer totalRegistros;

    @Column(name = "total_inconsistencias")
    private Integer totalInconsistencias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_cargue")
    private UsuarioPersona usuarioCargue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_cargue_archivo")
    private TipoCargueArchivo tipoCargueArchivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_cargue_archivo")
    private EstadoCargueArchivo estadoCargueArchivo;

    @OneToMany(mappedBy = "cargueArchivo", fetch = FetchType.LAZY)
    private List<DocumentoResultadoCargue> documentoResultadoCargues;

    public CargueArchivo() {
    }

    public String getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getIdDocumentoCargue() {
        return this.idDocumentoCargue;
    }

    public void setIdDocumentoCargue(Long idDocumentoCargue) {
        this.idDocumentoCargue = idDocumentoCargue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCargue() {
        return fechaCargue;
    }

    public void setFechaCargue(Date fechaCargue) {
        this.fechaCargue = fechaCargue;
    }

    public UsuarioPersona getUsuarioCargue() {
        return usuarioCargue;
    }

    public void setUsuarioCargue(UsuarioPersona usuarioCargue) {
        this.usuarioCargue = usuarioCargue;
    }

    public TipoCargueArchivo getTipoCargueArchivo() {
        return tipoCargueArchivo;
    }

    public void setTipoCargueArchivo(TipoCargueArchivo tipoCargueArchivo) {
        this.tipoCargueArchivo = tipoCargueArchivo;
    }

    public EstadoCargueArchivo getEstadoCargueArchivo() {
        return estadoCargueArchivo;
    }

    public void setEstadoCargueArchivo(EstadoCargueArchivo estadoCargueArchivo) {
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

    public List<DocumentoResultadoCargue> getDocumentoResultadoCargues() {
        return documentoResultadoCargues;
    }

    public void setDocumentoResultadoCargues(List<DocumentoResultadoCargue> documentoResultadoCargues) {
        this.documentoResultadoCargues = documentoResultadoCargues;
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