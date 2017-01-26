package co.com.datatools.c2.entidades;

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
import javax.persistence.Table;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "formulario")
@NamedQueries({ @NamedQuery(
        name = "Formulario.findByRango",
        query = "SELECT f FROM Formulario f WHERE f.rangoFormulario.id= :idRango"),
        @NamedQuery(
                name = "Formulario.countByResponsable",
                query = "SELECT COUNT(f) FROM Formulario f WHERE f.responsableFormulario.id= :idResponsable AND f.estadoFormulario.id IN :estados AND f.tipoFormulario.id= :idTipoFormulario"),
        @NamedQuery(
                name = "Formulario.findByNumeroTipoFormulario",
                query = "SELECT f FROM Formulario f WHERE f.numeroFormulario = :numeroFormulario AND f.tipoFormulario.id= :idTipoFormulario"),
        @NamedQuery(
                name = "Formulario.findByIntervaloResponsable",
                query = "SELECT COUNT(f) FROM Formulario f WHERE f.rangoFormulario.id= :idRango AND f.numeroFormulario>=:numeroInicial AND f.numeroFormulario<=:numeroFinal AND f.responsableFormulario.id <> :idResponsable"),
        @NamedQuery(
                name = "Formulario.findByRangoEstado",
                query = "SELECT f FROM Formulario f WHERE f.rangoFormulario.id= :idRango AND f.numeroFormulario>=:numeroInicial AND f.numeroFormulario<=:numeroFinal AND f.estadoFormulario.id IN (:idEstados)") })
public class Formulario implements EntidadC2 {

    private static final long serialVersionUID = 1L;

    public static final String SQ_FIND_BY_RANGO = "Formulario.findByRango";
    public static final String SQ_COUNT_BY_RESP_TIPO_FORMULARIO = "Formulario.countByResponsable";
    public static final String SQ_FORM_BY_NUMERO_TIPO_FORM = "Formulario.findByNumeroTipoFormulario";
    public static final String SQ_FORM_BY_INTERVALO_RESPONSABLE = "Formulario.findByIntervaloResponsable";
    public static final String SQ_FORM_BY_RANGO_ESTADO = "Formulario.findByRangoEstado";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formulario")
    private Long id;

    @Basic(optional = false)
    @Column(name = "numero_formulario")
    private String numeroFormulario;

    @JoinColumn(name = "id_estado_formulario", referencedColumnName = "id_estado_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoFormulario estadoFormulario;

    @JoinColumn(name = "id_tipo_formulario", referencedColumnName = "id_tipo_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoFormulario tipoFormulario;

    @JoinColumn(name = "id_responsable_formulario", referencedColumnName = "id_responsable_formulario")
    @ManyToOne(fetch = FetchType.LAZY)
    private ResponsableFormulario responsableFormulario;

    @JoinColumn(name = "id_rango_formulario", referencedColumnName = "id_rango_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RangoFormulario rangoFormulario;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito codigoOrganismo;

    @JoinColumn(name = "id_detalle_cambio_estado", referencedColumnName = "id_detalle_cambio_estado")
    @ManyToOne(fetch = FetchType.LAZY)
    private DetalleCambioEstado detalleCambioEstado;

    public Formulario() {
    }

    public Formulario(Long idFormulario) {
        this.id = idFormulario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idFormulario) {
        this.id = idFormulario;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public EstadoFormulario getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(EstadoFormulario idEstadoFormulario) {
        this.estadoFormulario = idEstadoFormulario;
    }

    public TipoFormulario getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(TipoFormulario tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public ResponsableFormulario getResponsableFormulario() {
        return responsableFormulario;
    }

    public void setResponsableFormulario(ResponsableFormulario responsableFormulario) {
        this.responsableFormulario = responsableFormulario;
    }

    public RangoFormulario getRangoFormulario() {
        return rangoFormulario;
    }

    public void setRangoFormulario(RangoFormulario rangoFormulario) {
        this.rangoFormulario = rangoFormulario;
    }

    public OrganismoTransito getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public void setCodigoOrganismo(OrganismoTransito codigoOrganismo) {
        this.codigoOrganismo = codigoOrganismo;
    }

    public DetalleCambioEstado getDetalleCambioEstado() {
        return detalleCambioEstado;
    }

    public void setDetalleCambioEstado(DetalleCambioEstado detalleCambioEstado) {
        this.detalleCambioEstado = detalleCambioEstado;
    }

}
