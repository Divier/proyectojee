package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "detalle_cambio_estado")
@NamedQueries({ @NamedQuery(name = "DetalleCambioEstado.findAll", query = "SELECT d FROM DetalleCambioEstado d"),
        @NamedQuery(
                name = "DetalleCambioEstado.findByRango",
                query = "SELECT d FROM DetalleCambioEstado d WHERE d.rangoFormulario.id= :idRango"),
        @NamedQuery(
                name = "DetalleCambioEstado.countByResponsableFormulario",
                query = "SELECT COUNT(d) FROM DetalleCambioEstado d WHERE d.responsableFormulario.id= :idRespForm") })
public class DetalleCambioEstado implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    public static final String SQ_FIND_BY_RANGO = "DetalleCambioEstado.findByRango";

    /**
     * Cuenta los detalles de cambio estado de formularios asociados a un determinado responsable de formulario <br>
     * <br>
     * Consulta:<br>
     * SELECT COUNT(d) FROM DetalleCambioEstado d WHERE d.responsableFormulario.id= :idRespForm
     * 
     * @author luis.forero(2015-09-14)
     */
    public static final String SQ_COUNT_BY_RESPONSABLE_FORMULARIO = "DetalleCambioEstado.countByResponsableFormulario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_cambio_estado")
    private Long id;

    @Column(name = "numero_inicial_rango")
    private String numeroInicial;

    @Column(name = "numero_final_rango")
    private String numeroFinal;

    @Basic(optional = false)
    @Column(name = "fecha_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;

    @Column(name = "cantidad_formularios")
    private Integer cantidadFormularios;

    @Column(name = "folio")
    private String folio;

    @Basic(optional = false)
    @Column(name = "trama")
    private String trama;

    @Basic(optional = false)
    @Column(name = "fecha_aplicacion_estado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplicacionEstado;

    @Column(name = "observaciones")
    private String observaciones;

    @JoinColumn(name = "id_rango_formulario", referencedColumnName = "id_rango_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RangoFormulario rangoFormulario;

    @JoinColumn(name = "id_responsable_formulario", referencedColumnName = "id_responsable_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ResponsableFormulario responsableFormulario;

    @JoinColumn(name = "id_documento_formulario", referencedColumnName = "id_documento_formulario")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private DocumentoFormulario documentoFormulario;

    @JoinColumn(name = "id_causal_cambio_estado", referencedColumnName = "id_causal_cambio_estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CausalCambioEstado causalCambioEstado;

    @JoinColumn(name = "id_estado_formulario", referencedColumnName = "id_estado_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoFormulario estadoFormulario;

    @OneToMany(mappedBy = "detalleCambioEstado", fetch = FetchType.LAZY)
    private List<SeguimientoFormulario> seguimientoFormularioList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleCambioEstado", fetch = FetchType.LAZY)
    private List<Formulario> formularioList;

    @Basic(optional = false)
    @Column(name = "es_evento")
    private Boolean esEvento;

    public DetalleCambioEstado() {
    }

    public DetalleCambioEstado(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroInicial() {
        return numeroInicial;
    }

    public void setNumeroInicial(String numeroInicial) {
        this.numeroInicial = numeroInicial;
    }

    public String getNumeroFinal() {
        return numeroFinal;
    }

    public void setNumeroFinal(String numeroFinal) {
        this.numeroFinal = numeroFinal;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Integer getCantidadFormularios() {
        return cantidadFormularios;
    }

    public void setCantidadFormularios(Integer cantidadFormularios) {
        this.cantidadFormularios = cantidadFormularios;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public EstadoFormulario getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(EstadoFormulario idEstadoFormulario) {
        this.estadoFormulario = idEstadoFormulario;
    }

    public List<SeguimientoFormulario> getSeguimientoFormularioList() {
        return seguimientoFormularioList;
    }

    public void setSeguimientoFormularioList(List<SeguimientoFormulario> seguimientoFormularioList) {
        this.seguimientoFormularioList = seguimientoFormularioList;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public Date getFechaAplicacionEstado() {
        return fechaAplicacionEstado;
    }

    public void setFechaAplicacionEstado(Date fechaAplicacionEstado) {
        this.fechaAplicacionEstado = fechaAplicacionEstado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ResponsableFormulario getResponsableFormulario() {
        return responsableFormulario;
    }

    public void setResponsableFormulario(ResponsableFormulario responsableFormulario) {
        this.responsableFormulario = responsableFormulario;
    }

    public DocumentoFormulario getDocumentoFormulario() {
        return documentoFormulario;
    }

    public void setDocumentoFormulario(DocumentoFormulario documentoFormulario) {
        this.documentoFormulario = documentoFormulario;
    }

    public CausalCambioEstado getCausalCambioEstado() {
        return causalCambioEstado;
    }

    public void setCausalCambioEstado(CausalCambioEstado causalCambioEstado) {
        this.causalCambioEstado = causalCambioEstado;
    }

    public List<Formulario> getFormularioList() {
        return formularioList;
    }

    public void setFormularioList(List<Formulario> formularioList) {
        this.formularioList = formularioList;
    }

    public RangoFormulario getRangoFormulario() {
        return rangoFormulario;
    }

    public void setRangoFormulario(RangoFormulario rangoFormulario) {
        this.rangoFormulario = rangoFormulario;
    }

    public Boolean getEsEvento() {
        return this.esEvento;
    }

    public void setEsEvento(Boolean esEvento) {
        this.esEvento = esEvento;
    }

}
