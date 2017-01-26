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

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "detalle_numeracion")
@NamedQueries({ @NamedQuery(name = "DetalleNumeracion.findAll", query = "SELECT d FROM DetalleNumeracion d"),
        @NamedQuery(
                name = "DetalleNumeracion.findByNumeracion",
                query = "SELECT d FROM DetalleNumeracion d WHERE d.numeracion.id=:idNumeracion"),
        @NamedQuery(
                name = "DetalleNumeracion.deleteByNumeracion",
                query = "DELETE DetalleNumeracion d WHERE d.numeracion.id=:idNumeracion") })
public class DetalleNumeracion implements EntidadC2 {

    private static final long serialVersionUID = 1L;
    public static final String SQ_DETALLE_BY_NUMERACION = "DetalleNumeracion.findByNumeracion";
    public static final String DELETE_BY_NUMERACION = "DetalleNumeracion.deleteByNumeracion";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_numeracion")
    private Long id;

    @Basic(optional = false)
    @Column(name = "digito")
    private Long digito;

    @Basic(optional = false)
    @Column(name = "caracter_inicio")
    private Character caracterInicio;

    @Basic(optional = false)
    @Column(name = "caracter_fin")
    private Character caracterFin;

    @Basic(optional = false)
    @Column(name = "incremental")
    private Boolean incremental;

    @Basic(optional = false)
    @Column(name = "numerico")
    private Boolean numerico;

    @JoinColumn(name = "id_numeracion", referencedColumnName = "id_numeracion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NumeracionFormulario numeracion;

    public DetalleNumeracion() {
    }

    public DetalleNumeracion(Long idDetalleNumeracion) {
        this.id = idDetalleNumeracion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idDetalleNumeracion) {
        this.id = idDetalleNumeracion;
    }

    public Long getDigito() {
        return digito;
    }

    public void setDigito(Long digito) {
        this.digito = digito;
    }

    public Character getCaracterInicio() {
        return caracterInicio;
    }

    public void setCaracterInicio(Character caracterInicio) {
        this.caracterInicio = caracterInicio;
    }

    public Character getCaracterFin() {
        return caracterFin;
    }

    public void setCaracterFin(Character caracterFin) {
        this.caracterFin = caracterFin;
    }

    public Boolean getIncremental() {
        return incremental;
    }

    public void setIncremental(Boolean incremental) {
        this.incremental = incremental;
    }

    public Boolean getNumerico() {
        return numerico;
    }

    public void setNumerico(Boolean numerico) {
        this.numerico = numerico;
    }

    public NumeracionFormulario getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(NumeracionFormulario Numeracion) {
        this.numeracion = Numeracion;
    }

}
