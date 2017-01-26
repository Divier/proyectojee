package co.com.datatools.c2.entidades;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.datatools.c2.entidades.comun.OrganismoTransito;
import co.com.datatools.util.dto.EntidadC2;

/**
 * 
 * @author felipe.martinez
 * @version Iteracion 1 - valida
 */
@Entity
@Table(name = "parametro_organismo")
@NamedQueries({ @NamedQuery(
        name = "ParametroOrganismo.vigenteByCodigo",
        query = "SELECT p FROM ParametroOrganismo p LEFT JOIN FETCH p.parametro LEFT JOIN FETCH p.organismoTransito"
                + " WHERE p.parametro.codigo = :codigoParametro AND p.fechaInicio <= :fechaActual "
                + " AND (p.fechaFin IS NULL OR p.fechaFin >= :fechaActual)") })
public class ParametroOrganismo implements EntidadC2 {
    private static final long serialVersionUID = 1L;
    /**
     * Parametros:<br/>
     * <p>
     * <li>codigoParametro</li>
     * <li>fechaActual</li>
     * </p>
     */
    public final static String SQ_VIGENTES_BY_CODIGO = "ParametroOrganismo.vigenteByCodigo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parametro")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "valor_parametro")
    private String valor;

    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @JoinColumn(name = "codigo_organismo", referencedColumnName = "codigo_organismo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoTransito organismoTransito;

    @JoinColumn(name = "codigo_parametro", referencedColumnName = "codigo_parametro")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Parametro parametro;

    public ParametroOrganismo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    public OrganismoTransito getOrganismoTransito() {
        return organismoTransito;
    }

    public void setOrganismoTransito(OrganismoTransito organismoTransito) {
        this.organismoTransito = organismoTransito;
    }

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }

}
