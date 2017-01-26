package co.com.datatools.c2.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "rango_formulario")
@NamedQueries({ @NamedQuery(name = "RangoFormulario.findAll", query = "SELECT r FROM RangoFormulario r"),
        @NamedQuery(
                name = "RangoFormulario.findByNumeracion",
                query = "SELECT r FROM RangoFormulario r WHERE r.numeracion.id = :idNumeracion"),
        @NamedQuery(
                name = "RangoFormulario.findByNumeros",
                query = "SELECT r FROM RangoFormulario r WHERE r.numeracion.id = :idNumeracion AND r.numeroInicial <= :numeroFinal AND r.numeroFinal >= :numeroInicial"),
        @NamedQuery(
                name = "RangoFormulario.findByNumeroTrama",
                query = "SELECT r FROM RangoFormulario r WHERE r.tipoFormulario.id=:idTipoFormulario AND r.numeracion.trama= :trama AND r.numeroInicial<= :numeroFormulario AND r.numeroFinal>= :numeroFormulario"),
        @NamedQuery(
                name = "RangoFormulario.findRelacionadosByNumeros",
                query = "SELECT r FROM RangoFormulario r WHERE r.numeracion.trama=:trama AND r.tipoFormulario.id=:idTipoFormulario AND r.numeroFinal >=:numeroInicial AND r.numeroInicial <=:numeroFinal ORDER BY r.numeroInicial ASC)"),
        @NamedQuery(
                name = "RangoFormulario.findByCodigoOrganismo",
                query = "SELECT r FROM RangoFormulario r WHERE r.codigoOrganismo.codigoOrganismo=:codigoOrganismo AND r.tipoFormulario.id=:idTipoFormulario AND r.numeracion.trama= :trama AND r.numeroInicial<= :numeroFormulario AND r.numeroFinal>= :numeroFormulario")

})
public class RangoFormulario implements EntidadC2 {

    private static final long serialVersionUID = 1L;
    public static final String SQ_RANGOS_BY_NUMERACION = "RangoFormulario.findByNumeracion";
    public static final String SQ_RANGOS_BY_NUMEROS = "RangoFormulario.findByNumeros";
    public static final String SQ_RANGOS_BY_NUMERO_TRAMA = "RangoFormulario.findByNumeroTrama";
    public static final String SQ_RANGOS_RELACIONADOS_NUMEROS = "RangoFormulario.findRelacionadosByNumeros";
    public static final String SQ_RANGOS_BY_NUMERO_ORGANISMO = "RangoFormulario.findByCodigoOrganismo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rango_formulario")
    private Long id;

    @Basic(optional = false)
    @Column(name = "numero_inicial")
    private String numeroInicial;

    @Basic(optional = false)
    @Column(name = "numero_final")
    private String numeroFinal;

    @Basic(optional = false)
    @Column(name = "fecha_autorizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAutorizacion;

    @Basic(optional = false)
    @Column(name = "cantidad_total")
    private Integer cantidadTotal;

    @Basic(optional = false)
    @Column(name = "alerta_stock")
    private Boolean alertaStock;

    @Basic(optional = false)
    @Column(name = "cantidad_disponible")
    private Integer cantidadDisponible;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito codigoOrganismo;

    @JoinColumn(name = "id_documento_formulario", referencedColumnName = "id_documento_formulario")
    @ManyToOne(fetch = FetchType.LAZY)
    private DocumentoFormulario documentoFormulario;

    @JoinColumn(name = "id_tipo_formulario", referencedColumnName = "id_tipo_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoFormulario tipoFormulario;

    @JoinColumn(name = "id_numeracion", referencedColumnName = "id_numeracion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NumeracionFormulario numeracion;

    @OneToMany(mappedBy = "rangoFormulario", fetch = FetchType.LAZY)
    private List<Formulario> formularioList;

    @OneToMany(mappedBy = "rangoFormulario", fetch = FetchType.LAZY)
    private List<DetalleCambioEstado> detalleCambioEstadoList;

    public RangoFormulario() {
    }

    public RangoFormulario(Long idRangoFormulario) {
        this.id = idRangoFormulario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idRangoFormulario) {
        this.id = idRangoFormulario;
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

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public Boolean getAlertaStock() {
        return alertaStock;
    }

    public void setAlertaStock(Boolean alertaStock) {
        this.alertaStock = alertaStock;
    }

    public TipoFormulario getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(TipoFormulario idTipoFormulario) {
        this.tipoFormulario = idTipoFormulario;
    }

    public NumeracionFormulario getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(NumeracionFormulario idNumeracion) {
        this.numeracion = idNumeracion;
    }

    public OrganismoTransito getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(OrganismoTransito codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public DocumentoFormulario getDocumentoFormulario() {
        return documentoFormulario;
    }

    public void setDocumentoFormulario(DocumentoFormulario documentoFormulario) {
        this.documentoFormulario = documentoFormulario;
    }

    public List<Formulario> getFormularioList() {
        return formularioList;
    }

    public void setFormularioList(List<Formulario> formularioList) {
        this.formularioList = formularioList;
    }

    public List<DetalleCambioEstado> getDetalleCambioEstadoList() {
        return detalleCambioEstadoList;
    }

    public void setDetalleCambioEstadoList(List<DetalleCambioEstado> detalleCambioEstadoList) {
        this.detalleCambioEstadoList = detalleCambioEstadoList;
    }

}
