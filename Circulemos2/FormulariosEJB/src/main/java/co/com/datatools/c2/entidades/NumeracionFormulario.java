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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author claudia.rodriguez
 */
@Entity
@Table(name = "numeracion_formulario")
@NamedQueries({ @NamedQuery(name = "NumeracionFormulario.findAll", query = "SELECT n FROM NumeracionFormulario n"),
        @NamedQuery(
                name = "NumeracionFormulario.findByFecha",
                query = "SELECT n FROM NumeracionFormulario n WHERE n.tipoFormulario.id=:idTipoFormulario AND :fecha BETWEEN n.fechaInicial AND n.fechaFinal AND n.trama=:trama ORDER BY n.fechaInicial ASC"),
        @NamedQuery(
                name = "NumeracionFormulario.countByTipoVigente",
                query = "SELECT n FROM NumeracionFormulario n WHERE n.tipoFormulario.id=:idTipoFormulario "
                        + "AND ((n.fechaInicial BETWEEN :fechaIni AND :fechaFin OR n.fechaFinal BETWEEN :fechaIni AND :fechaFin) "
                        + "OR (:fechaIni BETWEEN n.fechaInicial AND n.fechaFinal AND :fechaFin BETWEEN n.fechaInicial AND n.fechaFinal)) "
                        + "AND n.id <> :idNumeracion"), })
public class NumeracionFormulario implements EntidadC2 {

    private static final long serialVersionUID = 1L;
    /**
     * Consulta cuantas numeraciones para el tipo de formulario existen cruzadas con las fechas de vigencia ingresadas
     * <p>
     * Parametros:
     * <li>idTipoFormulario id del tipo de formulario</li>
     * <li>fechaIni fecha inicial de vigencia de la numeracion</li>
     * <li>fechaFin fecha final de vigencia de la numeracion</li>
     * </p>
     * <br/>
     * 
     * <pre>
     * SELECT COUNT(n) FROM NumeracionFormulario n WHERE n.tipoFormulario.id=:idTipoFormulario 
     * AND ((n.fechaInicial BETWEEN :fechaIni AND :fechaFin OR n.fechaFinal BETWEEN :fechaIni AND :fechaFin) 
     * OR (:fechaIni BETWEEN n.fechaInicial AND n.fechaFinal AND :fechaFin BETWEEN n.fechaInicial AND n.fechaFinal))
     * </pre>
     */
    public static final String SQ_COUNT_TIPO_VIGENTE = "NumeracionFormulario.countByTipoVigente";
    public static final String SQ_NUMERACION_BY_FECHA = "NumeracionFormulario.findByFecha";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_numeracion")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "fecha_vigencia_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Basic(optional = false)
    @Column(name = "fecha_vigencia_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @Basic(optional = false)
    @Column(name = "digitos")
    private int digitos;

    @Basic(optional = false)
    @Column(name = "activo")
    private Boolean activo;

    @Basic(optional = false)
    @Column(name = "trama")
    private String trama;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeracion", fetch = FetchType.LAZY)
    @OrderBy("digito")
    private List<DetalleNumeracion> detalleNumeracionList;

    @JoinColumn(name = "id_tipo_formulario", referencedColumnName = "id_tipo_formulario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoFormulario tipoFormulario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeracion", fetch = FetchType.LAZY)
    private List<RangoFormulario> rangoFormularioList;

    public NumeracionFormulario() {
    }

    public NumeracionFormulario(Integer idNumeracion) {
        this.id = idNumeracion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idNumeracion) {
        this.id = idNumeracion;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaVigenciaInicial) {
        this.fechaInicial = fechaVigenciaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaVigenciaFinal) {
        this.fechaFinal = fechaVigenciaFinal;
    }

    public int getDigitos() {
        return digitos;
    }

    public void setDigitos(int digitos) {
        this.digitos = digitos;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public List<DetalleNumeracion> getDetalleNumeracionList() {
        return detalleNumeracionList;
    }

    public void setDetalleNumeracionList(List<DetalleNumeracion> detalleNumeracionList) {
        this.detalleNumeracionList = detalleNumeracionList;
    }

    public TipoFormulario getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(TipoFormulario TipoFormulario) {
        this.tipoFormulario = TipoFormulario;
    }

    public List<RangoFormulario> getRangoFormularioList() {
        return rangoFormularioList;
    }

    public void setRangoFormularioList(List<RangoFormulario> rangoFormularioList) {
        this.rangoFormularioList = rangoFormularioList;
    }

}
